package com.althaafridha.latihannoteapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.althaafridha.latihannoteapp.db.Note
import com.althaafridha.latihannoteapp.repository.NoteRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NoteRepository): ViewModel() {
	 val inputTitle = MutableLiveData<String?>()
	 val inputDesc = MutableLiveData<String?>()

	 val saveUpdateButton = MutableLiveData<String>()
	 val deleteAllDeleteButton = MutableLiveData<String>()

	val note = repository.note

	private lateinit var updateDelete: Note
	private  var isUpdateOrDelete = false

	init {
		saveUpdateButton.value = "Save"
		deleteAllDeleteButton.value = "Delete All"
	}

	fun saveOrUpdate() {
		if (isUpdateOrDelete){
			updateDelete.title = inputTitle.value!!
			updateDelete.desc = inputDesc.value!!
			update(updateDelete)
		} else {
			val title = inputTitle.value!!
			val desc = inputDesc.value!!

			insert(Note(0, title, desc))
			inputTitle.value = ""
			inputDesc.value = ""
		}
	}

	fun clearAllOrDelete() {
		if (isUpdateOrDelete) delete(updateDelete) else clearAll()
	}

	private fun insert(note: Note) {
		viewModelScope.launch {
			repository.insert(note)
		}
	}

	private fun update(note: Note) {
		viewModelScope.launch {
			repository.update(note)

			inputTitle.value = null
			inputDesc.value = null
			isUpdateOrDelete = false
			saveUpdateButton.value = "Save"
			deleteAllDeleteButton.value = "Delete All"
		}
	}


	private fun delete(note: Note) {
		viewModelScope.launch {
			repository.delete(note)

			inputTitle.value = null
			inputDesc.value = null
			isUpdateOrDelete = false
			saveUpdateButton.value = "Save"
			deleteAllDeleteButton.value = "Delete All"
		}
	}

	private fun clearAll() {
		viewModelScope.launch{
			repository.deleteAll()
		}
	}

	fun initUpdateAndDelete(note: Note){
		inputTitle.value = note.title
		inputDesc.value = note.desc
		isUpdateOrDelete = true
		updateDelete = note
		saveUpdateButton.value = "Update"
		deleteAllDeleteButton.value = "Delete"
	}
}