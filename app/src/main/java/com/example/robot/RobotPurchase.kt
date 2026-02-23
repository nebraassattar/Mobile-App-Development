package com.example.robot

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.cos

private const val EXTRA_ROBOT_ENERGY = "EXTRA_ROBOT_ENERGY"
class RobotPurchase : AppCompatActivity() {
    private lateinit var rewardA : Button
    private lateinit var rewardB : Button
    private lateinit var rewardC : Button
    private lateinit var balanceTotal : TextView
    private var robotEnergy = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Find this linking line
        setContentView(R.layout.activity_robot_purchase)

        rewardA = findViewById(R.id.rewardA)
        rewardB = findViewById(R.id.rewardB)
        rewardC = findViewById(R.id.rewardC)
        balanceTotal = findViewById(R.id.balanceTotal)

        robotEnergy = 2 // Hardcode for testing
        robotEnergy = intent.getIntExtra(EXTRA_ROBOT_ENERGY, 4)

        balanceTotal.setText(robotEnergy.toString())

        rewardA.setOnClickListener { makePurchase(1) }
        rewardB.setOnClickListener { makePurchase(2) }
        rewardC.setOnClickListener { makePurchase(3) }
    }


    private fun makePurchase(costOfPurchase : Int) {
        val rewards = listOf(R.string.reward_a_text, R.string.reward_b_text, R.string.reward_c_text)
        if (robotEnergy >= costOfPurchase) {
            val s1 = getString(rewards[costOfPurchase - 1])
            val s2 = getString(R.string.purchased)
            val s3 = s1 + " " + s2
            robotEnergy -= costOfPurchase
            balanceTotal.setText(robotEnergy.toString())
            Toast.makeText(this, s3, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, R.string.insufficient, Toast.LENGTH_SHORT).show()
        }
    }
}