package com.test_app.mvp_bigin.utils.schedulers

import io.reactivex.rxjava3.core.Scheduler

interface Schedulers {
    fun main(): Scheduler
    fun background(): Scheduler
}