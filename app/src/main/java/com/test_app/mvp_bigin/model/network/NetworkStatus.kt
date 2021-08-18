package com.test_app.mvp_bigin.model.network

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface NetworkStatus {
    fun onLine(): Observable<Boolean>
    fun onLineSingle(): Single<Boolean>
}