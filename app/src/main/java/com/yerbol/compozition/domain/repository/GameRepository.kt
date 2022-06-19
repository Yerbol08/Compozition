package com.yerbol.compozition.domain.repository

import com.yerbol.compozition.domain.entity.GameSettings
import com.yerbol.compozition.domain.entity.Level
import com.yerbol.compozition.domain.entity.Question

interface GameRepository {
    fun generateQuestion(
        maxSumValue:Int,
        countOfOptions:Int
    ):Question
    fun getGameSettings(level: Level):GameSettings
}