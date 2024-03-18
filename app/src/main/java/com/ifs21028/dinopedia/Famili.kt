package com.ifs21028.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Famili(
    var name: String,
    var icon: Int,
    var description: String,
    var periode: String,
    var characteristic: String,
    var place: String,
    var envi: String,
    var behavior: String,
    var classif: String,
) : Parcelable