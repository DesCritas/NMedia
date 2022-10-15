package ru.netology.nmedia.dto

const val defaultPostId = 0L
const val emptyString = ""

data class Post(
    val id: Long = defaultPostId,
    val author: String,
    val authorAvatar: String = emptyString,
    val published: String,
    val content: String,
    val likedByMe: Boolean,
    val likesCount: Long,
    val sharesCount: Long,
    val viewsCount: Long,
    val video: String? = null
)