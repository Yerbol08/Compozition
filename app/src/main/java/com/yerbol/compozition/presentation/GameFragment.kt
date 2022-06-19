package com.yerbol.compozition.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yerbol.compozition.R
import com.yerbol.compozition.databinding.FragmentGameBinding
import com.yerbol.compozition.domain.entity.GameResult
import com.yerbol.compozition.domain.entity.GameSettings
import com.yerbol.compozition.domain.entity.Level
import java.lang.RuntimeException
import java.util.*

class GameFragment:Fragment() {

    private lateinit var level:Level
    private var _binding : FragmentGameBinding? = null
    private val binding:FragmentGameBinding
    get() = _binding?:throw RuntimeException("FragmentGame == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvOption1.setOnClickListener{
            launchGameFinished(GameResult(
                true,
                25,
                30,
                GameSettings(0, 0,0,0)))
        }
    }

    private fun parseArgs(){
        level = requireArguments().getSerializable(KEY_LEVEL) as Level
    }

    private fun launchGameFinished(gameResult: GameResult){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container_view, GameFinishedFragment.newInstance(gameResult = gameResult))
            .addToBackStack(null)
            .commit()
    }

    companion object{
        private const val KEY_LEVEL = "level"
        const val NAME = "GameFragment"
        fun newInstance(level: Level):GameFragment{
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_LEVEL, level)
                }
            }
        }
    }

}