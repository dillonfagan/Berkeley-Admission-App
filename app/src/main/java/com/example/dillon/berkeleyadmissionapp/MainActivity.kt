package com.example.dillon.berkeleyadmissionapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity: AppCompatActivity() {

    /**
     * Random number generator for creating the
     * numbers for the left and right buttons.
     */
    private val random = Random()

    /**
     * Left number button
     */
    private lateinit var leftButton: Button

    /**
     * Right number button
     */
    private lateinit var rightButton: Button

    /**
     * Points accumulated by the user. Gets incremented
     * when the user successfully chooses the bigger number.
     */
    private var points = 0

    /**
     * The higher number, chosen by the randomizeNumbers function.
     */
    private var higherNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        leftButton = findViewById(R.id.leftButton) as Button
        rightButton = findViewById(R.id.rightButton) as Button

        randomizeNumbers()
    }

    private fun randomizeNumbers() {
        var leftNumber: Int
        var rightNumber: Int

        while (true) {
            leftNumber = random.nextInt(99)
            rightNumber = random.nextInt(99)

            if (leftNumber != rightNumber) break
        }

        higherNumber = maxOf(leftNumber, rightNumber)

        // Assign random values to the buttons
        val leftButton = findViewById(R.id.leftButton) as Button
        leftButton.text = leftNumber.toString()

        val rightButton = findViewById(R.id.rightButton) as Button
        rightButton.text = rightNumber.toString()
    }

    /**
     * Called when one of the buttons has been pressed.
     * Determines whether the user successfully or unsuccessfully
     * chose the bigger number, then updates the points.
     */
    fun buttonClicked(view: View) {
        val button = view as Button

        // Check the int value of "view" and update points accordingly
        if (button.text.toString().toInt() == higherNumber) {
            points += 1
        } else {
            points -= 1
        }

        val pointsView = findViewById(R.id.pointsView) as TextView

        // The user wins when they get 10 points
        if (points == 10) {
            pointsView.text = getString(R.string.win_dialogue)

            leftButton.isClickable = false
            rightButton.isClickable = false
        } else {
            pointsView.text = points.toString()
            randomizeNumbers()
        }
    }
}
