package org.apache.cordova.health;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.health.connect.client.HealthConnectClient;
import androidx.health.connect.client.aggregate.AggregateMetric;
import androidx.health.connect.client.aggregate.AggregationResult;
import androidx.health.connect.client.aggregate.AggregationResultGroupedByDuration;
import androidx.health.connect.client.aggregate.AggregationResultGroupedByPeriod;
import androidx.health.connect.client.request.AggregateGroupByDurationRequest;
import androidx.health.connect.client.request.AggregateGroupByPeriodRequest;
import androidx.health.connect.client.request.AggregateRequest;
import androidx.health.connect.client.request.ReadRecordsRequest;
import androidx.health.connect.client.PermissionController;
import androidx.health.connect.client.permission.HealthPermission;
import androidx.health.connect.client.records.StepsRecord;
import androidx.health.connect.client.records.metadata.DataOrigin;
import androidx.health.connect.client.response.ReadRecordsResponse;
import androidx.health.connect.client.time.TimeRangeFilter;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/**
 * Health plugin Android code.
 * MIT licensed.
 */
public class HealthPlugin extends CordovaPlugin {
  // logger tag
  private static final String TAG = "cordova-plugin-health";

  // calling activity
  private CordovaInterface cordova;

  // instance of the call back when requesting or checking authorisation
  private CallbackContext authReqCallbackCtx;
  private static final int REQUEST_DYN_PERMS = 2;

  private final HashSet<String> authReadTypes = new HashSet<>();
  private final HashSet<String> authReadWriteTypes = new HashSet<>();


  private HealthConnectClient healthClient;
  private ActivityResultLauncher requestPermissionLauncher;

  private Set<String> requestedPermissions = new HashSet<>();

  public HealthPlugin() {
  }

  void onNewPermissionResult(Set<String> granted) {
    if (granted.size() == 0) {
      return;
    }
    if (granted.containsAll(requestedPermissions)) {
      // Permissions successfully granted
      authReqCallbackCtx.sendPluginResult(new PluginResult(PluginResult.Status.OK, true));
    } else {
      // Lack of required permissions
      authReqCallbackCtx.error("Not all permissions not granted");
    }
  }

  // general initialization
  @Override
  public void initialize(CordovaInterface cordova, CordovaWebView webView) {

    super.initialize(cordova, webView);
    this.cordova = cordova;
    healthClient = HealthConnectClient.getOrCreate(cordova.getContext());
    ActivityResultContract requestPermissionActivityContract  =  PermissionController.createRequestPermissionResultContract();
    requestPermissionLauncher = this.cordova.getActivity().registerForActivityResult(requestPermissionActivityContract, new ActivityResultCallback<Set<String>>() {
      @Override
      public void onActivityResult(Set<String> granted) {
        onNewPermissionResult(granted);
      }
    });
  }

