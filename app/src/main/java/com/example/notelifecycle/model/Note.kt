import android.os.Parcel
import android.os.Parcelable

data class Note(
    val id:Long=0,
    val title: String,
    val content: String,
    val lastEditTime: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(content)
        parcel.writeString(lastEditTime)
    }
    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<Note> {
        override fun createFromParcel(parcel: Parcel): Note {
            return Note(parcel)
        }
        override fun newArray(size: Int): Array<Note?> {
            return arrayOfNulls(size)
        }
    }
}