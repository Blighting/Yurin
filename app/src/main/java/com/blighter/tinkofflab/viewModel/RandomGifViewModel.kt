package com.blighter.tinkofflab.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blighter.tinkofflab.repository.GifRepository
import kotlinx.coroutines.launch

class RandomGifViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GifRepository(this.getApplication())
    val description = MutableLiveData<String>()
    val previousCount = MutableLiveData<Int>()
    private val totalCount = MutableLiveData<Int>()
    val gifURL = MutableLiveData<String>()
    val isVisible = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()


    init {
        previousCount.value = 0
        totalCount.value = 1
        onNext()
    }

    fun onNext() {
        isError.value = false
        previousCount.value = previousCount.value!! + 1
        if (previousCount.value!! == totalCount.value) {
            isVisible.value = false
            totalCount.value = totalCount.value!! + 1
            viewModelScope.launch {
                val gif = repository.getGifFromApiAsync().await()
                if (gif != null) {
                    description.value = gif!!.description
                    gifURL.value = gif.gifURL
                    isVisible.value = true
                    repository.insertGif(previousCount.value!!, gif)
                } else {
                    isError.value = true
                    description.value =
                        "Возникла ошибка, возможно у вас нет подключения к интернету"
                    isVisible.value = true
                }
            }
        } else (
                viewModelScope.launch {
                    val gif = repository.getGifFromDaoAsync(previousCount.value!!).await()
                    description.value = gif.description
                    gifURL.value = gif.gifURL
                }
                )
    }

    fun onBack() {
        isError.value = false
        viewModelScope.launch {
            previousCount.value = previousCount.value!! - 1
            val gif = repository.getGifFromDaoAsync(previousCount.value!!).await()
            description.value = gif.description
            gifURL.value = gif.gifURL
        }
    }

    override fun onCleared() {
        viewModelScope.launch {
            repository.deleteDatabase()
        }
        super.onCleared()
    }
}