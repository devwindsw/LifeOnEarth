/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.devwindsw.lifeonearth.core.unsplash.retrofit

import android.util.Log
import androidx.tracing.trace
import com.devwindsw.lifeonearth.core.secrets.CustSecrets
import com.devwindsw.lifeonearth.core.unsplash.BuildConfig
import com.devwindsw.lifeonearth.core.unsplash.UnsplashNetworkDataSource
import com.devwindsw.lifeonearth.core.unsplash.model.UnsplashNetworkPhoto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

// Refer to https://square.github.io/okhttp/features/interceptors/,
// https://devgeek.tistory.com/56, and https://kimch3617.tistory.com/entry/Retrofit%EC%9D%84-%EC%9D%B4%EC%9A%A9%ED%95%9C-%ED%86%A0%ED%81%B0-%EC%9D%B8%EC%A6%9D-%EB%B0%A9%EC%8B%9D-%EA%B5%AC%ED%98%84
/*
class AuthorizationInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val auth = "Client-ID ${CustSecrets.UnsplashAccessKey}"
        requestBuilder.addHeader("Authorization", auth)
        return chain.proceed(requestBuilder.build())
    }
}

class LoggingInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.i("JAKECHOI", "Sending request url=[${request.url}], headers=[${request.headers}]")
        val response = chain.proceed(request)
        Log.i("JAKECHOI", "response=${response.code}")
        return response
    }
}
*/

/**
 * Retrofit API declaration for Unsplash API
 */
private interface RetrofitUnsplashNetworkApi {
    @GET(value = "photos/")
    suspend fun getPhotos(
    ): List<UnsplashNetworkPhoto>
}

private const val UNSPLASH_API_BASE_URL = BuildConfig.UNSPLASH_API_BASE_URL
private const val CONTENT_TYPE = "Content-Type"
private const val APPLICATION_JSON = "application/json"
private const val ACCEPT_VERSION = "Accept-Version"
private const val AUTHORIZATION = "Authorization"

/**
 * [Retrofit] backed [UnsplashNetworkDataSource]
 */
@Singleton
internal class RetrofitUnsplashNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>,
) : UnsplashNetworkDataSource {

    private fun createHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val auth = "Client-ID ${CustSecrets.UnsplashAccessKey}"
            val newRequest = chain.request().newBuilder()
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .addHeader(ACCEPT_VERSION, "v1")
                .addHeader(AUTHORIZATION, auth)
                .build()
            chain.proceed(newRequest)
        }
    }

    private fun createLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    private fun createHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            //.addInterceptor(AuthorizationInterceptor())
            //.addInterceptor(LoggingInterceptor())
            .addNetworkInterceptor(createHeaderInterceptor())
            .addNetworkInterceptor(createLoggingInterceptor())
            .build()
    }

    private val networkApi = trace("RetrofitUnsplashNetwork") {
        Retrofit.Builder()
            .baseUrl(UNSPLASH_API_BASE_URL)
            // We use callFactory lambda here with dagger.Lazy<Call.Factory>
            // to prevent initializing OkHttp on the main thread.
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .client(createHttpClient())
            .build()
            .create(RetrofitUnsplashNetworkApi::class.java)
    }

    override suspend fun getPhotos(): List<UnsplashNetworkPhoto> =
        networkApi.getPhotos()
}
