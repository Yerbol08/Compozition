package com.yerbol.compozition.data

import com.yerbol.compozition.domain.entity.GameSettings
import com.yerbol.compozition.domain.entity.Level
import com.yerbol.compozition.domain.entity.Question
import com.yerbol.compozition.domain.repository.GameRepository
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object GameRepositoryImpl:GameRepository {
    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1
    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE,sum)
        val option = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        option.add(rightAnswer)
        val from = max (rightAnswer - countOfOptions, MIN_ANSWER_VALUE)
        val to = min(maxSumValue-1, rightAnswer + countOfOptions)
        while (option.size < countOfOptions){
            option.add(Random.nextInt(from, to))
        }
        return Question(sum, visibleNumber, options = option.toList())
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when(level){
            Level.TEST -> {
                GameSettings(
                    10,
                    3,
                    50,
                    8
                )
            }
            Level.EASY -> {
                GameSettings(
                    10,
                    10,
                    70,
                    60
                )
            }
            Level.NORMAL -> {
                GameSettings(
                    15,
                    15,
                    80,
                    40
                )
            }
            Level.HARD -> {
                GameSettings(
                    20,
                    30,
                    90,
                    40
                )
            }
        }
    }
}