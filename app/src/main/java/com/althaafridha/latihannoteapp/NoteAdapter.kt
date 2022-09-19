package com.althaafridha.latihannoteapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.althaafridha.latihannoteapp.databinding.ItemNoteBinding
import com.althaafridha.latihannoteapp.db.Note

class NoteAdapter(private val noteList: List<Note>, private val clickListener: (Note) -> Unit) :
	RecyclerView.Adapter<MyViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		val binding: ItemNoteBinding =
			DataBindingUtil.inflate(layoutInflater, R.layout.item_note, parent, false)
		return MyViewHolder(binding)
	}

	override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
		holder.bind(noteList[position], clickListener)
	}

	override fun getItemCount() = noteList.size

}

class MyViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
	fun bind(note: Note, clickListener: (Note) -> Unit) {
		binding.nameTextView.text = note.title
		binding.emailTextView.text = note.desc
		binding.listItemLayout.setOnClickListener {
			clickListener(note)
		}
	}
}
