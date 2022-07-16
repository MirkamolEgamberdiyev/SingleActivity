package com.mirkamol.android_sass.fragment.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirkamol.android_sass.model.HomePhotoItem
import com.mirkamol.android_sass.repository.MainRepository
import com.mirkamol.android_sass.utils.UiStateList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    private val _getPhotosState = MutableStateFlow<UiStateList<HomePhotoItem>>(UiStateList.EMPTY)
    val getPhotosState = _getPhotosState
    fun getAllPhotos(page:Int, per_page:Int) = viewModelScope.launch {
        _getPhotosState.value = UiStateList.LOADING
        try {
            val response = repository.getAllPhotos(page, per_page)
            _getPhotosState.value = UiStateList.SUCCESS(response)
        } catch (e: Exception) {
            _getPhotosState.value = UiStateList.ERROR(e.localizedMessage ?: "No Connection")
        }
    }
}