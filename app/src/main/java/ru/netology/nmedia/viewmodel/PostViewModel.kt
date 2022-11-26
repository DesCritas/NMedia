package ru.netology.nmedia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.db.AppDb
import ru.netology.nmedia.db.AppDbRoom
//import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryRoomImpl
//import ru.netology.nmedia.repository.PostRepositoryFileImpl
import ru.netology.nmedia.repository.PostRepositorySQLiteImpl

//import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl
//import ru.netology.nmedia.repository.PostRepositorySharedPrefsImpl

val empty = Post(
    0, "", "", "",
    //authorAvatar ="",
    false, 0, 0, 0
)

class PostViewModel(app: Application):  AndroidViewModel(app) {
    private val repository: PostRepository = PostRepositoryRoomImpl(
        AppDbRoom.getInstance(context = app).postDao()
    )
    //=PostRepositorySQLiteImpl(
    //AppDb.getInstance(context = app).postDao
    //)
        // PostRepositoryFileImpl(app)
    val data = repository.getAll()
    val edited = MutableLiveData(empty)

    fun likeById(id: Long) = repository.likeById(id)
    fun shareById(id: Long) = repository.shareById(id)
    fun removeById(id: Long) = repository.removeById(id)


    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun cancelEdit(): Post? {
        return edited.value
    }

    fun editContent(content: String) {
        edited.value?.let {
            val trimmed = content.trim()
            if (trimmed == it.content) {
                return
            } else {
                edited.value = it.copy(content = trimmed)
            }
        }
    }
}