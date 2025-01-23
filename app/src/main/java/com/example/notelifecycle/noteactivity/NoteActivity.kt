class NoteActivity:AppCompatActivity(){
    private lateinit var noteEditText:EditText
    private lateinit var lastEditTimeTextView:TextView
    private var lastSavedNote:String=""

    companion object{
        private const val NOTE_EXTRA="note_extra"
        private const val EDIT_TIME_KEY="edit_time_key"
    }

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        noteEditText=findViewById(R.id.etNote)
        lastEditTimeTextView=findViewById(R.id.tvLastEditTime)

        //kaydedilmiş durumu geri yükleme
        savedInstanceState?.let{
            lastSavedNote=it.getString(NOTE_EXTRA,"")
            noteEditText.setText(lastSavedNote)
            lastEditTimeTextView.text=it.getString(EDIT_TIME_KEY,"")
        }
        Log.d("LifecycleDemo","onCreate çağrıldı")
        
    }

    override fun onStart(){
        super.onStart()
        Log.d("LifecycleDemo","onStart çağrıldı")
    }

    override fun onResume(){
        super.onResume()
        updateLastEditTime()
        Log.d("LifecycleDemo","onResume çağrıldı")
    }

    override fun onPause(){
        super.onPause()
        lastSavedNote=noteEditText.text.toString()
        Log.d("LifecycleDemo","onPause çağrıldı")
    }
    
    override fun onStop(){
        super.onStop()
        Log.d("LifecycleDemo","onStop çağrıldı")
    }

    override fun onSaveInstanceState(outState:Bundle){
        super.onSaveInstanceState(outState)
        outState.putString(NOTE_EXTRA,lastSavedNote)
        outState.putString(EDIT_TIME_KEY,lastEditTimeTextView.text.toString())
       Log.d("LifecycleDemo","onSaveInstanceState çağrıldı")
    }
    override fun onDestory(){
        super.onDestroy()
        Log.d("LifecycleDemo","onDestroy çağrıldı")
    }
    private fun updateLastEditTime() {
        val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        lastEditTimeTextView.text = "Son düzenleme: $currentTime"
    }
}