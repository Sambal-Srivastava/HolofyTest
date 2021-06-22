package com.apps.holofytest.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
 class VideoDataModel(
    //===========my classes===================
    val videoTitle: String,
    val videoURL: String,
    val videoThumbnail: String,
    val videodescription: String
) : Parcelable