  // called once custom data types have been created
  // asks for dynamic permissions on Android 6 and more
  private void requestDynamicPermissions() {
    requestedPermissions = new HashSet<>();
    // see https://developers.google.com/fit/android/authorization#data_types_that_need_android_permissions
    if (authReadTypes.contains("steps")) {
      requestedPermissions.add(HealthPermission.getReadPermission(Reflection.getOrCreateKotlinClass(StepsRecord.class)));
    }

//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//      // new Android 10 permissions
//      if (authReadTypes.contains("steps") || authReadTypes.contains("activity")
//        || authReadWriteTypes.contains("steps") || authReadWriteTypes.contains("activity")
//        || authReadWriteTypes.contains("calories") || authReadWriteTypes.contains("calories.active")) {
//        if (!cordova.hasPermission(Manifest.permission.ACTIVITY_RECOGNITION))
//          dynPerms.add(Manifest.permission.ACTIVITY_RECOGNITION);
//      }
//    }
//    if (authReadTypes.contains("distance") || authReadWriteTypes.contains("distance")) {
//      if (!cordova.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION))
//        dynPerms.add(Manifest.permission.ACCESS_FINE_LOCATION);
//    }
//    if (authReadTypes.contains("heart_rate")) {
//      if (!cordova.hasPermission(Manifest.permission.BODY_SENSORS))
//        dynPerms.add(Manifest.permission.BODY_SENSORS);
//    }
//    if (dynPerms.isEmpty()) {
//      // no dynamic permissions to ask
//      accessGoogleFit();
//    } else {
//      if (authAutoresolve) {
//        cordova.requestPermissions(this, REQUEST_DYN_PERMS, dynPerms.toArray(new String[dynPerms.size()]));
//        // the request results will be taken care of by onRequestPermissionResult()
//      } else {
//        // if should not autoresolve, and there are dynamic permissions needed, send a false
//        authReqCallbackCtx.sendPluginResult(new PluginResult(PluginResult.Status.OK, false));
//      }
//    }

    healthClient.getPermissionController().getGrantedPermissions(
      new Continuation<Set<String>>() {
        @NonNull
        @Override
        public CoroutineContext getContext() {
          return EmptyCoroutineContext.INSTANCE;
        }

        @Override
        public void resumeWith(@NonNull Object o) {
          if (o instanceof Set<?>) {
            Set<String> granted = (Set<String>) o;
            if (granted.containsAll(requestedPermissions)) {
              // Permissions successfully granted
              authReqCallbackCtx.sendPluginResult(new PluginResult(PluginResult.Status.OK, true));
            } else {
              // Lack of required permissions
              requestPermissionLauncher.launch(requestedPermissions);
            }
          }
        }
      });
  }

