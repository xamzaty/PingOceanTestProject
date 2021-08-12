package repository

import com.example.pingoceantestproject.view.AppModule
import data.PostUser
import data.Token
import data.Users
import okhttp3.ResponseBody
import retrofit2.Response

class UserRepository {

    suspend fun pushPost(postUser: PostUser): Response<Token> {
        return AppModule.api.postUser(postUser)
    }

    suspend fun getUser(token: String): Response<Users> {
        return AppModule.api.getUser(token)
    }
}