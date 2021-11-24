package com.tptz.leo

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
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
) =
    leoAdapter?.loadingView()?.let {
        handleLeo(viewGroup, R.id.root_loading_view, it)
    }

fun handleEmpty(
    viewGroup: ViewGroup,
    tips: String? = "",
    @DrawableRes
    resId: Int = -1,
    listener: OnRefreshListener? = null
) =
    leoAdapter?.emptyView(tips, resId, listener)?.let {
        handleLeo(viewGroup, R.id.root_empty_view, it)
    }

fun handleOffline(
    viewGroup: ViewGroup,
    listener: OnRefreshListener? = null
) =
    leoAdapter?.offlineView(listener)?.let {
        handleLeo(viewGroup, R.id.root_offline_view, it)
    }

private fun handleLeo(viewGroup: ViewGroup, @IdRes id: Int, view: View) {
    var parent = viewGroup
    if (parent is RecyclerView) {
        parent.visibility = View.GONE
        parent = parent.parent as ViewGroup
        parent.findViewById<View?>(id)?.let {
            parent.removeView(it)
        }
        parent.addView(view, viewGroup.layoutParams)
    } else {
        parent.children.forEach {
            it.visibility = View.GONE
        }
        parent.findViewById<View?>(id)?.let {
            parent.removeView(it)
        }
        parent.addView(view, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
    }
}