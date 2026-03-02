package com.example.robot

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "RobotViewModel"

class RobotViewModel : ViewModel() {
    init {
        Log.d(TAG, "ViewModel instance created")
    }

    private var turnCount = 0
    private var energy = 0
    val currentTurn : Int
        get() = turnCount
    val robotEnergy : Int
        get() = energy

    override fun onCleared() {
        super.onCleared()
    }

    fun incrementEnergy() {
        energy++
    }
    fun makePurchase(amount : Int) {
        energy -= amount
        if (energy < 0) {
            energy = 0
        }
    }
    fun advanceTurn() {
        turnCount++
        incrementEnergy()
        if (turnCount > 3) {
            turnCount = 1
        }
    }

}