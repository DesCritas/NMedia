package ru.netology.nmedia.dto

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

const val defaultPostId = 0L
const val emptyString = ""

@Parcelize
data class Post(
    val id: Long = defaultPostId,
    val author: String?= emptyString,
    val authorAvatar: String? = emptyString,
    val published: String?= emptyString,
    val content: String?= emptyString,
    val likedByMe: Boolean,
    val likesCount: Long,
    val sharesCount: Long,
    val viewsCount: Long,
    val video: String? = null
) : Parcelable {

}