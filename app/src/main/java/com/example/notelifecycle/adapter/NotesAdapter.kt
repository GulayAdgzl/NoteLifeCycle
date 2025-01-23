import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(
    private val notes: List<Note>,
    private val listener: (Note) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NoteViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): NotesViewHolder {
      val binding=ItemNoteBinding.inflate(
        LayoutInflater.from(parent.context),parent,false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
       val note=notes[position]
       with(holder.binding) {
        tvNoteTitle.text = note.title
        tvNoteDate.text = note.lastEditTime
        root.setOnClickListener { onNoteClick(note) }
       }
    }

    override fun getItemCount(): Int = notes.size

   fun updateNotes(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }
}