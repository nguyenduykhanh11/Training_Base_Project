package com.beetech.hsba.network


import android.content.Context
import com.beetech.hsba.R
import com.beetech.hsba.utils.DeviceUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkCheckerInterceptor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (DeviceUtil.hasConnection(context)) {
            chain.proceed(chain.request())
        } else {
            throw NoConnectivityException()
        }
    }

    inner class NoConnectivityException : Exception() {
        override val message: String?
            get() = context.getString(R.string.disconect)
    }
}
