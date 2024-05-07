package ae.tahaluf.modulea.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [UniversityEntity::class], version = 2)
@TypeConverters(Converters::class)  // Register the custom type converter
abstract class UniversityDatabase : RoomDatabase() {
    abstract fun universityDao(): UniversityDao
}
