package api

import data.PostUser
import data.Token
import data.Users
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import utils.Constants.AUTH_URL
import utils.Constants.PROFILE_URL
import java.util.HashMap

interface UsersApi {

    @POST(AUTH_URL)
    suspend fun postUser(@Body postUser: PostUser): Response<Token>

    @GET(PROFILE_URL)
    suspend fun getUser(@Header("Authorization") token: String): Response<Users>
}