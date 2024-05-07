package ae.tahaluf.modulea.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Entity(tableName = "universities")
data class UniversityEntity(
    @PrimaryKey val name: String,
    val country: String,
    @ColumnInfo(name = "state-province")
    val stateProvince: String?,
    @TypeConverters(Converters::class)
    val domains: List<String>,
    @ColumnInfo(name = "web_pages")
    @TypeConverters(Converters::class)
    val webPages: List<String>,
    @ColumnInfo(name = "alpha_two_code")
    var alphaTwoCode: String?
)


class Converters {

    @TypeConverter
    fun fromString(value: String?): List<String> {
        return value?.split(",") ?: emptyList()  // Convert a comma-separated string to a list
    }

    @TypeConverter
    fun fromList(list: List<String>?): String {
        return list?.joinToString(",") ?: ""  // Convert a list to a comma-separated string
    }
}