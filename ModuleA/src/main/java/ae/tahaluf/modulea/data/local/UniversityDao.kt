package ae.tahaluf.modulea.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UniversityDao {
    @Query("SELECT * FROM universities")
    fun getAll(): List<UniversityEntity>

    @Insert
    fun insertAll(universities: List<UniversityEntity>)
}
