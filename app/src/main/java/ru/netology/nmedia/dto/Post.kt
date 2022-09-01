package ru.netology.nmedia.dto

/*"id": 1,
"author": "Нетология. Университет интернет-профессий будущего",
"authorAvatar": "@sample/posts_avatars",
"published": "21 мая в 18:36",
"content": "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb"
*/
class Post(
    val id: Int = 0,
    val author: String,
    val authorAvatar: String,
    val published: String,
    val content: String,
    var likedByMe: Boolean = false,
    var likesCount: Int,
    var sharesCount: Int,
    var viewsCount: Int
) {


}