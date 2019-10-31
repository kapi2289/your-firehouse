package pl.kapiz.yourfirehouse.data.api.pojo

import com.google.gson.annotations.SerializedName

data class User(

    val id: Long,

    @SerializedName("bsisOuId")
    val ouId: Long,

    @SerializedName("bsisOuName")
    val ouName: String,

    val name: String,

    val firstName: String,

    val lastName: String,

    val phoneNumber: String
)
