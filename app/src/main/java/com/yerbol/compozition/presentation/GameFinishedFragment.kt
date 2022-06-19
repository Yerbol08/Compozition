package com.yerbol.compozition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.yerbol.compozition.R
import com.yerbol.compozition.databinding.FragmentGameFinishedBinding
import com.yerbol.compozition.domain.entity.GameResult
import java.lang.RuntimeException

class GameFinishedFragment:Fragment() {

    private lateinit var gameResult: GameResult
    private var _binding:FragmentGameFinishedBinding? = null
    private val binding:FragmentGameFinishedBinding
    get() = _binding?:throw RuntimeException("FragmentGameFinished == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonRetry.setOnClickListener {

        }

        val callback = object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                retryGame()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun parseArgs(){
        gameResult = requireArguments().getSerializable(KAY_GAME_RESULT) as GameResult
    }

    private fun retryGame(){
        requireActivity().supportFragmentManager.popBackStack(GameFragment.NAME,
            FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    companion object{
        private const val KAY_GAME_RESULT = "game_result"
        fun newInstance(gameResult: GameResult):GameFinishedFragment{
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KAY_GAME_RESULT, gameResult)
                }
            }
        }
    }
}