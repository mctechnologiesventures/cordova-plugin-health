var exec = require("cordova/exec");

var WHealth = function () {
  this.name = "whealth";
};

WHealth.prototype.isAvailable = function (onSuccess, onError) {
  exec(onSuccess, onError, "whealth", "isAvailable", []);
};

WHealth.prototype.disconnect = function (onSuccess, onError) {
  exec(onSuccess, onError, "whealth", "disconnect", []);
};

WHealth.prototype.promptInstallFit = function (onSuccess, onError) {
  exec(onSuccess, onError, "whealth", "promptInstallFit", []);
};

WHealth.prototype.openHealthSettings = function (onSuccess, onError) {
  exec(onSuccess, onError, "whealth", "openHealthSettings", []);
};

WHealth.prototype.requestAuthorization = function (
  datatypes,
  onSuccess,
  onError
) {
  if (!Array.isArray(datatypes)) {
    onError("data types must be array");
  } else {
    exec(onSuccess, onError, "whealth", "requestAuthorization", datatypes);
  }
};

WHealth.prototype.isAuthorized = function (datatypes, onSuccess, onError) {
  exec(onSuccess, onError, "whealth", "isAuthorized", datatypes);
};

WHealth.prototype.query = function (opts, onSuccess, onError) {
  //calories.active is done by asking all calories and subtracting the basal
  if (opts.dataType == "calories.active") {
    //get basal average in the time window between endDate and BASAL_CALORIES_QUERY_PERIOD
    navigator.whealth.queryAggregated(
      {
        dataType: "calories.basal",
        endDate: opts.endDate,
        startDate: opts.startDate,
        bucket: opts.bucket,
      },
      function (data) {
        var basal_ms = data.value / (opts.endDate - opts.startDate);
        //now get the total
        opts.dataType = "calories";
        navigator.whealth.query(
          opts,
          function (data) {
            //and subtract the basal
            for (var i = 0; i < data.length; i++) {
              data[i].value -=
                basal_ms *
                (data[i].endDate.getTime() - data[i].startDate.getTime());

              //although it shouldn't happen, after subtracting, sometimes the values are negative,
              //in that case let's return 0 (negative values don't make sense)
              if (data[i].value < 0) data[i].value = 0;
            }
            onSuccess(data);
          },
          onError
        );
      },
      onError
    );
  } else {
    //standard case, just use the name as it is
    if (opts.startDate && typeof opts.startDate == "object")
      opts.startDate = opts.startDate.getTime();
    if (opts.endDate && typeof opts.endDate == "object")
      opts.endDate = opts.endDate.getTime();
    exec(
      function (data) {
        for (var i = 0; i < data.length; i++) {
          data[i].startDate = new Date(data[i].startDate);
          data[i].endDate = new Date(data[i].endDate);
        }
        // if nutrition, add water
        if (opts.dataType == "nutrition") {
          opts.dataType = "nutrition.water";
          navigator.whealth.query(
            opts,
            function (water) {
              // merge and sort
              for (var i = 0; i < water.length; i++) {
                water[i].value = {
                  item: "water",
                  nutrients: { "nutrition.water": water[i].value },
                };
                water[i].unit = "nutrition";
              }
              data.concat(water);
              data = data.concat(water);
              data.sort(function (a, b) {
                return a.startDate - b.startDate;
              });
              onSuccess(data);
            },
            onError
          );
        } else {
          onSuccess(data);
        }
      },
      onError,
      "whealth",
      "query",
      [opts]
    );
  }
};

