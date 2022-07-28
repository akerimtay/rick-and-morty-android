package com.akerimtay.rickandmorty.episode.presentation.model

import android.os.Parcelable
import androidx.annotation.StringRes
import com.akerimtay.rickandmorty.episode.R
import kotlinx.parcelize.Parcelize

@Parcelize
internal enum class PageType(
    val seasonCode: String,
    @StringRes val titleResId: Int
) : Parcelable {

    SEASON_1("S01", R.string.season_1),
    SEASON_2("S02", R.string.season_2),
    SEASON_3("S03", R.string.season_3),
    SEASON_4("S04", R.string.season_4),
    SEASON_5("S05", R.string.season_5),
}