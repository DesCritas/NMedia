package ru.netology.nmedia.dto

data class Post(
    val id: Long = 0,
    val author: String,
    val authorAvatar: String,
    val published: String,
    val content: String,
    val likedByMe: Boolean,
    val likesCount: Long,
    val sharesCount: Long,
    val viewsCount: Long
) {


}