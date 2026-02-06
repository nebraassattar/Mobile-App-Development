package com.example.robot

import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var redRobotImg : ImageView
    private lateinit var whiteRobotImg : ImageView
    private lateinit var yellowRobotImg : ImageView
    private lateinit var messageBox : TextView
    private lateinit var clockwiseButton : ImageView
    private lateinit var counterClockwiseButton : ImageView
    private var turnCount /*: Int*/ = 0
    private val Robots = listOf(
        Robot(R.string.red_message_text, false),
        Robot(R.string.white_message_text, false),
        Robot(R.string.yellow_message_text, false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        redRobotImg = findViewById(R.id.red_robot)
        whiteRobotImg = findViewById(R.id.white_robot)
        yellowRobotImg = findViewById(R.id.yellow_robot)
        // messageBox = findViewById(R.id.message_box)
        clockwiseButton = findViewById(R.id.clockwise_button)
        counterClockwiseButton = findViewById(R.id.counter_clockwise_button)

        // This was done in class, had to comment out for the homework
        /*redRobotImg.setOnClickListener {
            // Toast.makeText(this, "Red Robot Clicked", Toast.LENGTH_SHORT).show()
            toggleImage()
        }

        whiteRobotImg.setOnClickListener {
            // Toast.makeText(this, "White Robot Clicked", Toast.LENGTH_SHORT).show()
            toggleImage()
        }

        yellowRobotImg.setOnClickListener {
            // Toast.makeText(this, "Yellow Robot Clicked", Toast.LENGTH_SHORT).show()
            toggleImage()
        }*/

        clockwiseButton.setOnClickListener {
            toggleImageButtonClockwise()
        }

        counterClockwiseButton.setOnClickListener {
            toggleImageButtonCounterClockwise()
        }
    } // End of onCreate

    /* private fun toggleImage(){
        turnCount++
        if (turnCount > 3) {
            turnCount = 1
        }
        if (turnCount == 1) {
            redRobotImg.setImageResource(R.drawable.robot_red_large)
            whiteRobotImg.setImageResource(R.drawable.robot_white_small)
            yellowRobotImg.setImageResource(R.drawable.robot_yellow_small)
        }else if (turnCount == 2) {
            whiteRobotImg.setImageResource(R.drawable.robot_white_large)
            redRobotImg.setImageResource(R.drawable.robot_red_small)
            yellowRobotImg.setImageResource(R.drawable.robot_yellow_small)
        }else {
            yellowRobotImg.setImageResource(R.drawable.robot_yellow_large)
            redRobotImg.setImageResource(R.drawable.robot_red_small)
            whiteRobotImg.setImageResource(R.drawable.robot_white_small)
        }
    } */

    private fun toggleImageButtonCounterClockwise() {
        turnCount++
        if (turnCount > 3) {
            turnCount = 1
        }
        if (turnCount == 1) {
            redRobotImg.setImageResource(R.drawable.robot_red_large)
            whiteRobotImg.setImageResource(R.drawable.robot_white_small)
            yellowRobotImg.setImageResource(R.drawable.robot_yellow_small)
        }else if (turnCount == 2) {
            whiteRobotImg.setImageResource(R.drawable.robot_white_large)
            redRobotImg.setImageResource(R.drawable.robot_red_small)
            yellowRobotImg.setImageResource(R.drawable.robot_yellow_small)
        }else {
            yellowRobotImg.setImageResource(R.drawable.robot_yellow_large)
            redRobotImg.setImageResource(R.drawable.robot_red_small)
            whiteRobotImg.setImageResource(R.drawable.robot_white_small)
        }
    }

    private fun toggleImageButtonClockwise() {
        // When we start, we set turnCount to 1 so that red is the first robot to become big
        if (turnCount == 0) {
            turnCount = 1
        } else {
            turnCount--
        }
        // After turnCount is 1, it is ran through the loop and the first if statement decrements it
        // to 0, and this if statement equals it to 3, looping it back to the yellow robot
        if (turnCount < 1) {
            turnCount = 3
        }
        if (turnCount == 1) {
            redRobotImg.setImageResource(R.drawable.robot_red_large)
            whiteRobotImg.setImageResource(R.drawable.robot_white_small)
            yellowRobotImg.setImageResource(R.drawable.robot_yellow_small)
        }else if (turnCount == 2) {
            whiteRobotImg.setImageResource(R.drawable.robot_white_large)
            redRobotImg.setImageResource(R.drawable.robot_red_small)
            yellowRobotImg.setImageResource(R.drawable.robot_yellow_small)
        }else {
            yellowRobotImg.setImageResource(R.drawable.robot_yellow_large)
            redRobotImg.setImageResource(R.drawable.robot_red_small)
            whiteRobotImg.setImageResource(R.drawable.robot_white_small)
        }
    }



    // Pick it up here
    private fun updateMessageBox() {
        when(turnCount) {
            1 -> messageBox.setText(R.string.red_message_text)
        }
    }
}