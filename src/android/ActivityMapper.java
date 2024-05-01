package org.apache.cordova.health;

import androidx.health.connect.client.records.ExerciseSessionRecord;

/**
 * Maps activities with exercise type and viceversa
 */
public class ActivityMapper {

    static String activityFromExerciseType(int exType){

        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_BADMINTON){
            return "badminton";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_BASEBALL){
            return "baseball";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_BASKETBALL){
            return "basketball";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_BIKING){
            return "biking";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_BOXING){
            return "boxing";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_BIKING_STATIONARY){
            return "biking.stationary";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_BOOT_CAMP){
            return "bootcamp";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_CALISTHENICS){
            return "calisthenics";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_CRICKET){
            return "cricket";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_DANCING){
            return "dancing";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_ELLIPTICAL){
            return "elliptical";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_EXERCISE_CLASS){
            return "exercise_class";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_FENCING){
            return "fencing";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_FOOTBALL_AMERICAN){
            return "football.american";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_FOOTBALL_AUSTRALIAN){
            return "football.australian";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_FRISBEE_DISC){
            return "frisbee_disc";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_GOLF){
            return "golf";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_GUIDED_BREATHING){
            return "guided_breathing";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_GYMNASTICS){
            return "gymnastics";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_HANDBALL){
            return "handball";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_HIGH_INTENSITY_INTERVAL_TRAINING){
            return "interval_training.high_intensity";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_HIKING){
            return "hiking";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_ICE_HOCKEY){
            return "hockey";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_ICE_SKATING){
            return "ice_skating";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_MARTIAL_ARTS){
            return "martial_arts";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_PADDLING){
            return "paddle_sports";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_PARAGLIDING){
            return "paragliding";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_PILATES){
            return "pilates";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_RACQUETBALL){
            return "racquetball";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_ROCK_CLIMBING){
            return "rock_climbing";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_ROLLER_HOCKEY){
            return "hockey.roller";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_ROWING){
            return "rowing";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_ROWING_MACHINE){
            return "rowing.machine";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_RUGBY){
            return "rugby";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_RUNNING){
            return "running";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_RUNNING_TREADMILL){
            return "running.treadmill";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_SAILING){
            return "sailing";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_SCUBA_DIVING){
            return "scuba_diving";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_SKATING){
            return "skating";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_SKIING){
            return "skiing";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_SNOWBOARDING){
            return "snowboarding";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_SNOWSHOEING){
            return "snowshoeing";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_SOCCER){
            return "football.soccer";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_SOFTBALL){
            return "softball";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_SQUASH){
            return "squash";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_STAIR_CLIMBING){
            return "stair_climbing";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_STAIR_CLIMBING_MACHINE){
            return "stair_climbing.machine";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_STRENGTH_TRAINING){
            return "strength_training";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_STRETCHING){
            return "stretching";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_SURFING){
            return "surfing";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_SWIMMING_OPEN_WATER){
            return "swimming.open_water";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_SWIMMING_POOL){
            return "swimming.pool";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_TABLE_TENNIS){
            return "table_tennis";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_TENNIS){
            return "tennis";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_VOLLEYBALL){
            return "volleyball";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_WALKING){
            return "walking";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_WATER_POLO){
            return "water_polo";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_WEIGHTLIFTING){
            return "weightlifting";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_WHEELCHAIR){
            return "wheelchair";
        }
        if(exType == ExerciseSessionRecord.EXERCISE_TYPE_YOGA){
            return "yoga";
        }