  // called when the dynamic permissions are asked
  @Override
  public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) throws JSONException {
    if (requestCode == REQUEST_DYN_PERMS) {
      for (int i = 0; i < grantResults.length; i++) {
        if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
          String errmsg = "Permission denied ";
          for (String perm : permissions) {
            errmsg += " " + perm;
          }
          authReqCallbackCtx.error("Permission denied: " + permissions[i]);
          return;
        }
      }
      // all dynamic permissions accepted!
      Log.i(TAG, "All dynamic permissions accepted");
    }
  }

  /**
   * The "execute" method that Cordova calls whenever the plugin is used from the JavaScript
   *
   * @param action          The action to execute.
   * @param args            The exec() arguments.
   * @param callbackContext The callback context used when calling back into JavaScript.
   * @return
   * @throws JSONException
   */
  @Override
  public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {

    if ("isAvailable".equals(action)) {
      isAvailable(callbackContext);
      return true;
    } else if ("promptInstallFit".equals(action)) {
      promptInstall(callbackContext);
      return true;
    } else if ("requestAuthorization".equals(action)) {
      cordova.getThreadPool().execute(new Runnable() {
        @Override
        public void run() {
          try {
            checkAuthorization(args, callbackContext); // with autoresolve
          } catch (Exception ex) {
            callbackContext.error(ex.getMessage());
          }
        }
      });
      return true;
    } else if ("checkAuthorization".equals(action)) {
      cordova.getThreadPool().execute(new Runnable() {
        @Override
        public void run() {
          try {
            checkAuthorization(args, callbackContext); // without autoresolve
          } catch (Exception ex) {
            callbackContext.error(ex.getMessage());
          }
        }
      });
      return true;
    } else if ("isAuthorized".equals(action)) {
      cordova.getThreadPool().execute(new Runnable() {
        @Override
        public void run() {
          try {
            checkAuthorization(args, callbackContext); // without autoresolve
          } catch (Exception ex) {
            callbackContext.error(ex.getMessage());
          }
        }
      });
      return true;
    } else if ("query".equals(action)) {
      cordova.getThreadPool().execute(new Runnable() {
        @Override
        public void run() {
          try {
            query(args, callbackContext);
          } catch (Exception ex) {
            callbackContext.error(ex.getMessage());
          }
        }
      });
      return true;
    } else if ("queryAggregated".equals(action)) {
      cordova.getThreadPool().execute(new Runnable() {
        @Override
        public void run() {
          try {
            queryAggregated(args, callbackContext);
          } catch (Exception ex) {
            callbackContext.error(ex.getMessage());
          }
        }
      });
      return true;
    } else if ("store".equals(action)) {
      cordova.getThreadPool().execute(new Runnable() {
        @Override
        public void run() {
          try {
            store(args, callbackContext);
          } catch (Exception ex) {
            callbackContext.error(ex.getMessage());
          }
        }
      });
      return true;
    } else if ("delete".equals(action)) {
      cordova.getThreadPool().execute(new Runnable() {
        @Override
        public void run() {
          try {
            delete(args, callbackContext);
          } catch (Exception ex) {
            callbackContext.error(ex.getMessage());
          }
        }
      });
      return true;
    }

    return false;
  }

  // detects if a) Google APIs are available, b) Google Fit is actually installed
  private void isAvailable(final CallbackContext callbackContext) {
    // first check that the Google APIs are available
//    GoogleApiAvailability gapi = GoogleApiAvailability.getInstance();
//    int apiresult = gapi.isGooglePlayServicesAvailable(this.cordova.getActivity());
//    if (apiresult == ConnectionResult.SUCCESS) {
      // then check that Google Fit is actually installed
      int status = HealthConnectClient.getSdkStatus(this.cordova.getActivity().getApplicationContext());
      PluginResult.Status pluginResult = PluginResult.Status.OK;
      if (status == HealthConnectClient.SDK_AVAILABLE) {
        Log.d(TAG, "HealthConnectClient available");
        PluginResult result;
        result = new PluginResult(pluginResult, true);
        callbackContext.sendPluginResult(result);
        return;
      } else if (status == HealthConnectClient.SDK_UNAVAILABLE) {
        Log.d(TAG, "HealthConnectClient unavailable");
        pluginResult = PluginResult.Status.ERROR;
      } else if (status == HealthConnectClient.SDK_UNAVAILABLE_PROVIDER_UPDATE_REQUIRED) {
        Log.d(TAG, "HealthConnectClient unavailable provider update required");
        pluginResult = PluginResult.Status.ERROR;
      }
    PluginResult result;
    result = new PluginResult(pluginResult, false);
    callbackContext.sendPluginResult(result);
  }

  // prompts to install GooglePlayServices if not available then Google Fit if not available
  private void promptInstall(final CallbackContext callbackContext) {
//    GoogleApiAvailability gapi = GoogleApiAvailability.getInstance();
//    int apiresult = gapi.isGooglePlayServicesAvailable(this.cordova.getActivity());
//    if (apiresult != ConnectionResult.SUCCESS) {
//      if (gapi.isUserResolvableError(apiresult)) {
//        // show the dialog, but no action is performed afterwards
//        gapi.showErrorDialogFragment(this.cordova.getActivity(), apiresult, 1000);
//      }
//    } else {
      // check that Google Fit is actually installed
      PackageManager pm = cordova.getActivity().getApplicationContext().getPackageManager();
      try {
        pm.getPackageInfo("com.google.android.apps.healthdata", PackageManager.GET_ACTIVITIES);
      } catch (PackageManager.NameNotFoundException e) {
        // show popup for downloading app
        // code from http://stackoverflow.com/questions/11753000/how-to-open-the-google-play-store-directly-from-my-android-application
        try {
          cordova.getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.apps.healthdata")));
        } catch (android.content.ActivityNotFoundException anfe) {
          cordova.getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.healthdata")));
        }
      }
//    }
//    callbackContext.success();
  }

  // check if the app is authorised to use Google fitness APIs
  // if autoresolve is set, it will try to get authorisation from the user
  // also includes some OS dynamic permissions if needed (eg location)
  private void checkAuthorization(final JSONArray args, final CallbackContext callbackContext) throws JSONException {
    this.cordova.setActivityResultCallback(this);
    authReqCallbackCtx = callbackContext;

    // build the read and read-write sets
    authReadTypes.clear();
    authReadWriteTypes.clear();

    for (int i = 0; i < args.length(); i++) {
      Object object = args.get(i);
      if (object instanceof JSONObject) {
        JSONObject readWriteObj = (JSONObject) object;
        if (readWriteObj.has("read")) {
          JSONArray readArray = readWriteObj.getJSONArray("read");
          for (int j = 0; j < readArray.length(); j++) {
            authReadTypes.add(readArray.getString(j));
          }
        }
        if (readWriteObj.has("write")) {
          JSONArray writeArray = readWriteObj.getJSONArray("write");
          for (int j = 0; j < writeArray.length(); j++) {
            authReadWriteTypes.add(writeArray.getString(j));
          }
        }
      } else if (object instanceof String) {
        authReadWriteTypes.add(String.valueOf(object));
      }
    }
    authReadTypes.removeAll(authReadWriteTypes);

    // now ask for dynamic permissiions
    requestDynamicPermissions();
  }

  private void queryRawData(final CallbackContext callbackContext, final String datatype, final long st, final long et, final boolean filtered, final int limit,
                            final Set<DataOrigin> dataOrigins ) {

    final KClass dt = HealthDataConvertor.getDataType(datatype);
    final Instant startInstant = Instant.ofEpochMilli(st);
    final Instant endInstant = Instant.ofEpochMilli(et);

    ReadRecordsRequest readRecordsRequest = new ReadRecordsRequest(
      dt,
      TimeRangeFilter.between(startInstant, endInstant),
      dataOrigins,
      true,
      limit,
      null

    );
    healthClient.readRecords(readRecordsRequest,
      new Continuation<Object>() {
        @NonNull
        @Override
        public CoroutineContext getContext() {
          return EmptyCoroutineContext.INSTANCE;
        }

        @Override
        public void resumeWith(@NonNull Object o) {
          if (o instanceof ReadRecordsResponse) {
            ReadRecordsResponse readResponse = (ReadRecordsResponse) o;
            List<androidx.health.connect.client.records.Record> records = readResponse.getRecords();
            JSONArray resultSet = new JSONArray();
            for (androidx.health.connect.client.records.Record record : records) {
              JSONObject data = HealthDataConvertor.parseRecordForType(record, datatype, filtered);
              if (data != null) {
                resultSet.put(data);
              }
            }
            callbackContext.success(resultSet);
          }
        }});
  }

  void queryAggregatedData(final CallbackContext callbackContext, final String datatype, final long st, final long et, final boolean filtered,
                           final Set<DataOrigin> dataOrigins) {
    final Set<AggregateMetric<?>> metrics = HealthDataConvertor.getAggregateMetricsForDataType(datatype);
    final Instant startInstant = Instant.ofEpochMilli(st);
    final Instant endInstant = Instant.ofEpochMilli(et);

    AggregateRequest aggregateRequest = new AggregateRequest(
      metrics,
      TimeRangeFilter.between(startInstant, endInstant),
      dataOrigins
    );


    healthClient.aggregate(aggregateRequest,
      new Continuation<Object>() {
        @NonNull
        @Override
        public CoroutineContext getContext() {
          return EmptyCoroutineContext.INSTANCE;
        }

        @Override
        public void resumeWith(@NonNull Object o) {
          if (o instanceof AggregationResult) {
            AggregationResult result = (AggregationResult) o;
            JSONArray resultSet = new JSONArray();
            resultSet.put(HealthDataConvertor.parseAggregationResultForType(result, datatype, filtered));
            callbackContext.success(resultSet);
          }
        }});

  }

  void queryAggregatedData(final CallbackContext callbackContext, final String datatype, final long st, final long et, final boolean filtered,
                           final Set<DataOrigin> dataOrigins, String bucketType) {
    final Set<AggregateMetric<?>> metrics = HealthDataConvertor.getAggregateMetricsForDataType(datatype);
    final Instant startInstant = Instant.ofEpochMilli(st);
    final Instant endInstant = Instant.ofEpochMilli(et);

    if (bucketType.equalsIgnoreCase("minute") || bucketType.equalsIgnoreCase("hour")) {
      Duration duration = Duration.ofHours(1);
      if (bucketType.equalsIgnoreCase("minute")) {
        duration = Duration.ofMinutes(1);
      }

      AggregateGroupByDurationRequest aggregateRequest = new AggregateGroupByDurationRequest(
        (Set<? extends AggregateMetric<?>>) metrics,
        TimeRangeFilter.between(startInstant, endInstant),
        duration,
        dataOrigins
      );

      healthClient.aggregateGroupByDuration(aggregateRequest,
        new Continuation<Object>() {
          @NonNull
          @Override
          public CoroutineContext getContext() {
            return EmptyCoroutineContext.INSTANCE;
          }

          @Override
          public void resumeWith(@NonNull Object o) {
            if (o instanceof List) {
              List<AggregationResultGroupedByDuration> results = (List<AggregationResultGroupedByDuration>) o;
              JSONArray resultSet = new JSONArray();
              for (AggregationResultGroupedByDuration aggregationResultGroupedByDuration : results) {
                JSONObject obj = HealthDataConvertor.parseAggregationResultForType(aggregationResultGroupedByDuration.getResult(), datatype, filtered);
                try {
                  obj.put("startDate", aggregationResultGroupedByDuration.getStartTime().toEpochMilli());
                  obj.put("endDate", aggregationResultGroupedByDuration.getEndTime().toEpochMilli());
                } catch (JSONException e) {
                  e.printStackTrace();
                }
                resultSet.put(obj);
              }

              callbackContext.success(resultSet);
            }
          }});

    } else if (bucketType.equalsIgnoreCase("day") || bucketType.equalsIgnoreCase("week") || bucketType.equalsIgnoreCase("month") || bucketType.equalsIgnoreCase("year")) {
      Period period = Period.ofDays(1);
      if (bucketType.equalsIgnoreCase("week")) {
        period = Period.ofWeeks(1);
      } else if (bucketType.equalsIgnoreCase("month")) {
        period = Period.ofMonths(1);
      } else if (bucketType.equalsIgnoreCase("year")) {
        period = Period.ofYears(1);
      }
      LocalDateTime startLocalTime =
        LocalDateTime.ofInstant(Instant.ofEpochMilli(st), ZoneId.systemDefault());
      LocalDateTime endLocalTime =
        LocalDateTime.ofInstant(Instant.ofEpochMilli(et), ZoneId.systemDefault());
      AggregateGroupByPeriodRequest aggregateRequest = new AggregateGroupByPeriodRequest(
        (Set<? extends AggregateMetric<?>>) metrics,
        TimeRangeFilter.between(startLocalTime, endLocalTime),
        period,
        dataOrigins
      );

      healthClient.aggregateGroupByPeriod(aggregateRequest,
        new Continuation<Object>() {
          @NonNull
          @Override
          public CoroutineContext getContext() {
            return EmptyCoroutineContext.INSTANCE;
          }

          @Override
          public void resumeWith(@NonNull Object o) {
            if (o instanceof List) {
              List<AggregationResultGroupedByPeriod> results = (List<AggregationResultGroupedByPeriod>) o;
              JSONArray resultSet = new JSONArray();
              for (AggregationResultGroupedByPeriod aggregationResultGroupedByDuration : results) {
                JSONObject obj = HealthDataConvertor.parseAggregationResultForType(aggregationResultGroupedByDuration.getResult(), datatype, filtered);
                try {
                  obj.put("startDate", aggregationResultGroupedByDuration.getStartTime());
                  obj.put("endDate", aggregationResultGroupedByDuration.getEndTime());
                } catch (JSONException e) {
                  e.printStackTrace();
                }
                resultSet.put(obj);
              }

              callbackContext.success(resultSet);
            }
          }});
    }

  }



  // queries for datapoints
  private void query(final JSONArray args, final CallbackContext callbackContext) throws Exception {
    if (!args.getJSONObject(0).has("startDate")) {
      callbackContext.error("Missing argument startDate");
      return;
    }
    long st = args.getJSONObject(0).getLong("startDate");
    if (!args.getJSONObject(0).has("endDate")) {
      callbackContext.error("Missing argument endDate");
      return;
    }
    long et = args.getJSONObject(0).getLong("endDate");
    if (!args.getJSONObject(0).has("dataType")) {
      callbackContext.error("Missing argument dataType");
      return;
    }
    String datatype = args.getJSONObject(0).getString("dataType");
    boolean filtered = args.getJSONObject(0).has("filtered") && args.getJSONObject(0).getBoolean("filtered");

    boolean includeCalsAndDist = false;
    if (args.getJSONObject(0).has("includeCalsAndDist")) {
      includeCalsAndDist = args.getJSONObject(0).getBoolean("includeCalsAndDist");
    }

    Integer limit = 1000;
    Set<DataOrigin> dataOrigins = new HashSet<>();
    if (args.getJSONObject(0).has("limit")) {
      limit = args.getJSONObject(0).getInt("limit");
    }

    if (HealthDataConvertor.getDataType(datatype) != null) {
      queryRawData(callbackContext, datatype, st, et, filtered, limit, dataOrigins);
      return;
    }

    if (HealthDataConvertor.getAggregateMetricsForDataType(datatype) != null ) {
      queryAggregatedData(callbackContext, datatype, st, et, filtered, dataOrigins);
      return;
    }

    callbackContext.error("Datatype " + datatype + " not supported");
  }

  // queries and aggregates data
  private void queryAggregated(final JSONArray args, final CallbackContext callbackContext) throws JSONException {
    if (!args.getJSONObject(0).has("startDate")) {
      callbackContext.error("Missing argument startDate");
      return;
    }
    long st = args.getJSONObject(0).getLong("startDate");
    if (!args.getJSONObject(0).has("endDate")) {
      callbackContext.error("Missing argument endDate");
      return;
    }
    long et = args.getJSONObject(0).getLong("endDate");
    long _et = et; // keep track of the original end time, needed for basal calories
    if (!args.getJSONObject(0).has("dataType")) {
      callbackContext.error("Missing argument dataType");
      return;
    }
    String datatype = args.getJSONObject(0).getString("dataType");

    boolean includeCalsAndDist = false;
    if (args.getJSONObject(0).has("includeCalsAndDist")) {
      includeCalsAndDist = args.getJSONObject(0).getBoolean("includeCalsAndDist");
    }
    boolean hasbucket = args.getJSONObject(0).has("bucket");
    boolean customBucket = false;
    String bucketType = "";

    if (hasbucket) {
      bucketType = args.getJSONObject(0).getString("bucket");
      queryAggregatedData(callbackContext, datatype, st, et, false, new HashSet<DataOrigin>(), bucketType);
    } else {
      queryAggregatedData(callbackContext, datatype, st, et, false, new HashSet<DataOrigin>());
    }
  }


  // stores a data point
  private void store(final JSONArray args, final CallbackContext callbackContext) throws JSONException {
    if (!args.getJSONObject(0).has("startDate")) {
      callbackContext.error("Missing argument startDate");
      return;
    }
    long st = args.getJSONObject(0).getLong("startDate");
    if (!args.getJSONObject(0).has("endDate")) {
      callbackContext.error("Missing argument endDate");
      return;
    }
    long et = args.getJSONObject(0).getLong("endDate");
    if (!args.getJSONObject(0).has("dataType")) {
      callbackContext.error("Missing argument dataType");
      return;
    }
    String datatype = args.getJSONObject(0).getString("dataType");
    if (!args.getJSONObject(0).has("value")) {
      callbackContext.error("Missing argument value");
      return;
    }

    String sourceBundleId = cordova.getActivity().getApplicationContext().getPackageName();
    if (args.getJSONObject(0).has("sourceBundleId")) {
      sourceBundleId = args.getJSONObject(0).getString("sourceBundleId");
    }

    final KClass dt = HealthDataConvertor.getDataType(datatype);

    if (dt == null) {
      callbackContext.error("Datatype " + datatype + " not supported");
      return;
    }
  }

  // deletes data points in a given time window
  private void delete(final JSONArray args, final CallbackContext callbackContext) throws JSONException {
    if (!args.getJSONObject(0).has("startDate")) {
      callbackContext.error("Missing argument startDate");
      return;
    }
    final long st = args.getJSONObject(0).getLong("startDate");
    if (!args.getJSONObject(0).has("endDate")) {
      callbackContext.error("Missing argument endDate");
      return;
    }
    final long et = args.getJSONObject(0).getLong("endDate");
    if (!args.getJSONObject(0).has("dataType")) {
      callbackContext.error("Missing argument dataType");
      return;
    }
    final String datatype = args.getJSONObject(0).getString("dataType");

    final KClass dt = HealthDataConvertor.getDataType(datatype);
    if (dt == null) {
      callbackContext.error("Datatype " + datatype + " not supported");
      return;
    }

  }
}
