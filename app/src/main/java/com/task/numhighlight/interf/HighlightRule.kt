package com.task.numhighlight.interf

import android.util.Log

interface HighlightRule {
    fun shouldHighlight(number: Int): Boolean
}

class OddNumberRule : HighlightRule {
    override fun shouldHighlight(number: Int): Boolean {
        return try {
            number % 2 != 0
        } catch (e: Exception) {
            Log.e("OddNumberRule", "Error checking odd number", e)
            false
        }
    }
}

class EvenNumberRule : HighlightRule {
    override fun shouldHighlight(number: Int): Boolean {
        return try {
            number % 2 == 0
        } catch (e: Exception) {
            Log.e("EvenNumberRule", "Error checking even number", e)
            false
        }
    }
}

class PrimeNumberRule : HighlightRule {
    override fun shouldHighlight(number: Int): Boolean {
        return try {
            if (number < 2) return false
            for (i in 2..Math.sqrt(number.toDouble()).toInt()) {
                if (number % i == 0) return false
            }
            true
        } catch (e: Exception) {
            Log.e("PrimeNumberRule", "Error checking prime number", e)
            false
        }
    }
}

class FibonacciNumberRule : HighlightRule {
    override fun shouldHighlight(number: Int): Boolean {
        return try {
            val isPerfectSquare = { n: Int -> Math.sqrt(n.toDouble()).toInt().toDouble() == Math.sqrt(n.toDouble()) }
            isPerfectSquare(5 * number * number + 4) || isPerfectSquare(5 * number * number - 4)
        } catch (e: Exception) {
            Log.e("FibonacciNumberRule", "Error checking Fibonacci number", e)
            false
        }
    }
}
