package com.kdtechs.imageupload.image

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ImageAPI {


    @POST("UploadImageFile")
    @Multipart
    suspend fun uploadImage(@Part file: MultipartBody.Part, @Query("fileType")fileType: String,  @Query("userId")userId: String):Response<Any>

}