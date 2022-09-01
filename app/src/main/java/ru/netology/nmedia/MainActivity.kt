package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.services.CounterService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val counter: CounterService = CounterService()

        val post = Post(
            author = "Нетология. Университет интернет-профессий будущего",
            authorAvatar =  "",
            published = "21 мая в 18:36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            likesCount = 0,
            sharesCount = 999,
            viewsCount = 999_999
        )
        binding.postText.text = post.content
        binding.published.text = post.published
        binding.author.text = post.author
        binding.likesCount.text = post.likesCount.toString()
        binding.likesButton.setOnClickListener {
            post.likedByMe = !post.likedByMe
            binding.likesButton.setImageResource(
                if(post.likedByMe){
                    R.drawable.ic_liked
                } else {
                    R.drawable.ic_like
                }
            )
            if(post.likedByMe){
                post.likesCount++
            } else {
                post.likesCount--
            }
            binding.likesCount.text = counterToText(post.likesCount)
        }


    }
    fun counterToText (count: Int): String {
        return count.toString()
    }
}