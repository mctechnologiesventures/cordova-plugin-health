package org.apache.cordova.health;

import androidx.annotation.NonNull;
import androidx.health.connect.client.aggregate.AggregateMetric;
import androidx.health.connect.client.aggregate.AggregationResult;
import androidx.health.connect.client.records.BloodGlucoseRecord;
import androidx.health.connect.client.records.BloodPressureRecord;
import androidx.health.connect.client.records.BodyFatRecord;
import androidx.health.connect.client.records.DistanceRecord;
import androidx.health.connect.client.records.ExerciseSessionRecord;
import androidx.health.connect.client.records.HeartRateRecord;
import androidx.health.connect.client.records.HeightRecord;
import androidx.health.connect.client.records.HydrationRecord;
import androidx.health.connect.client.records.NutritionRecord;
import androidx.health.connect.client.records.OxygenSaturationRecord;
import androidx.health.connect.client.records.Record;
import androidx.health.connect.client.records.SleepSessionRecord;
import androidx.health.connect.client.records.StepsRecord;
import androidx.health.connect.client.records.TotalCaloriesBurnedRecord;
import androidx.health.connect.client.records.WeightRecord;
import androidx.health.connect.client.records.metadata.DataOrigin;
import androidx.health.connect.client.records.metadata.Device;
import androidx.health.connect.client.records.metadata.Metadata;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

public class HealthDataConvertor {

  public static Map<String, KClass> datatypes = new HashMap<String, KClass>();

  static {
    datatypes.put("steps", Reflection.getOrCreateKotlinClass(StepsRecord.class));
    datatypes.put("activity", Reflection.getOrCreateKotlinClass(ExerciseSessionRecord.class));
    datatypes.put("calories", Reflection.getOrCreateKotlinClass(TotalCaloriesBurnedRecord.class));
    datatypes.put("calories.basal", Reflection.getOrCreateKotlinClass(TotalCaloriesBurnedRecord.class));
    datatypes.put("height", Reflection.getOrCreateKotlinClass(HeightRecord.class));
    datatypes.put("weight", Reflection.getOrCreateKotlinClass(WeightRecord.class));
    datatypes.put("heart_rate", Reflection.getOrCreateKotlinClass(HeartRateRecord.class));
    datatypes.put("fat_percentage", Reflection.getOrCreateKotlinClass(BodyFatRecord.class));
    datatypes.put("distance", Reflection.getOrCreateKotlinClass(DistanceRecord.class));
    datatypes.put("oxygen_saturation", Reflection.getOrCreateKotlinClass(OxygenSaturationRecord.class));
    datatypes.put("blood_glucose", Reflection.getOrCreateKotlinClass(BloodGlucoseRecord.class));
    datatypes.put("blood_pressure", Reflection.getOrCreateKotlinClass(BloodPressureRecord.class));
    datatypes.put("sleep", Reflection.getOrCreateKotlinClass(SleepSessionRecord.class));
    datatypes.put("nutrition", Reflection.getOrCreateKotlinClass(NutritionRecord.class));
    datatypes.put("nutrition.water", Reflection.getOrCreateKotlinClass(HydrationRecord.class));
  }

  public static Set<AggregateMetric<?>> getAggregateMetricsForDataType(String dataType) {
    switch (dataType) {
      case "activity":
        return new HashSet<>(Arrays.asList(
        ExerciseSessionRecord.EXERCISE_DURATION_TOTAL,
          StepsRecord.COUNT_TOTAL,
          DistanceRecord.DISTANCE_TOTAL,
          TotalCaloriesBurnedRecord.ENERGY_TOTAL
        ));
    }
    return null;
  }

  public static KClass getDataType(String dataType) {
    return datatypes.get(dataType);
  }

  public static Map<AggregateMetric<?>, String> aggregateMetricMap = new HashMap<AggregateMetric<?>, String>();