WHealth.prototype.queryAggregated = function (opts, onSuccess, onError) {
  if (opts.dataType == "calories.active") {
    //get basal average
    navigator.whealth.queryAggregated(
      {
        dataType: "calories.basal",
        endDate: opts.endDate,
        startDate: opts.startDate,
      },
      function (data) {
        var basal_ms = data.value / (opts.endDate - opts.startDate);
        //now get the total
        opts.dataType = "calories";
        navigator.whealth.queryAggregated(
          opts,
          function (retval) {
            //and remove the basal
            retval.value -=
              basal_ms *
              (retval.endDate.getTime() - retval.startDate.getTime());
            //although it shouldn't happen....
            if (retval.value < 0) retval.value = 0;
            onSuccess(retval);
          },
          onError
        );
      },
      onError
    );
  } else {
    if (typeof opts.startDate == "object")
      opts.startDate = opts.startDate.getTime();
    if (typeof opts.endDate == "object") opts.endDate = opts.endDate.getTime();
    exec(
      function (data) {
        //reconvert the dates back to Date objects
        if (Object.prototype.toString.call(data) === "[object Array]") {
          //it's an array
          for (var i = 0; i < data.length; i++) {
            data[i].startDate = new Date(data[i].startDate);
            data[i].endDate = new Date(data[i].endDate);
          }
        } else {
          data.startDate = new Date(data.startDate);
          data.endDate = new Date(data.endDate);
        }

        // if nutrition, add water
        if (opts.dataType == "nutrition") {
          opts.dataType = "nutrition.water";
          navigator.whealth.queryAggregated(
            opts,
            function (water) {
              data.value["nutrition.water"] = water.value;
              onSuccess(data);
            },
            onError
          );
        } else {
          onSuccess(data);
        }
      },
      onError,
      "whealth",
      "queryAggregated",
      [opts]
    );
  }
};

WHealth.prototype.store = function (data, onSuccess, onError) {
  if (data.dataType == "calories.basal") {
    onError("basal calories cannot be stored in Android");
    return;
  }
  if (data.dataType == "calories.active") {
    //rename active calories to total calories
    data.dataType = "calories";
  }
  if (data.startDate && typeof data.startDate == "object")
    data.startDate = data.startDate.getTime();
  if (data.endDate && typeof data.endDate == "object")
    data.endDate = data.endDate.getTime();
  if (data.dataType == "activity") {
    data.value = navigator.whealth.toFitActivity(data.value);
  }
  exec(onSuccess, onError, "whealth", "store", [data]);
};

WHealth.prototype.delete = function (data, onSuccess, onError) {
  if (data.dataType == "calories.basal") {
    onError("basal calories cannot be deleted in Android");
    return;
  }
  if (data.dataType == "calories.active") {
    //rename active calories to total calories
    data.dataType = "calories";
  }
  if (data.startDate && typeof data.startDate == "object")
    data.startDate = data.startDate.getTime();
  if (data.endDate && typeof data.endDate == "object")
    data.endDate = data.endDate.getTime();
  if (data.dataType == "activity") {
    data.value = navigator.whealth.toFitActivity(data.value);
  }
  exec(onSuccess, onError, "whealth", "delete", [data]);
};

WHealth.prototype.toFitActivity = function (act) {
  const acts = {
    supported: {
      core_training: "strength_training",
      flexibility: "gymnastics",
      stairs: "stair_climbing",
      "wheelchair.walkpace": "wheelchair",
      "wheelchair.runpace": "wheelchair",
      "sleep.inBed": "sleep.awake",
    },
    unsupported: {
      archery: 1,
      barre: 1,
      bowling: 1,
      fishing: 1,
      functional_strength: 1,
      hunting: 1,
      lacrosse: 1,
      mixed_metabolic_cardio: 1,
      paddle_sports: 1,
      play: 1,
      preparation_and_recovery: 1,
      snow_sports: 1,
      softball: 1,
      water_fitness: 1,
      water_sports: 1,
      wrestling: 1,
    },
  };
  var activity = acts.supported[act];
  if (!activity) {
    activity = acts.unsupported[act];
    if (activity) {
      activity = "other";
    } else {
      activity = act;
    }
  }
  return activity;
};

cordova.addConstructor(function () {
  navigator.whealth = new WHealth();
  return navigator.whealth;
});
