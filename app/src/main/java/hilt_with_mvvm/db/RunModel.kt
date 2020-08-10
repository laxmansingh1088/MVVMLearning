package hilt_with_mvvm.db

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "running_table")
data class RunModel(
        var img: Bitmap? = null,
        var timestamp: Long = 0L,
        var averageSpeedInKMPH: Float = 0F,
        var distanceInMetres: Float = 0F,
        var timeInMillis: Long = 0L,
        var caloriesBurned: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}