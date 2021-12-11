package io.github.leoallvez.tasklist.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = "",
    var description: String = "",
    var status: String = PENDING
) {
//    fun destruction(): Pair<String, String> {
//        return Pair(title, description)
//    }

    fun isFilled(): Boolean {
        return title.isNotEmpty() && description.isNotEmpty()
    }

    companion object {
        const val PENDING = "PENDING"
        const val DOING = "DOING"
        const val DONE = "DONE"
    }
}
