class TaskRepository(private val dao: TaskDao) {

    val tasks = dao.getAllTasks()

    suspend fun insert(task: Task) {
        dao.insert(task)
    }

    suspend fun delete(task: Task) {
        dao.delete(task)
    }
}
