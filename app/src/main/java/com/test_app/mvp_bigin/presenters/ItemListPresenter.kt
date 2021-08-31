package com.test_app.mvp_bigin.presenters

import com.test_app.mvp_bigin.views.ItemView
import com.test_app.mvp_bigin.views.UserItemView

interface ItemListPresenter<View : ItemView> {
    var itemClickedListener: ((View) -> Unit)?
    fun bindView(view: View)
    fun getCount(): Int
}

interface UserItemListPresenter : ItemListPresenter<UserItemView>
