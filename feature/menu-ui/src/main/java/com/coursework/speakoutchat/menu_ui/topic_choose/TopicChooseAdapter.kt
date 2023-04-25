package com.coursework.speakoutchat.menu_ui.topic_choose

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coursework.speakoutchat.menu_ui.databinding.ItemTopicChooseBinding

class TopicChooseAdapter(private val items: List<DialogTopic>) :
    RecyclerView.Adapter<TopicChooseAdapter.TopicChooseViewHolder>() {

    var onItemClicked: ((DialogTopic) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicChooseViewHolder {
        val binding =
            ItemTopicChooseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopicChooseViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TopicChooseViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class TopicChooseViewHolder(private val binding: ItemTopicChooseBinding) :
        RecyclerView.ViewHolder(binding.root) {

            init {
                binding.root.setOnClickListener {
                    if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                        onItemClicked?.invoke(items[bindingAdapterPosition])
                    }
                }
            }

            fun bind(dialogTopic: DialogTopic) {
                with(binding.root.context) {
                    binding.textItemTopic.text = getString(dialogTopic.title)
                }
            }

    }
}