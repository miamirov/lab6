package test

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import tokens.*
import visitors.CalcVisitor
import java.lang.IllegalArgumentException

class CalcVisitorTest {
    lateinit var calcVisitor: CalcVisitor


    @Before
    fun init() {
        calcVisitor = CalcVisitor()
    }

    @Test
    fun plusTest() {
        val expr: MutableList<Token> = mutableListOf(NumberToken(33), NumberToken(11), Plus())
        expr.forEach { it.accept(calcVisitor) }
        Assert.assertEquals(44, calcVisitor.result.toLong())
    }

    @Test
    fun minusTest() {
        val expr: MutableList<Token> = mutableListOf(NumberToken(13), NumberToken(10), Minus())
        expr.forEach { it.accept(calcVisitor) }
        Assert.assertEquals(3, calcVisitor.result.toLong())
    }

    @Test
    fun mulTest() {
        val expr: MutableList<Token> = mutableListOf(NumberToken(2), NumberToken(3), Mul())
        expr.forEach { it.accept(calcVisitor) }
        Assert.assertEquals(6, calcVisitor.result.toLong())
    }

    @Test
    fun divTest() {
        val expr: MutableList<Token> = mutableListOf(NumberToken(783), NumberToken(13), Div())


        expr.forEach { it.accept(calcVisitor) }
        Assert.assertEquals(60, calcVisitor.result.toLong())
    }

    @Test
    fun test() {
        val expr: MutableList<Token> = mutableListOf(
            NumberToken(2),
            NumberToken(13),
            Plus(),
            NumberToken(12),
            NumberToken(22),
            Minus(),
            Div(),
            NumberToken(123),
            Mul()
        )
        expr.forEach { it.accept(calcVisitor) }
        Assert.assertEquals(-123, calcVisitor.result.toLong())
    }

    @Test(expected = IllegalArgumentException::class)
    fun divisionByZeroErrorTest() {
        val expr: MutableList<Token> = mutableListOf(NumberToken(1), NumberToken(0), Div())

        expr.forEach { it.accept(calcVisitor) }

    }

    @Test(expected = IllegalArgumentException::class)
    fun incorrectRpnTest() {
        val expr: MutableList<Token> = mutableListOf(NumberToken(1), (Mul()))
        expr.forEach { it.accept(calcVisitor) }

    }

    @Test(expected = IllegalArgumentException::class)
    fun uncompletedExpressionErrorTest() {
        val expr: MutableList<Token> = mutableListOf(NumberToken(1), NumberToken((2)))
        expr.forEach { it.accept(calcVisitor) }
        calcVisitor.result
    }
}