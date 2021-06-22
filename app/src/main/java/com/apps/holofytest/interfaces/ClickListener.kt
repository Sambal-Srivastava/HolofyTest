package com.apps.holofytest.interfaces

import android.view.View

interface ClickListener {
    fun onItemClick(
        position: Int,
        v: View?,
        timestamp: String?
    )

}