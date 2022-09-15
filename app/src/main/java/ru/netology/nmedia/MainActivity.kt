package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.adapter.PostEventListener
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.services.AndroidUtils
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        val adapter = PostAdapter(
            object : PostEventListener{
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

            }
        )

        viewModel.edited.observe(this){edited->
            if (edited.id == 0L){
                return@observe
            } else {
                binding.contentEdit.setText(edited.content)
                binding.contentEdit.requestFocus()
            }
        }

        binding.container.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)

        }

       //adapter.onLikeListener = {
       //    viewModel.likeById(it.id)
       //}
       //adapter.onShareListener = {
       //    viewModel.shareById(it.id)
       //}
       //adapter.onRemoveListener = {
       //    viewModel.removeById(it.id)
       //}

        binding.save.setOnClickListener{
            if(binding.contentEdit.text.isNullOrBlank()){
                Toast.makeText(it.context, "Post is blank", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else{
                val text = binding.contentEdit.text.toString()
                viewModel.editContent(text)
                viewModel.save()

                binding.contentEdit.clearFocus()
                AndroidUtils.hideKeyboard(binding.contentEdit)
                binding.contentEdit.setText("")
            }
        }


    }


}