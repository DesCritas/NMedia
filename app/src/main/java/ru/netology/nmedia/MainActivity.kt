package ru.netology.nmedia

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.adapter.PostEventListener
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel

const val blankPost = "Post is blank"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()

        val newPostLauncher = registerForActivityResult(NewPostResultContract()) { text ->
            text ?: return@registerForActivityResult
            viewModel.editContent(text)
            viewModel.save()
        }

        val editPostLauncher = registerForActivityResult(EditPostResultContract()) { text ->
            text ?: return@registerForActivityResult
            viewModel.editContent(text)
            viewModel.save()
        }


        val adapter = PostAdapter(object : PostEventListener {
            override fun onEdit(post: Post) {
                viewModel.edit(post)
                editPostLauncher.launch(post)
            }

            override fun onShare(post: Post) {
                viewModel.shareById(post.id)

                val intent =
                    Intent().putExtra(Intent.EXTRA_TEXT, post.content).setAction(Intent.ACTION_SEND)
                        .setType("text/plain")
                val createChooser = Intent.createChooser(intent, "Choose app")
                startActivity(createChooser)

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

            override fun onVideo(post: Post) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.video))
                val createChooser = Intent.createChooser(intent, "Choose app")
                startActivity(createChooser)

            }

        })
        //viewModel.edited.observe(this) { post ->
        //    if (post.id == newPostId) {
        //        return@observe
        //    } else {
        //        binding.groupEditMessage.visibility = View.VISIBLE
        //        binding.editingMessage.text = post.content
        //        binding.contentEdit.setText(post.content)
        //        binding.contentEdit.requestFocus()
        //    }
        //}
        //binding.save.setOnClickListener {
        //    if (binding.contentEdit.text.isNullOrBlank()) {
        //        Toast.makeText(
        //            it.context, blankPost, Toast.LENGTH_SHORT
        //        ).show()
        //        return@setOnClickListener
        //    } else {
        //        val text = binding.contentEdit.text.toString()
        //        viewModel.editContent(text)
        //        viewModel.save()
        //        binding.groupEditMessage.visibility = View.GONE
        //        binding.contentEdit.setText("")
        //        binding.contentEdit.clearFocus()
        //        AndroidUtils.hideKeyboard(binding.contentEdit)
        //    }
        //}
        //binding.editingCancel.setOnClickListener {
        //    viewModel.cancelEdit()
        //    binding.groupEditMessage.visibility = View.GONE
        //    binding.contentEdit.setText("")
        //    binding.contentEdit.clearFocus()
        //    AndroidUtils.hideKeyboard(binding.contentEdit)
        //}
        binding.container.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        binding.newPostButton.setOnClickListener {
            newPostLauncher.launch()
        }


    }


}