package ru.netology.nmedia.services

import androidx.recyclerview.widget.DiffUtil
import ru.netology.nmedia.dto.Post

class PostDiffUtil : DiffUtil.ItemCallback<Post>() {

    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}