package com.test_app.mvp_bigin.model.storage

import com.test_app.mvp_bigin.utils.schedulers.SchedulersFactory

object StorageFactory {
    fun create(): Storage = RoomRepositoryImpl(RoomDB.getInstance() as RoomDB, SchedulersFactory.create())
}