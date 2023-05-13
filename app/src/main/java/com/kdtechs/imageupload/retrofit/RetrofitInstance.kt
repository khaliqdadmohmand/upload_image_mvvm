package com.kdtechs.imageupload.retrofit


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object{

         var instance:Retrofit?=null

        fun getRetrofitInstance():Retrofit{

            if (instance==null){
                instance = Retrofit.Builder().baseUrl("YOUR_BASE_URL_HERE")
                    .addConverterFactory(GsonConverterFactory.create()).build()
            }
            return instance!!
        }

    }
}