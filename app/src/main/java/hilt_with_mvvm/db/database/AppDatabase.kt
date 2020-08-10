package hilt_with_mvvm.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hilt_with_mvvm.db.RunDao
import hilt_with_mvvm.db.RunModel
import hilt_with_mvvm.db.converters.BitmapConverter


@Database(
        entities = [RunModel::class],
        version = 1
)
@TypeConverters(BitmapConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getRunDao(): RunDao
}