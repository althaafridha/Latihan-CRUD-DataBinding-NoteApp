package com.althaafridha.latihannoteapp.repository

import com.althaafridha.latihannoteapp.db.Note
import com.althaafridha.latihannoteapp.db.NoteDao

class NoteRepository(private val dao: NoteDao) {

	val note = dao.getAllNote()

	suspend fun insert(note: Note) {
		dao.insertNote(note)
	}

	suspend fun update(note: Note) {
		dao.updateNote(note)
	}

	suspend fun delete(note: Note){
		dao.deleteNote(note)
	}

	suspend fun deleteAll() {
		dao.deleteAllNote()
	}
}