        return "other";
    }
    static int exerciseTypeFromActivity(String activityName) {
        if (activityName.equalsIgnoreCase("other")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_OTHER_WORKOUT;
        }

        if (activityName.equalsIgnoreCase("badminton")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_BADMINTON;
        }
        if (activityName.equalsIgnoreCase("baseball")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_BASEBALL;
        }
        if (activityName.equalsIgnoreCase("basketball")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_BASKETBALL;
        }
        if (activityName.equalsIgnoreCase("biking")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_BIKING;
        }
        if (activityName.equalsIgnoreCase("boxing")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_BOXING;
        }
        if (activityName.equalsIgnoreCase("biking.stationary")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_BIKING_STATIONARY;
        }
        if (activityName.equalsIgnoreCase("bootcamp")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_BOOT_CAMP;
        }
        if (activityName.equalsIgnoreCase("calisthenics")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_CALISTHENICS;
        }
        if (activityName.equalsIgnoreCase("cricket")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_CRICKET;
        }
        if (activityName.equalsIgnoreCase("dancing") ||
                activityName.equalsIgnoreCase("dancing.social") ||
                activityName.equalsIgnoreCase("dancing.cardio")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_DANCING;
        }
        if (activityName.equalsIgnoreCase("elliptical")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_ELLIPTICAL;
        }
        if (activityName.equalsIgnoreCase("exercise_class")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_EXERCISE_CLASS;
        }
        if (activityName.equalsIgnoreCase("fencing")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_FENCING;
        }
        if (activityName.equalsIgnoreCase("football.american")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_FOOTBALL_AMERICAN;
        }
        if (activityName.equalsIgnoreCase("football.australian")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_FOOTBALL_AUSTRALIAN;
        }
        if (activityName.equalsIgnoreCase("frisbee_disc")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_FRISBEE_DISC;
        }
        if (activityName.equalsIgnoreCase("golf")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_GOLF;
        }
        if (activityName.equalsIgnoreCase("guided_breathing")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_GUIDED_BREATHING;
        }
        if (activityName.equalsIgnoreCase("gymnastics") ||
                activityName.equalsIgnoreCase("flexibility")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_GYMNASTICS;
        }
        if (activityName.equalsIgnoreCase("handball")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_HANDBALL;
        }
        if (activityName.equalsIgnoreCase("interval_training.high_intensity")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_HIGH_INTENSITY_INTERVAL_TRAINING;
        }
        if (activityName.equalsIgnoreCase("hiking")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_HIKING;
        }
        if (activityName.equalsIgnoreCase("hockey")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_ICE_HOCKEY;
        }
        if (activityName.equalsIgnoreCase("ice_skating")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_ICE_SKATING;
        }
        if (activityName.equalsIgnoreCase("martial_arts") ||
                activityName.equalsIgnoreCase("kickboxing")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_MARTIAL_ARTS;
        }
        if (activityName.equalsIgnoreCase("paddle_sports")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_PADDLING;
        }
        if (activityName.equalsIgnoreCase("paragliding")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_PARAGLIDING;
        }
        if (activityName.equalsIgnoreCase("pilates")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_PILATES;
        }
        if (activityName.equalsIgnoreCase("racquetball")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_RACQUETBALL;
        }
        if (activityName.equalsIgnoreCase("rock_climbing")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_ROCK_CLIMBING;
        }
        if (activityName.equalsIgnoreCase("hockey.roller")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_ROLLER_HOCKEY;
        }
        if (activityName.equalsIgnoreCase("rowing")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_ROWING;
        }
        if (activityName.equalsIgnoreCase("rowing.machine")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_ROWING_MACHINE;
        }
        if (activityName.equalsIgnoreCase("rugby")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_RUGBY;
        }
        if (activityName.equalsIgnoreCase("running")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_RUNNING;
        }
        if (activityName.equalsIgnoreCase("running.treadmill")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_RUNNING_TREADMILL;
        }
        if (activityName.equalsIgnoreCase("sailing")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_SAILING;
        }
        if (activityName.equalsIgnoreCase("scuba_diving")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_SCUBA_DIVING;
        }
        if (activityName.equalsIgnoreCase("skating")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_SKATING;
        }
        if (activityName.equalsIgnoreCase("skiing") ||
                activityName.equalsIgnoreCase("skiing.cross_country") ||
                activityName.equalsIgnoreCase("skiing.downhill")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_SKIING;
        }
        if (activityName.equalsIgnoreCase("snowboarding")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_SNOWBOARDING;
        }
        if (activityName.equalsIgnoreCase("snowshoeing")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_SNOWSHOEING;
        }
        if (activityName.equalsIgnoreCase("football.soccer")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_SOCCER;
        }
        if (activityName.equalsIgnoreCase("softball")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_SOFTBALL;
        }
        if (activityName.equalsIgnoreCase("squash")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_SQUASH;
        }
        if (activityName.equalsIgnoreCase("stair_climbing")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_STAIR_CLIMBING;
        }
        if (activityName.equalsIgnoreCase("stair_climbing.machine")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_STAIR_CLIMBING_MACHINE;
        }
        if (activityName.equalsIgnoreCase("strength_training") ||
                activityName.equalsIgnoreCase("strength_training.functional") ||
                activityName.equalsIgnoreCase("crossfit") ||
                activityName.equalsIgnoreCase("core_training")
        ) {
            return ExerciseSessionRecord.EXERCISE_TYPE_STRENGTH_TRAINING;
        }
        if (activityName.equalsIgnoreCase("stretching") ||
                activityName.equalsIgnoreCase("preparation_and_recovery")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_STRETCHING;
        }
        if (activityName.equalsIgnoreCase("surfing")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_SURFING;
        }
        if (activityName.equalsIgnoreCase("swimming.open_water")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_SWIMMING_OPEN_WATER;
        }
        if (activityName.equalsIgnoreCase("swimming.pool")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_SWIMMING_POOL;
        }
        if (activityName.equalsIgnoreCase("swimming")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_SWIMMING_POOL;
        }
        if (activityName.equalsIgnoreCase("table_tennis")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_TABLE_TENNIS;
        }
        if (activityName.equalsIgnoreCase("tennis")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_TENNIS;
        }
        if (activityName.equalsIgnoreCase("volleyball")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_VOLLEYBALL;
        }
        if (activityName.equalsIgnoreCase("walking")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_WALKING;
        }
        if (activityName.equalsIgnoreCase("water_polo")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_WATER_POLO;
        }
        if (activityName.equalsIgnoreCase("weightlifting")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_WEIGHTLIFTING;
        }
        if (activityName.equalsIgnoreCase("wheelchair") ||
                activityName.equalsIgnoreCase("wheelchair.walkpace")||
                activityName.equalsIgnoreCase("wheelchair.runpace")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_WHEELCHAIR;
        }
        if (activityName.equalsIgnoreCase("yoga")) {
            return ExerciseSessionRecord.EXERCISE_TYPE_YOGA;
        }

        return ExerciseSessionRecord.EXERCISE_TYPE_OTHER_WORKOUT;
    }
}
