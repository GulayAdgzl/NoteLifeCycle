class NoteRepository {
    private val noteDao = NoteDatabase.getInstance().noteDao()

    suspend fun saveNote(note: Note) = withContext(Dispatchers.IO) {
        noteDao.insertNote(note)
    }

    suspend fun getAllNotes() = withContext(Dispatchers.IO) {
        noteDao.getAllNotes()
    }

    suspend fun deleteNote(noteId: Long) = withContext(Dispatchers.IO) {
        noteDao.deleteNote(noteId)
    }

    suspend fun updateNote(note: Note) = withContext(Dispatchers.IO) {
        noteDao.updateNote(note)
    }
}