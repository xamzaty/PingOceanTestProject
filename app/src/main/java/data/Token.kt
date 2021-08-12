package data

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class Token(
    @SerializedName("token")
    @Expose
    val token: String
)
