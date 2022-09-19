package com.althaafridha.latihannoteapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDB: RoomDatabase() {

	abstract val noteDao: NoteDao

	companion object {

		@Volatile
		private var INSTANCE: NoteDB? = null

		fun getInstance(context: Context): NoteDB{
			synchronized(this) {
				var instance = INSTANCE
				if (instance == null) {
					instance = Room.databaseBuilder(
						context.applicationContext,
						NoteDB::class.java,
						"note_data.db"
					).build()
				}
				return instance
			}
		}
	}
}