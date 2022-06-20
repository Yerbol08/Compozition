package com.yerbol.compozition.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yerbol.compozition.domain.entity.Level
import java.lang.RuntimeException

class GameViewModelFactory(
    private val application: Level,
    private val level: Application
        ):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)){
            return GameViewModel(level, application) as T
        }
        else{
            throw RuntimeException("Unknown view model class $modelClass")
        }

    }
}