package com.kdtechs.imageupload

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kdtechs.imageupload.helper.Resource
import com.kdtechs.imageupload.image.Image
import com.kdtechs.imageupload.image.ImageAPI
import com.kdtechs.imageupload.image.ImageImplementation
import com.kdtechs.imageupload.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class MainActivityViewModel : ViewModel() {


    private val imageFile_ = MutableLiveData<File>()
    val imageFile: LiveData<File>
        get() = imageFile_


    private val mainEvent_ = MutableLiveData<MainEvent>()
    val mainEvent: LiveData<MainEvent>
        get() = mainEvent_


    var image: Image? = null
    var imageApi: ImageAPI? = null


    init {

        imageApi = RetrofitInstance.getRetrofitInstance().create(ImageAPI::class.java)
        image = ImageImplementation(imageApi!!)


    }


    sealed class MainEvent {

        class Success(val jsonObject: Any) : MainEvent()
        class Failure(val m: String) : MainEvent()
        object Empty : MainEvent()
        object Loading : MainEvent()


    }


    fun uploadImage(file: File) {
        mainEvent_.value = MainEvent.Loading

        val profileImage: RequestBody = RequestBody.create(
            "image/jpg".toMediaTypeOrNull(),
            file
        )

        val profileImageBody: MultipartBody.Part =
            MultipartBody.Part.createFormData(
                "file",
                file.getName(), profileImage
            )


        val fileType = "image"
        val userId = "kdtechs"


        viewModelScope.launch {


            val response = image?.uploadImage(profileImageBody, fileType,userId)


            when (response) {


                is Resource.Success -> {

                    mainEvent_.value = MainEvent.Success(response.data!!)
                }


                is Resource.Error -> {

                    mainEvent_.value = MainEvent.Failure(response.message!!)

                }


            }


        }


    }

    fun getMultiPartFormRequestBody(tag: String?): RequestBody {
        return RequestBody.create(MultipartBody.FORM, tag!!)

    }

    fun sendImageFile(file: File) {
        imageFile_.value = file

    }


}