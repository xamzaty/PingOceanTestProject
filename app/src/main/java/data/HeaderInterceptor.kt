package data

import okhttp3.Interceptor
import okhttp3.Response
import utils.SharedKeys
import utils.SharedPrefUtil
import java.io.IOException

class HeaderInterceptor(private val sharedPrefUtil: SharedPrefUtil) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(
        chain.request().newBuilder()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer ${sharedPrefUtil.getString(SharedKeys.ACCESS_TOKEN)}")
            .build()
    )
}