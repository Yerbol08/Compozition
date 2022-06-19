package com.yerbol.compozition.domain.usecases

import com.yerbol.compozition.domain.entity.GameSettings
import com.yerbol.compozition.domain.entity.Level
import com.yerbol.compozition.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(level: Level):GameSettings{
        return repository.getGameSettings(level)
    }
}