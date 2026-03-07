package com.example.robot

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kotlin.getValue
import kotlin.math.cos
const val EXTRA_ROBOT_ENERGY = "EXTRA_ROBOT_ENERGY"
const val EXTRA_ROBOT_PURCHASE_MADE = "EXTRA_ROBOT_ENERGY_MADE"
const val EXTRA_CURRENT_TURN = "EXTRA_CURRENT_TURN"
class RobotPurchase : AppCompatActivity() {
    private lateinit var rewardA : Button
    private lateinit var rewardB : Button
    private lateinit var rewardC : Button
    private lateinit var rewardATotal : TextView
    private lateinit var rewardBTotal : TextView
    private lateinit var rewardCTotal : TextView
    private lateinit var whiteRobotImg: ImageView
    private lateinit var balanceTotal : TextView
    private val robotViewModel : RobotViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Find this linking line
        setContentView(R.layout.activity_robot_purchase)

        rewardA = findViewById(R.id.rewardA)
        rewardB = findViewById(R.id.rewardB)
        rewardC = findViewById(R.id.rewardC)
        rewardATotal = findViewById(R.id.rewardATotal)
        rewardBTotal = findViewById(R.id.rewardBTotal)
        rewardCTotal = findViewById(R.id.rewardCTotal)
        balanceTotal = findViewById(R.id.balanceTotal)
        whiteRobotImg = findViewById(R.id.white_robot)

        robotViewModel.setEnergy(
            intent.getIntExtra(EXTRA_ROBOT_ENERGY, 0)
        )
        val currentTurn = intent.getIntExtra(EXTRA_CURRENT_TURN, 1)

        balanceTotal.setText(robotViewModel.robotEnergy.toString())

        val rewards = robotViewModel.selectedRewards
        val button = listOf(rewardA, rewardB, rewardC)
        val costViews = listOf(rewardATotal, rewardBTotal, rewardCTotal)

        for (i in 0..2) {
            button[i].setText(rewards[i].nameRes)
            costViews[i].text = rewards[i].cost.toString()
        }

        refreshButtonStates()

        rewardA.setOnClickListener { makePurchase(0) }
        rewardB.setOnClickListener { makePurchase(1) }
        rewardC.setOnClickListener { makePurchase(2) }

        toggleImage(currentTurn)
    }

    private fun refreshButtonStates() {
        val buttons = listOf(rewardA, rewardB, rewardC)
        for (i in 0..2) {
            buttons[i].isEnabled = !robotViewModel.purchased.contains(i)
        }
    }


    private fun makePurchase(index : Int) {
        val rewards = robotViewModel.selectedRewards[index]
        when {
            robotViewModel.purchased.contains(index) -> {
                Toast.makeText(this, R.string.insufficient, Toast.LENGTH_SHORT).show()
            }
            robotViewModel.robotEnergy >= rewards.cost -> {
                val name = getString(rewards.nameRes)
                robotViewModel.makePurchase(index, name)
                balanceTotal.text = robotViewModel.robotEnergy.toString()
                Toast.makeText(this, "$name ${getString(R.string.purchased)}", Toast.LENGTH_SHORT).show()
                refreshButtonStates()
                sendResults()
            }
            else -> {Toast.makeText(this, R.string.insufficient, Toast.LENGTH_SHORT).show()}
        }
//        if (robotViewModel.robotEnergy >= costOfPurchase) {
//            val s1 = getString(rewards[costOfPurchase - 1])
//            val s2 = getString(R.string.purchased)
//            val s3 = s1 + " " + s2
//            robotViewModel.makePurchase(costOfPurchase)
//            balanceTotal.setText(robotViewModel.robotEnergy.toString())
//            Toast.makeText(this, s3, Toast.LENGTH_SHORT).show()
//            setWhichPurchaseMade(costOfPurchase)
//        } else {
//            Toast.makeText(this, R.string.insufficient, Toast.LENGTH_SHORT).show()
//        }
    }

    private fun sendResults() {
        val resultIntent = Intent().apply {
            putExtra(EXTRA_ROBOT_ENERGY, robotViewModel.robotEnergy)
            putExtra(EXTRA_ROBOT_PURCHASE_MADE, robotViewModel.history.joinToString(", "))
        }
        setResult(Activity.RESULT_OK, resultIntent)
    }
    private fun toggleImage(currentTurn : Int) {
        when (currentTurn) {
            1 -> whiteRobotImg.setImageResource(R.drawable.robot_red_large)
            2 -> whiteRobotImg.setImageResource(R.drawable.robot_white_large)
            else -> whiteRobotImg.setImageResource(R.drawable.robot_yellow_large)
        }
    }
    companion object{
        fun newIntent(context : Context, robotEnergy : Int, currentTurn : Int) : Intent{
            return Intent(context, RobotPurchase::class.java).apply {
                putExtra(EXTRA_ROBOT_ENERGY, robotEnergy)
                putExtra(EXTRA_CURRENT_TURN, currentTurn)
            }
        }
    }

//    private fun setWhichPurchaseMade(robotPurchaseMade : Int){
//        val resultIntent = Intent()
//        resultIntent.putExtra(EXTRA_ROBOT_ENERGY, robotViewModel.robotEnergy)
//        resultIntent.putExtra(EXTRA_ROBOT_PURCHASE_MADE, robotPurchaseMade.toString())
//        setResult(Activity.RESULT_OK, resultIntent)
//    }


}