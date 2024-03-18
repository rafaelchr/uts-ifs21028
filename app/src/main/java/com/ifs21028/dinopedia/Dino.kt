package com.ifs21028.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dino(
    var name: String,
    var description: String,
    var characteristics: String,
    var foodType: String,
    var period: String,
    var habitat: String,
    var behavior: String,
    var size: String,
    var weakness: String
) : Parcelable