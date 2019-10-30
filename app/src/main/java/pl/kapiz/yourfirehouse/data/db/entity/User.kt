package pl.kapiz.yourfirehouse.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class User(

    @PrimaryKey
    val id: Long,

    @SerializedName("bsisOuId")
    val ouId: Long,

    @SerializedName("bsisOuName")
    val ouName: String,

    val firstName: String,

    val lastName: String,

    val phoneNumber: String
)
