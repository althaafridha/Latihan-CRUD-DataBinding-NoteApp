package com.althaafridha.latihannoteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.althaafridha.latihannoteapp.databinding.ActivityMainBinding
import com.althaafridha.latihannoteapp.db.Note
import com.althaafridha.latihannoteapp.db.NoteDB
import com.althaafridha.latihannoteapp.repository.NoteRepository

class MainActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMainBinding
	private lateinit var noteViewModel: MainViewModel



	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

		val dao = NoteDB.getInstance(applicationContext).noteDao
		val repository = NoteRepository(dao)
		val factory = ViewModelFactory(repository)

		noteViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

		binding.myViewModel = noteViewModel
		binding.lifecycleOwner = this

		initRecyclerView()

	}

	private fun initRecyclerView() {
		binding.recyclerView.layoutManager = LinearLayoutManager(this)
		displaySubscriberList()
	}

	private fun displaySubscriberList() {
		noteViewModel.note.observe(this, Observer {
			Log.i("TAG", "displaySubscriberList: $it")
			binding.recyclerView.adapter =
				NoteAdapter(it) { selectedItem: Note ->
					listItemClicked(selectedItem)
				}
		})
	}
	private fun listItemClicked(note: Note) {
		noteViewModel.initUpdateAndDelete(note)
	}

}