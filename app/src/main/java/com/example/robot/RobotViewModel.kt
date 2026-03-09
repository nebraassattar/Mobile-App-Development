package com.example.robot

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "RobotViewModel"

val ALL_REWARDS = listOf(
    Reward(R.string.reward_a_text, 1),
    Reward(R.string.reward_b_text,2),
    Reward(R.string.reward_c_text, 3),
    Reward(R.string.reward_d_text, 3),
    Reward(R.string.reward_e_text, 4),
    Reward(R.string.reward_f_text, 4),
    Reward(R.string.reward_g_text, 7)
)
class RobotViewModel : ViewModel() {
    init {
        Log.d(TAG, "ViewModel instance created")
    }

    private var turnCount = 0
    val currentTurn : Int
        get() = turnCount
    //var robotEnergy : Int = 0

    var redRobotEnergy : Int = 0
    var whiteRobotEnergy : Int = 0
    var yellowRobotEnergy : Int = 0


    // Randomly pick 3 rewards out of the 7 we have
    val selectedRewards: List<Reward> = ALL_REWARDS.shuffled().take(3).sortedBy { it.cost }

    // Track which rewards have been bought
    val purchased : MutableSet<Int> = mutableSetOf()

    // Store all purchase names to show in MainActivity
    val history : MutableList<String> = mutableListOf()

    override fun onCleared() {
        super.onCleared()
    }

    fun incrementEnergy() {
        //robotEnergy++
        if (turnCount == 1){
            redRobotEnergy++
        }
        else if (turnCount == 2){
            whiteRobotEnergy++
        }
        else{
            yellowRobotEnergy++
        }
    }
    fun makePurchase(index : Int, name : String) {
        val amount = selectedRewards[index].cost
        if (turnCount == 1){
            redRobotEnergy -= amount
        }
        else if (turnCount == 2){
            whiteRobotEnergy -= amount
        }
        else{
            yellowRobotEnergy -= amount
        }
        purchased.add(index)
        history.add(name)

    }
    fun setEnergy(value : Int) {
        if (turnCount == 1){
            redRobotEnergy = value
        }
        else if (turnCount == 2){
            whiteRobotEnergy = value
        }
        else {
            yellowRobotEnergy = value
        }
    }
    fun advanceTurn() {
        turnCount++
        if (turnCount > 3) {
            turnCount = 1
        }
        incrementEnergy()
    }
    val currentRobotEnergy : Int
        get(){
            if (turnCount == 1){
                return redRobotEnergy
            }
            else if (turnCount == 2){
                return whiteRobotEnergy
            }
            else{
                return yellowRobotEnergy
            }
        }

}