package ru.ruslan.otustest

import org.junit.Test

import org.junit.Assert.*
import java.lang.Exception

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class HomeWorkUnitTest {
	private val quadraticEquation by lazy {
		QuadraticEquation()
	}

	// Написать тест, который проверяет, что для уравнения x^2+1 = 0 корней нет (возвращается пустой массив)
	@Test
	fun test1() {
		val a = 1.0
		val b = 0.0
		val c = 1.0

		val list = quadraticEquation.solve(a, b, c)
		assertEquals(list, null) // корней нет
	}

	// Написать тест, который проверяет, что для уравнения x^2-1 = 0 есть два корня кратности 1 (x1=1, x2=-1)
	@Test
	fun test2() {
		val a = 1.0
		val b = 0.0
		val c = -1.0

		val list = quadraticEquation.solve(a, b, c)
		assertEquals(list?.size, 2) // есть 2 корня
		assertEquals(list?.contains(1.0), true)
		assertEquals(list?.contains(-1.0), true)
	}

	// Написать тест, который проверяет, что для уравнения x^2+2x+1 = 0 есть один корень кратности 2 (x1 = x2 = -1).
	@Test
	fun test3() {
		var a = 1.0
		var b = 2.0
		var c = 1.0

		val list = quadraticEquation.solve(a, b, c)
		assertEquals(list?.size, 1) // один корень
		assertEquals(list?.contains(-1.0), true)

		// если дискриминант равен 0,
		var discriminant = quadraticEquation.discriminant(a, b, c)
		assertTrue(discriminant == 0.0)

		// если дискриминант не ноль, но меньше заданного epsilon.

		a = 1.0
		b = 4.0
		c = 3.0
		discriminant = quadraticEquation.discriminant(a, b, c)
		assertTrue(discriminant < 5)
		assertFalse(discriminant < 4)
		assertTrue(discriminant == 4.0)
		assertTrue(discriminant > 3)


		a = 1.0
		b = 4.0
		c = -3.0
		discriminant = quadraticEquation.discriminant(a, b, c)
		assertTrue(discriminant < 29)
		assertFalse(discriminant < 27)
		assertTrue(discriminant == 28.0)
		assertTrue(discriminant > 0)
	}

	// Написать тест, который проверяет, что коэффициент a не может быть равен 0. В этом случае solve выбрасывает исключение.
	@Test
	fun test4() {
		val a = 0.0
		val b = 2.0
		val c = 1.0

		try {
			quadraticEquation.solve(a, b, c)
		} catch (e: Exception) {
			val compare = e is QuadraticEquation.Ais0Exception
			assertTrue(compare)
		}
	}

	@Test
	fun test5() {
		// Проверяем, что метод не пропускает параметр "a" со значением больше максимального для данного типа
		minMaxTest(a = 2 * Double.MAX_VALUE, b = 2.0, c = 1.0, compareMessage = "\"a\" is infinity")

		// Проверяем, что метод не пропускает параметр "a" со значением меньше минимального для данного типа
		minMaxTest(a = 2 * Double.MIN_VALUE, b = 2.0, c = 1.0, compareMessage = "\"a\" is infinity")

		// Проверяем, что метод не пропускает параметр "b" со значением больше максимального для данного типа
		minMaxTest(a = 2.0, b = 2 * Double.MAX_VALUE, c = 1.0, compareMessage = "\"b\" is infinity")

		// Проверяем, что метод не пропускает параметр "b" со значением меньше минимального для данного типа
		minMaxTest(a = 2.0, b = 2 * Double.MIN_VALUE, c = 1.0, compareMessage = "\"b\" is infinity")

		// Проверяем, что метод не пропускает параметр "c" со значением больше максимального для данного типа
		minMaxTest(a = 2.0, b = 2.0, c = 2 * Double.MAX_VALUE, compareMessage = "\"c\" is infinity")

		// Проверяем, что метод не пропускает параметр "c" со значением меньше минимального для данного типа
		minMaxTest(a = 2.0, b = 2.0, c = 2 * Double.MIN_VALUE, compareMessage = "\"c\" is infinity")

		minMaxTest(
			a = Double.MAX_VALUE + 1.0,
			b = Double.MAX_VALUE + 1.0,
			c = Double.MAX_VALUE + 1.0,
			compareMessage = "\"a\" is infinity"
		)
	}

	private fun minMaxTest(a: Double, b: Double, c: Double, compareMessage: String) {
		try {
			quadraticEquation.solve(a, b, c)
		} catch (e: Exception) {
			val compare = e is QuadraticEquation.InfinityException
			assertTrue(compare)
			assertTrue(compareMessage in e.message.orEmpty())
		}
	}
}