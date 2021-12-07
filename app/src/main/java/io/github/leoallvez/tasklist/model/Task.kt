package io.github.leoallvez.tasklist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val status: String = PENDING
) {
    companion object {
        const val PENDING = "pending"
        const val DOING = "doing"
        const val DONE = "done"
    }
}
