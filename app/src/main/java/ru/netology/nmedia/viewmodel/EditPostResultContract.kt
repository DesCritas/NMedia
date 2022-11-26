package ru.netology.nmedia

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import ru.netology.nmedia.activity.NewPostFragment
import ru.netology.nmedia.dto.Post


class EditPostResultContract : ActivityResultContract<Post, String?>() {

    override fun createIntent(context: Context, input: Post): Intent {
        val intent = Intent(context, NewPostFragment::class.java)
        val postText = input.content
        intent.putExtra("Post Text" ,postText)
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? =
        intent?.getStringExtra(Intent.EXTRA_TEXT)
}