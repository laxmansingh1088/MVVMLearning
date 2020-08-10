package hilt_with_mvvm.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RunDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRunModel(runModel: RunModel)

    @Delete
    suspend fun deleteRunModel(runModel: RunModel)

    @Update
    suspend fun updateRunModel(runModel: RunModel)

    @Query("SELECT * FROM RUNNING_TABLE ORDER BY timestamp DESC")
    fun getAllRunModelSortedByDate(): LiveData<List<RunModel>>

    @Query("SELECT * FROM RUNNING_TABLE ORDER BY caloriesBurned DESC")
    fun getAllRunModelSortedByCalories(): LiveData<List<RunModel>>

    @Query("SELECT * FROM RUNNING_TABLE ORDER BY timeInMillis DESC")
    fun getAllRunModelSortedByTimeInMillis(): LiveData<List<RunModel>>

    @Query("SELECT * FROM RUNNING_TABLE ORDER BY distanceInMetres DESC")
    fun getAllRunModelSortedByDistance(): LiveData<List<RunModel>>

    @Query("SELECT * FROM RUNNING_TABLE ORDER BY averageSpeedInKMPH DESC")
    fun getAllRunModelSortedByAverageSpeed(): LiveData<List<RunModel>>


    @Query("SELECT SUM(timeInMillis) from running_table")
    fun getTotalTimeInMillis(): LiveData<Long>

    @Query("SELECT SUM(caloriesBurned) from running_table")
    fun getTotalCaloriesBurned(): LiveData<Int>

    @Query("SELECT SUM(distanceInMetres) from running_table")
    fun getTotalDistanceInMeters(): LiveData<Int>


    @Query("SELECT AVG(averageSpeedInKMPH) from running_table")
    fun getTotalAverageSpeed(): LiveData<Float>


}