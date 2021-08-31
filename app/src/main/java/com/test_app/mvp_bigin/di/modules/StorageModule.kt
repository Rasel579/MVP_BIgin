package com.test_app.mvp_bigin.di.modules

import android.content.Context
import androidx.room.Room
import com.test_app.mvp_bigin.model.storage.RoomDB
import dagger.Module
import dagger.Provides

@Module
class StorageModule {
    @Provides
    fun provideGithubStorageDatabase(context: Context): RoomDB =
        Room.databaseBuilder(context, RoomDB::class.java, "github.db").build()
}
