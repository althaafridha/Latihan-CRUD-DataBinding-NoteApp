package com.althaafridha.latihannoteapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.althaafridha.latihannoteapp.repository.NoteRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
			return MainViewModel(repository) as T
		} else {
			throw IllegalArgumentException("Unknown ViewModel Class:")
		}
	}
}
