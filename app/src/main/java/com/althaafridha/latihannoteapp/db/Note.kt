package com.althaafridha.latihannoteapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_data_table")
data class Note(

	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "note_id")
	var id: Long,

	@ColumnInfo(name = "note_title")
	var title: String,

	@ColumnInfo(name = "note_desc")
	var desc: String
)
