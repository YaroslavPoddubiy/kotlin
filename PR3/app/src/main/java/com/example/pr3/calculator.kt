package com.example.pr3
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.math.PI

class Calculator {

    private fun erf(x: Double, terms: Int = 100): Double {
        var result = 0.0

        for (n in 0 until terms) {
            val term = (-1.0).pow(n) * x.pow(2 * n + 1) / factorial(n) / (2 * n + 1)
            result += term
        }

        return (2 / sqrt(PI)) * result
    }

    private fun factorial(n: Int): Double {
        return if (n == 0) 1.0 else n * factorial(n - 1)
    }

    private fun noImbalance(dailyPower: Double, stdDev: Double): Double {
        val error = 0.05 * dailyPower
        val noImbalanceCoef = erf(error / (stdDev * sqrt(2.0)) ) / 2 -
                erf(-error / (stdDev * sqrt(2.0))) / 2
        return String.format("%.2f", noImbalanceCoef).toDouble()
    }

    fun calculateProfit(
        dailyPower: Double,
        stdDeviation: Double,
        energyCost: Double
    ): Double {
        val noImbalanceCoef = noImbalance(dailyPower, stdDeviation)
        val profit = dailyPower * energyCost * 24.0 * noImbalanceCoef
        val tax = dailyPower * energyCost * 24 * (1.0 - noImbalanceCoef)
        return profit - tax
    }

}