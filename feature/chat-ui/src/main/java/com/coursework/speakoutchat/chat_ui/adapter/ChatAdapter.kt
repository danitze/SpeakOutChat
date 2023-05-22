package com.coursework.speakoutchat.chat_ui.adapter

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.coursework.speakoutchat.chat_domain_data.data.MessageType
import com.coursework.speakoutchat.chat_domain_data.data.MessageUiModel
import com.coursework.speakoutchat.chat_ui.R
import com.coursework.speakoutchat.chat_ui.databinding.ItemMessageBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatAdapter(context: Context) :
    ListAdapter<MessageUiModel, ChatAdapter.ChatViewHolder>(MessageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding =
            ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ChatViewHolder(private val binding: ItemMessageBinding) : ViewHolder(binding.root) {

        fun bind(position: Int) {
            val message = getItem(position)
            with(binding.layoutMessage) {
                when (message.messageType) {
                    MessageType.MY_MESSAGE -> {
                        gravity = Gravity.END
                        background = ContextCompat
                            .getDrawable(context, R.drawable.background_my_message)
                    }
                    MessageType.PARTNER_MESSAGE -> {
                        gravity = Gravity.START
                        background = ContextCompat
                            .getDrawable(context, R.drawable.background_partner_message)
                    }
                }
            }
            binding.textMessageContent.text = message.content
            binding.textTime.text = extractTime(message.date)
        }

        private fun extractTime(date: Date): String {
            val simpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            return simpleDateFormat.format(date)
        }

    }
}

class MessageDiffCallback : DiffUtil.ItemCallback<MessageUiModel>() {
    override fun areItemsTheSame(oldItem: MessageUiModel, newItem: MessageUiModel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MessageUiModel, newItem: MessageUiModel): Boolean =
        oldItem == newItem

}