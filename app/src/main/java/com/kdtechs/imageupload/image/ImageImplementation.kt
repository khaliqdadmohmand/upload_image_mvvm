package com.kdtechs.imageupload.image

import android.util.Log
import com.kdtechs.imageupload.helper.Resource
import okhttp3.MultipartBody

class ImageImplementation( val imageAPI: ImageAPI):Image{
    override suspend fun uploadImage(file:MultipartBody.Part,fileType:String,userId:String):Resource<Any> {

        var TAG = "KDTECHS"
        return try {

            val response = imageAPI.uploadImage(file,fileType,userId)

            val result = response.body()
            Log.d(TAG, "" + result.toString())

            if (response.isSuccessful) {
                Log.d(TAG, "" + result.toString())

                Resource.Success(result!!)


            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occured")
        }
    }

}