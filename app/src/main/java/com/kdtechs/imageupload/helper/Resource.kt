package com.kdtechs.imageupload.helper

// TO CHECK THE API RESPONSE STATUS WE WILL USE THIS SEALED CLASS
    sealed class Resource<T>(val data: T?, val message: String?) {
        class Success<T>(data: T) : Resource<T>(data, null)
        class Error<T>(message: String) : Resource<T>(null, message)
    }
