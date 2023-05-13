package com.kdtechs.imageupload.image

import com.kdtechs.imageupload.helper.Resource
import okhttp3.MultipartBody

interface Image {

suspend fun uploadImage(file:MultipartBody.Part, fileType:String,userId:String):Resource<Any>


}