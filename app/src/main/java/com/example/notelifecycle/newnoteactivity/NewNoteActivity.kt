package com.example.notelifecycle

import Note
import NoteViewModel
import android.app.Activity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.notelifecycle.databinding.ActivityNewNoteBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NewNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewNoteBinding
    private var currentNote: Note? = null
    private val noteViewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Intent'ten gelen notu al (düzenleme durumu için)
        currentNote = intent.getParcelableExtra(EXTRA_NOTE)

        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        with(binding) {
            // Mevcut not varsa alanları doldur
            currentNote?.let { note ->
                etNoteTitle.setText(note.title)
                etNoteContent.setText(note.content)
                btnSave.text = getString(R.string.next)
            }

            // Kaydet butonu
            btnSave.setOnClickListener {
                saveNote()
            }

            // Geri butonu
            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun observeViewModel() {
        noteViewModel.noteSaveStatus.observe(this) { success ->
            if (success) {
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }

    private fun saveNote() {
        val title = binding.etNoteTitle.text.toString()
        val content = binding.etNoteContent.text.toString()

        if (title.isEmpty()) {
            binding.etNoteTitle.error = "Başlık gerekli"
            return
        }

        val currentTime = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            .format(Date())

        val note = currentNote?.copy(
            title = title,
            content = content,
            lastEditTime = currentTime
        ) ?: Note(
            title = title,
            content = content,
            lastEditTime = currentTime
        )

        noteViewModel.saveNote(note)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_TITLE, binding.etNoteTitle.text.toString())
        outState.putString(STATE_CONTENT, binding.etNoteContent.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.etNoteTitle.setText(savedInstanceState.getString(STATE_TITLE))
        binding.etNoteContent.setText(savedInstanceState.getString(STATE_CONTENT))
    }

    companion object {
        const val EXTRA_NOTE = "extra_note"
        private const val STATE_TITLE = "state_title"
        private const val STATE_CONTENT = "state_content"
    }
}