package ru.netology.nmedia.activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.netology.nmedia.R
import ru.netology.nmedia.activity.NewPostFragment.Companion.textArg
import ru.netology.nmedia.databinding.FragmentNewPostBinding
import ru.netology.nmedia.databinding.FragmentPostElementBinding
import ru.netology.nmedia.databinding.PostCardBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.services.CounterToText
import ru.netology.nmedia.services.PostArg
import ru.netology.nmedia.services.StringArg
import ru.netology.nmedia.viewmodel.PostViewModel

class PostElementFragment : Fragment() {

    private val args by navArgs<PostElementFragmentArgs>()

    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPostElementBinding.inflate(
            inflater,
            container,
            false
        )
        bindLayout(binding)

        return binding.root
    }

    private fun bindLayout(binding: FragmentPostElementBinding){
        val post = args.post
        with(binding.postLayout){
            author.text = post.author
            published.text = post.published
            postText.text = post.content
            likesButton.text = CounterToText.counterToTextFun(post.likesCount)
            sharesButton.text = CounterToText.counterToTextFun(post.sharesCount)
            viewedIcon.text = CounterToText.counterToTextFun(post.viewsCount)
            likesButton.isChecked = post.likedByMe
            if (post.video == null) {
                this.videoGroup.visibility = View.GONE
            } else {
                this.videoGroup.visibility = View.VISIBLE
            }
            likesButton.setOnClickListener {
                viewModel.likeById(post.id)
            }
            sharesButton.setOnClickListener {
                viewModel.shareById(post.id)
                val intent =
                    Intent().putExtra(Intent.EXTRA_TEXT, post.content).setAction(Intent.ACTION_SEND)
                        .setType("text/plain")
                val createChooser = Intent.createChooser(intent, "Choose app")
                startActivity(createChooser)
            }
            dottedMenu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.post_menu)
                    setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.remove -> {
                                viewModel.removeById(post.id)
                                return@setOnMenuItemClickListener true
                            }
                            R.id.post_menu_edit -> {
                                viewModel.edit(post)
                                val text = post.content

                                findNavController().navigate(
                                    R.id.action_postElementFragment_to_newPostFragment,
                                    Bundle().apply {
                                        textArg = text
                                    }
                                )
                                return@setOnMenuItemClickListener true
                            }
                            else -> false
                        }
                    }
                }.show()
            }
        }
    }
}