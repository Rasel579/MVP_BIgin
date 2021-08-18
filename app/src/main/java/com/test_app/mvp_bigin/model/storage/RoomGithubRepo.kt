package com.test_app.mvp_bigin.model.storage

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = RoomGithubUser::class,
            parentColumns = ["reposUrl"],
            childColumns = ["userUrl"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RoomGithubRepo(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val name: String?,
    val date: String?,
    val language: String?,
    val forksCount: Int?,
    val userUrl: String?,
    val url: String?
)
