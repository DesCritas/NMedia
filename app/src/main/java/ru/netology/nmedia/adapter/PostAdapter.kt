package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.PostCardBinding
import ru.netology.nmedia.services.PostDiffUtil
import ru.netology.nmedia.dto.Post as Post

interface PostEventListener {
    fun onEdit(post: Post)
    fun onShare(post: Post)
    fun onRemove(post: Post)
    fun onLike(post: Post)
    fun onCancelEdit(post: Post)
}


class PostAdapter(
    private val listener: PostEventListener
) : ListAdapter<Post, PostAdapter.PostViewHolder>(PostDiffUtil()) {

    inner class PostViewHolder(
        private val binding: PostCardBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            bindLayout(binding, post)
        }
    }

    private fun bindLayout(binding: PostCardBinding, post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            postText.text = post.content
            likesButton.text = counterToText(post.likesCount)
            sharesButton.text = counterToText(post.sharesCount)
            viewedCount.text = counterToText(post.viewsCount)
            likesButton.isChecked = post.likedByMe
            likesButton.setOnClickListener {
                listener.onLike(post)
            }
            sharesButton.setOnClickListener {
                listener.onShare(post)
            }
            dottedMenu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.post_menu)
                    setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.remove -> {
                                listener.onRemove(post)
                                return@setOnMenuItemClickListener true
                            }
                            R.id.post_menu_edit -> {
                                listener.onEdit(post)
                                return@setOnMenuItemClickListener true
                            }
                            else -> false
                        }
                    }
                }.show()
            }
        }
    }


    private fun counterToText(count: Long): String {
        val result: String
        val resultDouble: Double
        if (count < 0) {
            result = "ERROR"
        } else if (count < 1000) {
            result = count.toString()
        } else if (count < 1_100) {
            resultDouble = count.toDouble() / 1000
            result = resultDouble.toString().substring(0, 1) + "K"
        } else if (count < 10_000) {
            resultDouble = count.toDouble() / 1000
            result = resultDouble.toString().substring(0, 3) + "K"
        } else if (count < 100_000) {
            resultDouble = count.toDouble() / 1000
            result = resultDouble.toString().substring(0, 2) + "K"
        } else if (count < 1_000_000) {
            result = (count.toDouble() / 1000).toString().substring(0, 3) + "K"
        } else if (count < 1_100_000) {
            resultDouble = count.toDouble() / 1_000_000
            result = resultDouble.toString().substring(0, 1) + "M"
        } else if (count < 10_000_000) {
            resultDouble = count.toDouble() / 1_000_000
            result = resultDouble.toString().substring(0, 3) + "M"
        } else if (count < 100_000_000) {
            resultDouble = count.toDouble() / 1_000_000
            result = resultDouble.toString().substring(0, 2) + "M"
        } else {
            result = (count / 1_000_000).toDouble().toString().substring(0, 3) + "M"
        }

        return result
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}