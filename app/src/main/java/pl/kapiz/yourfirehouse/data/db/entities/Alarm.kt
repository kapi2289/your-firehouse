package pl.kapiz.yourfirehouse.data.db.entities

import androidx.room.Entity

@Entity
data class Alarm(

    val id: Int,

    val description: String
)
