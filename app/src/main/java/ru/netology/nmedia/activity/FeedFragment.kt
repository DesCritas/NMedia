package ru.netology.nmedia.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.EditPostResultContract
import ru.netology.nmedia.R
import ru.netology.nmedia.activity.NewPostFragment.Companion.textArg
import ru.netology.nmedia.activity.PostElementFragment.Companion.postToCast

import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.adapter.PostEventListener
import ru.netology.nmedia.databinding.FragmentFeedBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel
import ru.netology.nmedia.viewmodel.empty

const val blankPost = "Post is blank"

class FeedFragment : Fragment() {

    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //super.onCreate(savedInstanceState)
        val binding = FragmentFeedBinding.inflate(inflater, container, false)
        //setContentView(binding.root)


        //val newPostLauncher = registerForActivityResult(NewPostResultContract()) { text ->
        //    text ?: return@registerForActivityResult
        //    viewModel.editContent(text)
        //    viewModel.save()
        //}
//
        //val editPostLauncher = registerForActivityResult(EditPostResultContract()) { text ->
        //    text ?: return@registerForActivityResult
        //    viewModel.editContent(text)
        //    viewModel.save()
        //}


        val adapter = PostAdapter(object : PostEventListener {
            override fun onEdit(post: Post) {
                viewModel.edit(post)
                val text = post.content

                //editPostLauncher.launch(post)
                findNavController().navigate(
                    R.id.action_feedFragment_to_newPostFragment,
                    Bundle().apply {
                        textArg = text
                    }
                )
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
            override fun onPostClick(post:Post){
                binding.container.setOnClickListener{
                    findNavController().navigate(
                        R.id.action_feedFragment_to_postElementFragment,
                        Bundle().apply { postToCast = post }
                    )
                }
            }

        })
        binding.container.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) { posts ->
            adapter.submitList(posts)
        }

        binding.newPostButton.setOnClickListener {
            //newPostLauncher.launch()
            findNavController().navigate(R.id.action_feedFragment_to_newPostFragment)

        }

        //   findNavController().navigate(
        //       R.id.action_feedFragment_to_postElementFragment,
        //       Bundle().apply { postToCast }
        //   )


        return  binding.root
    }

   // override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
   //     super.onViewCreated(view, savedInstanceState)
   //     loadArguments()
   //     if (savedInstanceState != null){
   //         restoreState(savedInstanceState)
   //     } else {
   //         initState()
   //     }
   // }
   // private fun restoreState(savedInstanceState: Bundle){
   //     savedInstanceState.apply {  }
   // }
   // private fun initState(){}
//
   // private fun loadArguments(){
   //     arguments?.let{
   //
   //     }
   // }
//
   // override fun onSaveInstanceState(outState: Bundle) {
   //     super.onSaveInstanceState(outState)
   //     outState.apply {
   //         putString("editing text", "text")
   //     }
   // }



}