import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.notelifecycle.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteActivity: AppCompatActivity(){
    private lateinit var noteEditText: EditText
    private lateinit var lastEditTimeTextView: TextView
    private var lastSavedNote:String=""

    companion object{
        private const val NOTE_EXTRA="note_extra"
        private const val EDIT_TIME_KEY="edit_time_key"
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_note)

        noteEditText=findViewById(R.id.tvNoteTitle)
        lastEditTimeTextView=findViewById(R.id.tvNoteDate)

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

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifecycleDemo","onDestroy çağrıldı")
    }

    private fun updateLastEditTime() {
        val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        lastEditTimeTextView.text = "Son düzenleme: $currentTime"
    }
}