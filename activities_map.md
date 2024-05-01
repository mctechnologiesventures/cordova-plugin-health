# Activities mapping

"NA" is automatically converted "other". This means that, for example, if you try to save "bootcamp" in an iOS application, you will get back "other" when querying. When two activities can be mapped to the same data type on the native platform, an asterisk indicates the activity that is returned when querying it back. For example, when storing `stretching` on iOS, it is saved as `HKWorkoutActivityTypePreparationAndRecovery`, when querying for acitivities, `preparation_and_recovery` is actually returned. Another example: when you store `wheelchair.walkpace` on Android, you get back `wheelchair` when querying.

* [List of activities in Health Connect](https://developer.android.com/reference/kotlin/androidx/health/connect/client/records/ExerciseSessionRecord)
* [List of activities in HealthKit](https://developer.apple.com/documentation/healthkit/hkworkoutactivitytype?language=objc)

| activity name     | Health Connect equivalent | HealthKit equivalent              |
|-------------------|---------------------------|-----------------------------------|
|	archery	        |	NA	                    | HKWorkoutActivityTypeArchery    |
| badminton         | EXERCISE_TYPE_BADMINTON   | HKWorkoutActivityTypeBadminton    |
|	barre	        |	NA	                    | HKWorkoutActivityTypeBarre	    |
| baseball          | EXERCISE_TYPE_BASEBALL	| HKWorkoutActivityTypeBaseball     |
| basketball	    | EXERCISE_TYPE_BASKETBALL	| HKWorkoutActivityTypeBasketball   |
| biking	        | EXERCISE_TYPE_BIKING	    | HKWorkoutActivityTypeCycling      |
| biking.stationary | EXERCISE_TYPE_BIKING_STATIONARY | HKWorkoutActivityTypeCycling |
| bootcamp          | EXERCISE_TYPE_BOOT_CAMP   | NA                                |
|	bowling	        |	NA	                    | HKWorkoutActivityTypeBowling	    |
| boxing   	        | EXERCISE_TYPE_BOXING      | HKWorkoutActivityTypeBoxing       |
| calisthenics      | EXERCISE_TYPE_CALISTHENICS | NA                               |
| cricket           | EXERCISE_TYPE_CRICKET     | HKWorkoutActivityTypeCricket      |
|   cooldown        | NA                        | HKWorkoutActivityTypeCooldown     |
| core_training	    | EXERCISE_TYPE_STRENGTH_TRAINING | HKWorkoutActivityTypeCoreTraining	|
| crossfit	        | EXERCISE_TYPE_STRENGTH_TRAINING | HKWorkoutActivityTypeCrossTraining|
| curling	        | NA	                    |	HKWorkoutActivityTypeCurling	|
| dancing           | EXERCISE_TYPE_DANCING     | HKWorkoutActivityTypeCardioDance  |
| dancing.social    | EXERCISE_TYPE_DANCING     | HKWorkoutActivityTypeSocialDance  |
| dancing.cardio    | EXERCISE_TYPE_DANCING     | HKWorkoutActivityTypeCardioDance  |
| disc_sports       | NA                        | HKWorkoutActivityTypeDiscSports   |
| elliptical	    | EXERCISE_TYPE_ELLIPTICAL   | HKWorkoutActivityTypeElliptical  |
| exercise_class    | EXERCISE_TYPE_EXERCISE_CLASS | NA                             |
| fencing           | EXERCISE_TYPE_FENCING     | HKWorkoutActivityTypeFencing      |
| fitness_gaming    | NA                        | HKWorkoutActivityTypeFitnessGaming |
| fishing	        | NA	                    | HKWorkoutActivityTypeFishing	    |
| flexibility       | EXERCISE_TYPE_GYMNASTICS  | HKWorkoutActivityTypeFlexibility |
| football.american | EXERCISE_TYPE_FOOTBALL_AMERICAN | KWorkoutActivityTypeAmericanFootball |
| football.australian | EXERCISE_TYPE_FOOTBALL_AUSTRALIAN | HKWorkoutActivityTypeAustralianFootball |
| football.soccer	| EXERCISE_TYPE_SOCCER	    | HKWorkoutActivityTypeSoccer	    |
| frisbee_disc      | EXERCISE_TYPE_FRISBEE_DISC | NA                            |
| golf              | EXERCISE_TYPE_GOLF        | HKWorkoutActivityTypeGolf       |
| guided_breathing  | EXERCISE_TYPE_GUIDED_BREATHING | NA                      |
| gymnastics	    | EXERCISE_TYPE_GYMNASTICS * | HKWorkoutActivityTypeGymnastics  |
| handball	        | EXERCISE_TYPE_HANDBALL    | HKWorkoutActivityTypeHandball    |
| interval_training.high_intensity | EXERCISE_TYPE_HIGH_INTENSITY_INTERVAL_TRAINING |  HKWorkoutActivityTypeHighIntensityIntervalTraining |
| hiking            | EXERCISE_TYPE_HIKING     | HKWorkoutActivityTypeHiking      |
| hockey            | EXERCISE_TYPE_ICE_HOCKEY | HKWorkoutActivityTypeHockey      |
| hockey.roller     | EXERCISE_TYPE_ROLLER_HOCKEY | HKWorkoutActivityTypeHockey   |
| horseback_riding	| NA	                    | HKWorkoutActivityTypeEquestrianSports	|
| hunting	        | NA	                    | HKWorkoutActivityTypeHunting	    |
| ice_skating       | EXERCISE_TYPE_ICE_SKATING | HKWorkoutActivityTypeSkatingSports|
| jump_rope	        | NA	                    | HKWorkoutActivityTypeJumpRope	    |
| kickboxing	    | EXERCISE_TYPE_MARTIAL_ARTS| HKWorkoutActivityTypeKickboxing	|
| lacrosse	        | NA	                    | HKWorkoutActivityTypeLacrosse	    |
| martial_arts	    | EXERCISE_TYPE_MARTIAL_ARTS| HKWorkoutActivityTypeMartialArts	|
| meditation	    | NA                    	| HKWorkoutActivityTypeMindAndBody	|
| mixed_metabolic_cardio | NA	                | HKWorkoutActivityTypeMixedCardio	|
| other	            | EXERCISE_TYPE_OTHER_WORKOUT | HKWorkoutActivityTypeOther	    |
| paddle_sports	    | EXERCISE_TYPE_PADDLING	| HKWorkoutActivityTypePaddleSports	|
| paragliding	    | EXERCISE_TYPE_PARAGLIDING	| NA	                            |
| pilates	        | EXERCISE_TYPE_PILATES	    | HKWorkoutActivityTypePilates	    |
| play	            | NA	                    | HKWorkoutActivityTypePlay	        |
| pickleball        | NA                        | HKWorkoutActivityTypePickleball   |
| preparation_and_recovery | EXERCISE_TYPE_STRETCHING |	HKWorkoutActivityTypePreparationAndRecovery *	|
| racquetball	    | EXERCISE_TYPE_RACQUETBALL	| HKWorkoutActivityTypeRacquetball	    |
| rock_climbing	    | EXERCISE_TYPE_ROCK_CLIMBING	|	HKWorkoutActivityTypeClimbing	|
| rowing	        | EXERCISE_TYPE_ROWING	|	HKWorkoutActivityTypeRowing	|
| rowing.machine    | EXERCISE_TYPE_ROWING_MACHINE	|	HKWorkoutActivityTypeRowing	|
| rugby	            | EXERCISE_TYPE_RUGBY	        | HKWorkoutActivityTypeRugby	|
| running	        | EXERCISE_TYPE_RUNNING	        | HKWorkoutActivityTypeRunning	|
| running.treadmill	| EXERCISE_TYPE_RUNNING_TREADMILL |	HKWorkoutActivityTypeRunning	|
| sailing	        | EXERCISE_TYPE_SAILING	    | HKWorkoutActivityTypeSailing	|
| scuba_diving	    | EXERCISE_TYPE_SCUBA_DIVING | HKWorkoutActivityTypeWaterSports	|
| skating       	| EXERCISE_TYPE_SKATING	    | HKWorkoutActivityTypeSkatingSports	|
| skiing	        | EXERCISE_TYPE_SKIING *	| HKWorkoutActivityTypeDownhillSkiing	|
| skiing.cross_country|	EXERCISE_TYPE_SKIING    | HKWorkoutActivityTypeCrossCountrySkiing	|
| skiing.downhill	| EXERCISE_TYPE_SKIING	    | HKWorkoutActivityTypeDownhillSkiing *	|
| snowboarding	    | EXERCISE_TYPE_SNOWBOARDING	| HKWorkoutActivityTypeSnowboarding	|
| snowshoeing	    | EXERCISE_TYPE_SNOWSHOEING	| HKWorkoutActivityTypeSnowSports	|
| snow_sports	    | NA	                    | HKWorkoutActivityTypeSnowSports	|
| softball	        | EXERCISE_TYPE_SOFTBALL	|	HKWorkoutActivityTypeSoftball	|
| squash	        | EXERCISE_TYPE_SQUASH	    | HKWorkoutActivityTypeSquash	|
| stair_climbing	| EXERCISE_TYPE_STAIR_CLIMBING	| HKWorkoutActivityTypeStairs	|
| stair_climbing.machine | EXERCISE_TYPE_STAIR_CLIMBING_MACHINE	| HKWorkoutActivityTypeStairClimbing	|
| strength_training	| EXERCISE_TYPE_STRENGTH_TRAINING * |	HKWorkoutActivityTypeTraditionalStrengthTraining * |
| strength_training.functional | EXERCISE_TYPE_STRENGTH_TRAINING | HKWorkoutActivityTypeFunctionalStrengthTraining	|
| stretching	    | EXERCISE_TYPE_STRETCHING * | HKWorkoutActivityTypePreparationAndRecovery	|
| surfing	        | EXERCISE_TYPE_SURFING 	| HKWorkoutActivityTypeSurfingSports	|
| swimming	        | EXERCISE_TYPE_SWIMMING_POOL|	HKWorkoutActivityTypeSwimming * |
| swimming.pool	    | EXERCISE_TYPE_SWIMMING_POOL * |	HKWorkoutActivityTypeSwimming	|
| swimming.open_water | EXERCISE_TYPE_SWIMMING_OPEN_WATER	| HKWorkoutActivityTypeSwimming	|
| table_tennis	    | EXERCISE_TYPE_TABLE_TENNIS | HKWorkoutActivityTypeTableTennis	|
| tai_chi           | NA                        | HKWorkoutActivityTypeTaiChi       |
| tennis	        | EXERCISE_TYPE_TENNIS      | HKWorkoutActivityTypeTennis	    |
| track_and_field	| NA	                    | HKWorkoutActivityTypeTrackAndField	|
| volleyball	    | EXERCISE_TYPE_VOLLEYBALL	| HKWorkoutActivityTypeVolleyball	|
| walking	        | EXERCISE_TYPE_WALKING 	| HKWorkoutActivityTypeWalking	|
| water_fitness	    | NA	                    | HKWorkoutActivityTypeWaterFitness	|
| water_polo	    | EXERCISE_TYPE_WATER_POLO	| HKWorkoutActivityTypeWaterPolo	|
| water_sports	    | NA	                    | HKWorkoutActivityTypeWaterSports	|
| weightlifting	    | EXERCISE_TYPE_WEIGHTLIFTING |	HKWorkoutActivityTypeTraditionalStrengthTraining	|
| wheelchair	    | EXERCISE_TYPE_WHEELCHAIR * | HKWorkoutActivityTypeWheelchairRunPace |
| wheelchair.walkpace |	EXERCISE_TYPE_WHEELCHAIR |HKWorkoutActivityTypeWheelchairWalkPace	|
| wheelchair.runpace | EXERCISE_TYPE_WHEELCHAIR	|	HKWorkoutActivityTypeWheelchairRunPace	|
| wrestling	        |	NA	|	HKWorkoutActivityTypeWrestling	|
| yoga	            | EXERCISE_TYPE_YOGA    	|	HKWorkoutActivityTypeYoga	|
