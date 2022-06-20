package com.yerbol.compozition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.yerbol.compozition.R
import com.yerbol.compozition.databinding.FragmentGameFinishedBinding
import com.yerbol.compozition.domain.entity.GameResult
import java.lang.RuntimeException

class GameFinishedFragment:Fragment() {


    private var _binding:FragmentGameFinishedBinding? = null
    private val binding:FragmentGameFinishedBinding
    get() = _binding?:throw RuntimeException("FragmentGameFinished == null")

    val args by navArgs<GameFinishedFragmentArgs>()

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
        setClickListener()
        chooseSmile()
        setText()
    }

    private fun setClickListener(){
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }


    }
    private fun setText(){
        binding.tvRequiredAnswers.text = String.format(
            getString(R.string.required_score),
            args.gameResult.gameSettings.minCountOfRight)
        binding.tvScoreAnswers.text = String.format(
            getString(R.string.score_answers),
            args.gameResult.countOfRightAnswers)
        binding.tvScorePercentage.text = String.format(
            getString(R.string.required_percentage),
            percentOfRight())
        binding.tvRequiredPercentage.text = String.format(
            getString(R.string.score_percentage),
            args.gameResult.gameSettings.minPercentOfRightAnswers)
    }

    private fun percentOfRight() = with(args.gameResult){
        if (countOfQuestions == 0){
            0
        }
        else{
            ((countOfRightAnswers  / countOfQuestions.toDouble())*100).toInt()
        }
    }

    private fun chooseSmile(){
        if (args.gameResult.winner){
            binding.emojiResult.setImageResource(R.drawable.ic_smile)
        }
        else{
            binding.emojiResult.setImageResource(R.drawable.ic_sad)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun retryGame(){
        findNavController().popBackStack()
    }

}