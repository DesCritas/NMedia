package ru.netology.nmedia

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.adapter.PostEventListener
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.services.AndroidUtils
import ru.netology.nmedia.viewmodel.PostViewModel

const val blankPost = "Post is blank"
const val newPostId = 0L

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        val adapter = PostAdapter(object : PostEventListener {
            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }

            override fun onShare(post: Post) {
                viewModel.shareById(post.id)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onCancelEdit(post: Post) {
                viewModel.cancelEdit()
            }

        })
        viewModel.edited.observe(this) { post ->
            if (post.id == newPostId) {
                return@observe
            } else {
                binding.groupEditMessage.visibility = View.VISIBLE
                binding.editingMessage.text = post.content
                binding.contentEdit.setText(post.content)
                binding.contentEdit.requestFocus()
            }
        }
        binding.save.setOnClickListener {
            if (binding.contentEdit.text.isNullOrBlank()) {
                Toast.makeText(
                    it.context, blankPost, Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            } else {
                val text = binding.contentEdit.text.toString()
                viewModel.editContent(text)
                viewModel.save()
                binding.groupEditMessage.visibility = View.GONE
                binding.contentEdit.setText("")
                binding.contentEdit.clearFocus()
                AndroidUtils.hideKeyboard(binding.contentEdit)

            }
        }
        binding.editingCancel.setOnClickListener {
            viewModel.cancelEdit()
            binding.groupEditMessage.visibility = View.GONE
            binding.contentEdit.setText("")
            binding.contentEdit.clearFocus()
            AndroidUtils.hideKeyboard(binding.contentEdit)
        }

        binding.container.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)

        }


    }


}