package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.PostCardBinding
import ru.netology.nmedia.dto.Post


class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    var onLikeListener: ((Post) -> Unit)? = null
    var onShareListener: ((Post) -> Unit)? = null

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
            likesCount.text = counterToText(post.likesCount)
            sharesCount.text = counterToText(post.sharesCount)
            viewedCount.text = counterToText(post.viewsCount)
            likesButton.setImageResource(
                if (post.likedByMe) R.drawable.ic_liked else R.drawable.ic_like
            )
            likesButton.setOnClickListener {
                onLikeListener?.invoke(post)
            }
            sharesButton.setOnClickListener {
                onShareListener?.invoke(post)
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


    var list = emptyList<Post>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = list[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = list.size


}