  static {
    aggregateMetricMap.put(StepsRecord.COUNT_TOTAL, "value");
    aggregateMetricMap.put(ExerciseSessionRecord.EXERCISE_DURATION_TOTAL, "activity");
    aggregateMetricMap.put(DistanceRecord.DISTANCE_TOTAL, "distance");
    aggregateMetricMap.put(TotalCaloriesBurnedRecord.ENERGY_TOTAL, "calories");
  }

  public static Map<Integer, String> workoutTypeMap = new HashMap<Integer, String>();
  static {
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_BADMINTON, "badminton");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_BASEBALL, "baseball");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_BASKETBALL, "baseball");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_BIKING, "biking");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_BIKING_STATIONARY, "biking.stationary");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_BOOT_CAMP, "boot");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_BOXING, "boxing");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_CALISTHENICS, "calisthenics");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_CRICKET, "cricket");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_DANCING, "dancing");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_ELLIPTICAL, "elliptical");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_EXERCISE_CLASS, "exercise.class");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_FENCING, "fencing");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_FOOTBALL_AMERICAN, "football.american");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_FOOTBALL_AUSTRALIAN, "football.australian");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_FRISBEE_DISC, "frisbee");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_GOLF, "golf");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_GUIDED_BREATHING, "meditation");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_GYMNASTICS, "gymnastics");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_HANDBALL, "handball");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_HIGH_INTENSITY_INTERVAL_TRAINING, "interval_training.high_intensity");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_HIKING, "hiking");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_ICE_HOCKEY, "hockey");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_ICE_SKATING, "skating");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_MARTIAL_ARTS, "martial_arts");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_OTHER_WORKOUT, "other");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_PADDLING, "paddle_sports");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_PARAGLIDING, "paragliding");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_PILATES, "pilates");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_RACQUETBALL, "racquetball");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_ROCK_CLIMBING, "rock_climbing");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_ROLLER_HOCKEY, "roller_hockey");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_ROWING, "rowing");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_ROWING_MACHINE, "rowing");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_RUGBY, "rugby");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_RUNNING, "running");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_RUNNING_TREADMILL, "running");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_SAILING, "sailing");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_SCUBA_DIVING, "scuba_diving");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_SKATING, "skating");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_SKIING, "skiing");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_SNOWBOARDING, "snowboarding");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_SNOWSHOEING, "snowshoeing");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_SOCCER, "football.soccer");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_SOFTBALL, "softball");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_SQUASH, "squash");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_STAIR_CLIMBING, "stair_climbing");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_STAIR_CLIMBING_MACHINE, "stair_climbing.machine");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_STRENGTH_TRAINING, "strength_training");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_STRETCHING, "stretching");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_SURFING, "surfing");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_SWIMMING_OPEN_WATER, "swimming.open_water");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_SWIMMING_POOL, "swimming.pool");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_TABLE_TENNIS, "table_tennis");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_TENNIS, "tennis");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_VOLLEYBALL, "volleyball");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_WALKING, "walking");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_WATER_POLO, "water_polo");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_WEIGHTLIFTING, "weightlifting");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_WHEELCHAIR, "wheelchair");
    workoutTypeMap.put(ExerciseSessionRecord.EXERCISE_TYPE_YOGA, "yoga");
  };

  public static String getWorkoutType(int exerciseType) {
    return workoutTypeMap.get(exerciseType);
  }

  public static int getExerciseType(String workoutType) {
    for (Map.Entry<Integer, String> entry : workoutTypeMap.entrySet()) {
      if (entry.getValue().equals(workoutType)) {
        return entry.getKey();
      }
    }
    return -1;
  }

  public static JSONObject parseAggregationResultForType(AggregationResult result, String dataType, boolean filtered) {
    JSONObject obj = new JSONObject();
    try {
      Set<AggregateMetric<?>> metrics = getAggregateMetricsForDataType(dataType);
      if (metrics == null) {
        return null;
      }
      if (metrics.size() > 1) {
        JSONObject value = new JSONObject();
        for (AggregateMetric metric : metrics) {
          if (result.get(metric) != null) {
            value.put(aggregateMetricMap.get(metric), result.get(metric));
          }
        }
        obj.put("value", value);
      }
      if (metrics.size() == 1) {
        obj.put("value", result.get(metrics.iterator().next()));
      }
    } catch (JSONException e) {
     e.printStackTrace();
    }
    return obj;
  }
  public static JSONObject parseRecordForType(Record record, String dataType, boolean filtered) {
    JSONObject obj = new JSONObject();
    try {
      int recordingMethod =  record.getMetadata().getRecordingMethod();

      if (filtered && recordingMethod == Metadata.RECORDING_METHOD_MANUAL_ENTRY) {
        return null;
      }
      String sourceName = record.getMetadata().getDataOrigin().getPackageName();
      if (record.getMetadata().getDevice() != null) {
        sourceName = sourceName + " " + record.getMetadata().getDevice().toString();
      }
      obj.put("sourceName", sourceName);

      switch (dataType) {
        case "steps":
          StepsRecord stepsRecord = (StepsRecord) record;
          obj.put("value", stepsRecord.getCount());
          obj.put("unit", "count");
          obj.put("startDate", stepsRecord.getStartTime().toEpochMilli());
          obj.put("endDate", stepsRecord.getEndTime().toEpochMilli());
          break;
        case "calories":
        case "calories.basal":
          TotalCaloriesBurnedRecord caloriesRecord = (TotalCaloriesBurnedRecord) record;
          obj.put("value", caloriesRecord.getEnergy().getCalories());
          obj.put("unit", "kcal");
          obj.put("startDate", caloriesRecord.getStartTime().toEpochMilli());
          obj.put("endDate", caloriesRecord.getEndTime().toEpochMilli());
          break;
        case "distance":
          DistanceRecord distanceRecord = (DistanceRecord) record;
          obj.put("value", distanceRecord.getDistance().getMeters());
          obj.put("unit", "m");
          obj.put("startDate", distanceRecord.getStartTime().toEpochMilli());
          obj.put("endDate", distanceRecord.getEndTime().toEpochMilli());
          break;
        case "activity":
          ExerciseSessionRecord exerciseRecord = (ExerciseSessionRecord) record;
          obj.put("unit", "activitySummary");
          Instant startTime = exerciseRecord.getStartTime();
          Instant endTime = exerciseRecord.getEndTime();
          obj.put("startDate", startTime);
          obj.put("endDate", endTime);
          obj.put("duration", (endTime.toEpochMilli() - startTime.toEpochMilli()) / 1000);
          obj.put("value", getWorkoutType(exerciseRecord.getExerciseType()));
          break;
        case "weight":
          WeightRecord weightRecord = (WeightRecord) record;
//            obj.put("value", weightRecord.getWeight().getKg());
          break;
        case "nutrition.water":
          HydrationRecord hydrationRecord = (HydrationRecord) record;
          obj.put("value", hydrationRecord.getVolume().getMilliliters());
          obj.put("startDate", hydrationRecord.getStartTime().toEpochMilli());
          obj.put("endDate", hydrationRecord.getEndTime().toEpochMilli());
          break;
        case "sleep":
          SleepSessionRecord sleepRecord = (SleepSessionRecord) record;
          obj.put("startDate", sleepRecord.getStartTime().toEpochMilli());
          obj.put("endDate", sleepRecord.getEndTime().toEpochMilli());
          break;
        default:
          throw new RuntimeException("Unknown data type: " + dataType);
      }

    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
    return obj;
  }

  public static Record createRecord(String dataType, String value, String packageName, long st, long et) {
    Record newRecord = null;
    Metadata metadata = new Metadata(
      "id",
      new DataOrigin(packageName),
      Instant.now(),
      "",
      0,
      new Device(),
      Metadata.RECORDING_METHOD_MANUAL_ENTRY
      );
    Instant start = Instant.ofEpochMilli(st);
    Instant end = Instant.ofEpochMilli(et);
    if (dataType.equals("steps")) {
      newRecord = new StepsRecord(
        start,
        null,
        end,
        null,
        Integer.parseInt(value),
        metadata
      );
      // TODO: add other types
    }
    return newRecord;
  }
}
