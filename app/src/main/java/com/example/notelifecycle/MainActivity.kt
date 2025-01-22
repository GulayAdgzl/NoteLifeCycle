package com.example.notelifecycle

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNewNote.setOnClickListener {
            val intent = Intent(this, NewNoteActivity::class.java)
            startActivity(intent)

            setupNotesList()
        }

        private fun startNoteActivity() {
            val intent = Intent(this, NoteActivity::class.java)
           startActivityForResult(intent, NEW_REQUEST_CODE)
        }

        private fun setupNotesList() {
          
            val adapter= NotesAdapter(mutableListOf()){note ->
            val intent = Intent(this, NoteActivity::class.java)
            intent.putExtra(NOTE_EXTRA, note)
            startActivityForResult(intent, EDIT_REQUEST_CODE)}
        }
        binding.recyclerViewNotes.adapter = adapter
        binding.recyclerViewNotes.layoutManager = LinearLayoutManager(this)
        

    }

 
}