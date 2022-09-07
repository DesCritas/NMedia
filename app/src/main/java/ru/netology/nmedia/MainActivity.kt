package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.PostCardBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        viewModel.data.observe(this) { posts ->
            binding.container.removeAllViews()
            posts.map { post ->
                PostCardBinding.inflate(layoutInflater, binding.container, true).apply {
                    postText.text = post.content
                    published.text = post.published
                    author.text = post.author
                    likesCount.text = counterToText(post.likesCount)
                    sharesCount.text = counterToText(post.sharesCount)
                    viewedCount.text = counterToText(post.viewsCount)

                    likesButton.setImageResource(
                        if (post.likedByMe) R.drawable.ic_liked else R.drawable.ic_like
                    )
                    likesCount.text = counterToText(post.likesCount)
                    sharesCount.text = counterToText(post.sharesCount)

                    likesButton.setOnClickListener {
                        viewModel.likeById(post.id)
                    }
                    sharesButton.setOnClickListener {
                        viewModel.share()
                    }
                }.root
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
}