package com.task.numhighlight.activities

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.Spinner
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.task.numhighlight.R
import com.task.numhighlight.adapter.NumberGridAdapter
import com.task.numhighlight.interf.EvenNumberRule
import com.task.numhighlight.interf.FibonacciNumberRule
import com.task.numhighlight.interf.HighlightRule
import com.task.numhighlight.interf.OddNumberRule
import com.task.numhighlight.interf.PrimeNumberRule

class MainActivity : AppCompatActivity() {

    private lateinit var gridView: GridView
    private lateinit var ruleSelector: Spinner
    private lateinit var adapter: NumberGridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {

            gridView = findViewById(R.id.numberGrid)
            ruleSelector = findViewById(R.id.ruleSelector)

            // Create a list of numbers from 1 to 100
            val numbers = (1..100).toList()

            // Set up the GridView with the initial rule (Odd Numbers)
            adapter = NumberGridAdapter(this, numbers, OddNumberRule())
            gridView.adapter = adapter

            // Set up the Spinner with available rules
            val rules = arrayOf("Odd Numbers", "Even Numbers", "Prime Numbers", "Fibonacci Numbers")
            val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, rules).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            ruleSelector.adapter = spinnerAdapter

            ruleSelector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                    try {
                        // Select the appropriate rule based on user selection
                        val newRule: HighlightRule = when (position) {
                            0 -> OddNumberRule()
                            1 -> EvenNumberRule()
                            2 -> PrimeNumberRule()
                            3 -> FibonacciNumberRule()
                            else -> OddNumberRule()
                        }
                        // Update the GridView with the new rule
                        adapter.updateRule(newRule)
                    } catch (e: Exception) {
                        Log.e("MainActivity", "Error selecting rule", e)

                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Optional: Handle the case where no selection is made
                }
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Error initializing activity", e)

        }
    }
}
