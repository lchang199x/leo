package com.tptz.leo

import android.view.View
import androidx.annotation.DrawableRes

interface LeoAdapter {
    fun loadingView(): View?

    fun emptyView(
        tips: String?,
        @DrawableRes
        resId: Int,
        listener: OnRefreshListener?
    ): View?

    fun offlineView(listener: OnRefreshListener?): View?

    fun interface OnRefreshListener {
        fun onRefresh()
    }
}