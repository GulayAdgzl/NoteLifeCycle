package com.example.notelifecycle

import NoteActivity
import NotesAdapter
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNewNote.setOnClickListener {
            val intent = Intent(this, NewNoteActivity::class.java)
            startActivity(intent)

            setupNotesList()
        }



        binding.recyclerViewNotes.adapter = adapter
        binding.recyclerViewNotes.layoutManager = LinearLayoutManager(this)


    }

    private fun startNoteActivity() {
        val intent = Intent(this, NoteActivity::class.java)
        startActivityForResult(intent, NEW_REQUEST_CODE)
    }

    private fun setupNotesList() {

        val adapter = NotesAdapter(mutableListOf()) { note ->
            val intent = Intent(this, NoteActivity::class.java)
            intent.putExtra(NOTE_EXTRA, note)
            startActivityForResult(intent, EDIT_REQUEST_CODE)
        }
    }
    fun onActivityReult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                NEW_REQUEST_CODE, EDIT_REQUEST_CODE -> {
                    setupNotesList()
                }
            }
        }
    }

    companion object {
        const val NEW_REQUEST_CODE = 1
        const val EDIT_REQUEST_CODE = 2
        const val NOTE_EXTRA = "note_extra"

    }


}
