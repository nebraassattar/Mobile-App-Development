package com.example.robot

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kotlin.getValue
import kotlin.math.cos
const val EXTRA_ROBOT_ENERGY = "EXTRA_ROBOT_ENERGY"
const val EXTRA_ROBOT_PURCHASE_MADE = "EXTRA_ROBOT_ENERGY_MADE"
class RobotPurchase : AppCompatActivity() {
    private lateinit var rewardA : Button
    private lateinit var rewardB : Button
    private lateinit var rewardC : Button
    private lateinit var balanceTotal : TextView
    private val robotViewModel : RobotViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Find this linking line
        setContentView(R.layout.activity_robot_purchase)

        rewardA = findViewById(R.id.rewardA)
        rewardB = findViewById(R.id.rewardB)
        rewardC = findViewById(R.id.rewardC)
        balanceTotal = findViewById(R.id.balanceTotal)

        robotViewModel.setEnergy(
            intent.getIntExtra(EXTRA_ROBOT_ENERGY, 0)
        )

        balanceTotal.setText(robotViewModel.robotEnergy.toString())

        rewardA.setOnClickListener { makePurchase(1) }
        rewardB.setOnClickListener { makePurchase(2) }
        rewardC.setOnClickListener { makePurchase(3) }
    }




    private fun makePurchase(costOfPurchase : Int) {
        val rewards = listOf(R.string.reward_a_text, R.string.reward_b_text, R.string.reward_c_text)
        if (robotViewModel.robotEnergy >= costOfPurchase) {
            val s1 = getString(rewards[costOfPurchase - 1])
            val s2 = getString(R.string.purchased)
            val s3 = s1 + " " + s2
            robotViewModel.makePurchase(costOfPurchase)
            balanceTotal.setText(robotViewModel.robotEnergy.toString())
            Toast.makeText(this, s3, Toast.LENGTH_SHORT).show()
            setWhichPurchaseMade(costOfPurchase)
        } else {
            Toast.makeText(this, R.string.insufficient, Toast.LENGTH_SHORT).show()
        }
    }

    companion object{
        fun newIntent(context : Context, robotEnergy : Int) : Intent{
            return Intent(context, RobotPurchase::class.java).apply {
                putExtra(EXTRA_ROBOT_ENERGY, robotEnergy)
            }
        }
    }

    private fun setWhichPurchaseMade(robotPurchaseMade : Int){
        val resultIntent = Intent()
        resultIntent.putExtra(EXTRA_ROBOT_ENERGY, robotViewModel.robotEnergy)
        resultIntent.putExtra(EXTRA_ROBOT_PURCHASE_MADE, robotPurchaseMade.toString())
        setResult(Activity.RESULT_OK, resultIntent)
    }

}