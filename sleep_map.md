
Android sleep stages are listed [here](https://developers.google.com/android/reference/com/google/android/gms/fitness/data/SleepStages).
HealthKit sleep stages are listed [here](https://developer.apple.com/documentation/healthkit/hkcategoryvaluesleepanalysis)

| sleep stage name | HealthConnect equivalent | HealthKit equivalent |
|------------------|--------------------------|----------------------|
|	sleep      	   |    STAGE_TYPE_SLEEPING    | HKCategoryValueSleepAnalysisAsleep or HKCategoryValueSleepAnalysisAsleepCore or HKCategoryValueSleepAnalysisAsleepUnspecified	|
|	sleep.light    |	STAGE_TYPE_LIGHT  | 	HKCategoryValueSleepAnalysisAsleepCore |
|	sleep.deep     |	STAGE_TYPE_DEEP  | 	HKCategoryValueSleepAnalysisAsleepDeep |
|	sleep.rem      |	STAGE_TYPE_REM             | 	HKCategoryValueSleepAnalysisAsleepREM |
|	sleep.inBed    |	STAGE_TYPE_AWAKE_IN_BED   |	HKCategoryValueSleepAnalysisInBed	|
|	sleep.awake    |	STAGE_TYPE_AWAKE	|	HKCategoryValueSleepAnalysisAwake	|
|	sleep.outOfBed |	STAGE_TYPE_OUT_OF_BED	|	HKCategoryValueSleepAnalysisAwake	|
