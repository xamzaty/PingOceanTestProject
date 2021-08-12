package data

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class Users(
    @SerializedName("email")
    @Expose
    val name: String,
    @SerializedName("password")
    @Expose
    val email: String,
    @SerializedName("phone")
    @Expose
    val phone: String,
    @SerializedName("position")
    @Expose
    val position: String,
)
