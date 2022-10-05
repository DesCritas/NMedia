package ru.netology.nmedia

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.NewPostActivityBinding

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = NewPostActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding.edit.requestFocus()
        //binding.ok.setOn
        binding.save.setOnClickListener {

            if (binding.contentEdit.text.isNullOrBlank()) {
                Toast.makeText(
                    this, blankPost, Toast.LENGTH_SHORT
                ).show()
                setResult(RESULT_CANCELED)
            } else {
                val result =
                    Intent().putExtra(Intent.EXTRA_TEXT, binding.contentEdit.text.toString())
                //val text = binding.contentEdit.text.toString()
                //viewModel.editContent(text)
                //viewModel.save()
                //binding.groupEditMessage.visibility = View.GONE
                //binding.contentEdit.setText("")
                //binding.contentEdit.clearFocus()
                //AndroidUtils.hideKeyboard(binding.contentEdit)
                setResult(RESULT_OK, result)

            }
            finish()

        }

    }
}