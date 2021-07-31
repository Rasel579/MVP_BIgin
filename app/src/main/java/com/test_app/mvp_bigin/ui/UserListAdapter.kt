package com.test_app.mvp_bigin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test_app.mvp_bigin.databinding.ItemUserLayoutBinding
import com.test_app.mvp_bigin.presentation.UserItemListPresenter
import com.test_app.mvp_bigin.views.UserItemView

class UserListAdapter(val presenter : UserItemListPresenter) :
    RecyclerView.Adapter<UserListAdapter.UserItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder =
        UserItemViewHolder(ItemUserLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)).apply {
                itemView.setOnClickListener {
                    presenter.itemClickedListener()?.invoke(this)
                }
        }


    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount(): Int = presenter.getCount()

    inner class UserItemViewHolder(binding: ItemUserLayoutBinding) :
        RecyclerView.ViewHolder(binding.root), UserItemView{
        override fun setLogin(login: String) {
            TODO("Not yet implemented")
        }

        override var pos = -1

    }
}