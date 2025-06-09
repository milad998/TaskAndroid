class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TaskViewModel
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dao = TaskDatabase.getDatabase(this).taskDao()
        val repository = TaskRepository(dao)
        val factory = TaskViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory)[TaskViewModel::class.java]
        adapter = TaskAdapter { task -> viewModel.deleteTask(task) }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val editText: EditText = findViewById(R.id.editTextTask)
        val buttonAdd: Button = findViewById(R.id.buttonAdd)

        buttonAdd.setOnClickListener {
            val title = editText.text.toString()
            if (title.isNotBlank()) {
                viewModel.addTask(title)
                editText.text.clear()
            }
        }

        viewModel.tasks.observe(this) { tasks ->
            adapter.setTasks(tasks)
        }
    }
}
