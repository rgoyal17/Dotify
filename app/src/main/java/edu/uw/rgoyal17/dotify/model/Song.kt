package edu.uw.rgoyal17.dotify.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Song (
    val id: String,
    val title: String,
    val artist: String,
    val durationMillis: Int,
    val smallImageURL: String,
    val largeImageURL: String
): Parcelable