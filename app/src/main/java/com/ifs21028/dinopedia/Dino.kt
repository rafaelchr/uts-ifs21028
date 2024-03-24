package com.ifs21028.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dino(
    var icon: Int,
    var name: String,
    var description: String,
    var characteristics: String,
    var group: String,
    var habitat: String,
    var foodType: String,
    var length: String,
    var height: String,
    var weight: String,
    var weakness: String
) : Parcelable