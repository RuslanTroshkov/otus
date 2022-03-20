package ru.ruslan.otustest

import kotlin.math.pow
import kotlin.math.sqrt

class QuadraticEquation {

	/**
	 * [a] , [b], [c] - параметры квадратного уравнения ax2 + bx + c = 0
	 * @return список корней квадратного уравнения. Вернет null, если корней нет
	 */
	fun solve(a: Double, b: Double, c: Double): List<Double>? {
		if (a.isInfinite()) {
			throw InfinityException("parameter \"a\" is infinity")
		}
		if (b.isInfinite()) {
			throw InfinityException("parameter \"b\" is infinity")
		}
		if (c.isInfinite()) {
			throw InfinityException("parameter \"c\" is infinity")
		}

		if (a == 0.0 && b == 0.0) {
			return null
		}

		val d = discriminant(a, b, c)

		return when {
			d < 0.0 -> {
				null
			}
			d == 0.0 -> {
				return listOf(
					-b / (2 * a)
				)
			}
			else -> {
				listOf(
					(-b - sqrt(d)) / (2 * a),
					(-b + sqrt(d)) / (2 * a)
				)
			}
		}
	}

	fun discriminant(a: Double, b: Double, c: Double): Double {
		if (a == 0.0) {
			throw Ais0Exception("parameter a is 0")
		}
		return b.pow(2.0) - 4 * a * c
	}


	class Ais0Exception(override val message: String) : IllegalStateException(message)
	class InfinityException(override val message: String) : IllegalStateException(message)
}