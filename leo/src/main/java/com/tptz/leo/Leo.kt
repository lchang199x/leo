package com.tptz.leo

import android.util.Log
import android.util.LruCache
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import java.lang.ref.WeakReference

var leoAdapter: LeoAdapter? = null
val cachedViews = LruCache<String, WeakReference<View>>(8)

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
    val key = "loading_view"
    var loadingView = cachedViews[key]?.get()
    if (loadingView == null) {
        loadingView = leoAdapter?.loadingView()
        cachedViews.put(key, WeakReference(loadingView))
    }

    loadingView?.let {
        handleLeo(viewGroup, it)
    }
}

fun handleEmpty(
    viewGroup: ViewGroup,
    tips: String? = "",
    @DrawableRes
    resId: Int = -1,
    listener: OnRefreshListener? = null
) {
    val key = StringBuilder("empty_view").run {
        if (!tips.isNullOrBlank()) append("_$tips")
        if (resId != -1) append("_$resId")
        if (listener != null) append("_${listener.hashCode()}")
        toString()
    }

    var emptyView = cachedViews[key]?.get()
    if (emptyView == null) {
        emptyView = leoAdapter?.emptyView(tips, resId, listener)
        cachedViews.put(key, WeakReference(emptyView))
    }

    emptyView?.let {
        handleLeo(viewGroup, it)
    }
}

fun handleOffline(
    viewGroup: ViewGroup,
    listener: OnRefreshListener? = null
) {
    val key = StringBuilder("offline_view").run {
        if (listener != null) append("_${listener.hashCode()}")
        toString()
    }
    var offlineView = cachedViews[key]?.get()
    if (offlineView == null) {
        offlineView = leoAdapter?.offlineView(listener)
        cachedViews.put(key, WeakReference(offlineView))
    }

    offlineView?.let {
        handleLeo(viewGroup, it)
    }
}

private fun handleLeo(viewGroup: ViewGroup, view: View) {
    try {
        viewGroup.visibility = View.GONE
        val parent = viewGroup.parent as ViewGroup
        cachedViews.snapshot().values.forEach {
            it.get()?.let { view ->
                parent.removeView(view)
            }
        }
        parent.addView(view, viewGroup.layoutParams)
    } catch (e: Throwable) {
        Log.e("Leo", "Unable to add the abnormal layout to view hierarchy.", e)
    }
}