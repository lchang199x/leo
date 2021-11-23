package com.tptz.leo

import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

var leoAdapter: LeoAdapter? = null

class Leo {
    companion object {
        fun init(adapter: LeoAdapter) {
            leoAdapter = adapter
        }
    }
}

fun handleLoading(
    viewGroup: ViewGroup
) {
    leoAdapter?.loadingView()?.let { loadingView ->
        var parent = viewGroup
        if (parent is RecyclerView) {
            parent.visibility = View.GONE
            parent = parent.parent as ViewGroup
        } else {
            parent.children.forEach {
                it.visibility = View.GONE
            }
        }
        parent.findViewById<View?>(R.id.root_loading_view)?.let {
            parent.removeView(it)
        }
        parent.addView(loadingView)
    }
}

fun handleEmpty(
    viewGroup: ViewGroup,
    tips: String? = "",
    @DrawableRes
    resId: Int = -1,
    listener: OnRefreshListener? = null
) {
    leoAdapter?.emptyView(tips, resId, listener)?.let { emptyView ->
        var parent = viewGroup
        if (parent is RecyclerView) {
            parent.visibility = View.GONE
            parent = parent.parent as ViewGroup
        } else {
            parent.children.forEach {
                it.visibility = View.GONE
            }
        }
        parent.findViewById<View?>(R.id.root_empty_view)?.let {
            parent.removeView(it)
        }
        parent.addView(emptyView)
    }
}

fun handleOffline(
    viewGroup: ViewGroup,
    listener: OnRefreshListener? = null
) {
    leoAdapter?.offlineView(listener)?.let { offlineView ->
        var parent = viewGroup
        if (parent is RecyclerView) {
            parent.visibility = View.GONE
            parent = parent.parent as ViewGroup
            // todo parent.indexOfChild(parent)
        } else {
            parent.children.forEach {
                it.visibility = View.GONE
            }
        }

        parent.findViewById<View?>(R.id.root_offline_view)?.let {
            viewGroup.removeView(it)
        }
        parent.addView(offlineView)
    }
}