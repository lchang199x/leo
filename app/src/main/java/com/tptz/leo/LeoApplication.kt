package com.tptz.leo

import android.app.Application
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * @author Created by Chang Liu on 2021/11/22
 */
class LeoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Leo.init(object : LeoAdapter {
            override fun loadingView(): View? = View.inflate(
                this@LeoApplication,
                R.layout.base_loading_view,
                null
            )

            override fun emptyView(
                tips: String?,
                resId: Int,
                listener: OnRefreshListener?
            ): View? = View.inflate(
                this@LeoApplication,
                R.layout.base_empty_view,
                null
            ).apply {
                if (!tips.isNullOrEmpty()) {
                    findViewById<TextView>(R.id.tv_content).text = tips
                }

                findViewById<View>(R.id.tv_refresh).apply {
                    if (listener != null) {
                        setOnClickListener { listener.onRefresh() }
                    }
                }

                findViewById<ImageView>(R.id.iv_video_default).apply {
                    if (resId != -1) {
                        setImageResource(resId)
                    }
                }
            }

            override fun offlineView(
                listener: OnRefreshListener?
            ): View? = View.inflate(
                this@LeoApplication,
                R.layout.base_offline_view,
                null
            ).apply {
                findViewById<TextView>(R.id.tv_refresh)
                    .setOnClickListener { listener?.onRefresh() }
            }

        })
    }
}