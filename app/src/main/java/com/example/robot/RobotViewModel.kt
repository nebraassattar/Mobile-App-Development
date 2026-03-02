package com.example.robot

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "RobotViewModel"

class RobotViewModel : ViewModel() {
    init {
        Log.d(TAG, "ViewModel instance created")
    }

    private var turnCount = 0
    val currentTurn : Int
        get() = turnCount
    var robotEnergy : Int = 0

    override fun onCleared() {
        super.onCleared()
    }

    fun incrementEnergy() {
        robotEnergy++
    }
    fun makePurchase(amount : Int) {
        robotEnergy -= amount
    }
    fun setEnergy(value : Int) {
        robotEnergy = value
    }
    fun advanceTurn() {
        turnCount++
        if (turnCount > 3) {
            turnCount = 1
        }
        incrementEnergy()
    }

}