package com.test_app.mvp_bigin.model.network

import android.content.Context

object NetworkStatusFactory {
    fun create(context: Context?) : NetworkStatus = AndroidNetworkStatus(context)
}