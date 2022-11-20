package ru.netology.nmedia.dto

import android.os.Parcel
import android.os.Parcelable

const val defaultPostId = 0L
const val emptyString = ""

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
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(author)
        parcel.writeString(authorAvatar)
        parcel.writeString(published)
        parcel.writeString(content)
        parcel.writeByte(if (likedByMe) 1 else 0)
        parcel.writeLong(likesCount)
        parcel.writeLong(sharesCount)
        parcel.writeLong(viewsCount)
        parcel.writeString(video)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Post> {
        override fun createFromParcel(parcel: Parcel): Post {
            return Post(parcel)
        }

        override fun newArray(size: Int): Array<Post?> {
            return arrayOfNulls(size)
        }
    }
}