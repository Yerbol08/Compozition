package com.yerbol.compozition.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSettings (
    val maxSumValue:Int,
    val minCountOfRight:Int,
    val minPercentOfRightAnswers:Int,
    val gameTimeInSeconds:Int
    ): Parcelable