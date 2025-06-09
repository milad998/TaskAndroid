import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): LiveData<List<Task>>

    @Insert
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)
}
