package ru.netology.nmedia.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.netology.nmedia.R
import ru.netology.nmedia.activity.NewPostFragment.Companion.textArg
import ru.netology.nmedia.databinding.FragmentNewPostBinding
import ru.netology.nmedia.databinding.FragmentPostElementBinding
import ru.netology.nmedia.databinding.PostCardBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.services.PostArg
import ru.netology.nmedia.services.StringArg

class PostElementFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = PostCardBinding.inflate(
            inflater,
            container,
            false
        )

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_post_element, container, false)
        return binding.root
    }
    companion object {
        var Bundle.postToCast: Post? by PostArg
    }

}