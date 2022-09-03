package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val post = Post(
            author = "Нетология. Университет интернет-профессий будущего",
            authorAvatar =  "",
            published = "21 мая в 18:36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            likesCount = 999,
            sharesCount = 999_999,
            viewsCount = 999_999
        )
        with(binding){
            postText.text = post.content
            published.text = post.published
            author.text = post.author
            likesCount.text = counterToText(post.likesCount)
            sharesCount.text = counterToText(post.sharesCount)
            viewedCount.text = counterToText(post.viewsCount)
            likesButton.setOnClickListener {
                post.likedByMe = !post.likedByMe
                likesButton.setImageResource(
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
                likesCount.text = counterToText(post.likesCount)
            }
            sharesButton.setOnClickListener {
                post.sharesCount =  post.sharesCount + 100000
                //post.sharesCount++
                sharesCount.text = counterToText(post.sharesCount)
            }
        }



    }
    private fun counterToText (count: Long): String {
        val result: String
        val resultDouble: Double
        if (count < 0){
            result = "ERROR"
        } else if (count < 1000){
            result = count.toString()
        } else if (count<1_100){
            resultDouble = count.toDouble()/1000
            result = resultDouble.toString().substring(0,1) + "K"
        } else if (count<10_000){
            resultDouble = count.toDouble()/1000
            result = resultDouble.toString().substring(0,3) + "K"
        } else if (count<100_000){
            resultDouble = count.toDouble()/1000
            result = resultDouble.toString().substring(0,2) + "K"
        } else if(count<1_000_000) {
            result = (count.toDouble()/1000).toString().substring(0,3) + "K"
        } else if (count<1_100_000){
            resultDouble = count.toDouble()/1_000_000
            result = resultDouble.toString().substring(0,1) + "M"
        } else if (count<10_000_000){
            resultDouble = count.toDouble()/1_000_000
            result = resultDouble.toString().substring(0,3) + "M"
        } else if (count<100_000_000){
            resultDouble = count.toDouble()/1_000_000
            result = resultDouble.toString().substring(0,2) + "M"
        } else {
            result = (count/1_000_000).toDouble().toString().substring(0,3) + "M"
        }
        return result
    }
}