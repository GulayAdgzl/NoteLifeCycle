class NoteViewModel : ViewModel() {
    private val _noteSaveStatus = MutableLiveData<Boolean>()
    val noteSaveStatus: LiveData<Boolean> = _noteSaveStatus
 
    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes
 
    private val repository = NoteRepository()
 
    fun saveNote(note: Note) {
        viewModelScope.launch {
            try {
                repository.saveNote(note)
                _noteSaveStatus.value = true
            } catch (e: Exception) {
                _noteSaveStatus.value = false
                Log.e(TAG, "Error saving note", e)
            }
        }
    }
 
    fun getAllNotes() {
        viewModelScope.launch {
            try {
                val notesList = repository.getAllNotes()
                _notes.value = notesList
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching notes", e)
                _notes.value = emptyList()
            }
        }
    }
 
    fun deleteNote(noteId: Long) {
        viewModelScope.launch {
            try {
                repository.deleteNote(noteId)
                getAllNotes() // Listeyi güncelle
            } catch (e: Exception) {
                Log.e(TAG, "Error deleting note", e)
            }
        }
    }
 
    fun updateNote(note: Note) {
        viewModelScope.launch {
            try {
                repository.updateNote(note)
                _noteSaveStatus.value = true
            } catch (e: Exception) {
                _noteSaveStatus.value = false
                Log.e(TAG, "Error updating note", e)
            }
        }
    }
 
    companion object {
        private const val TAG = "NoteViewModel"
    }
 }