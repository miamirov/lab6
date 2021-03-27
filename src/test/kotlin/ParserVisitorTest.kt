package test

import org.junit.Before
import org.junit.Test
import tokens.*
import visitors.ParserVisitor
import java.lang.IllegalArgumentException


class ParserVisitorTest {
    private lateinit var parserVisitor: ParserVisitor

    @Before
    fun init() {
        parserVisitor = ParserVisitor()
    }

    @Test
    fun baseTest() {
        val expr: MutableList<Token> = mutableListOf(
            LeftBrace(),
            NumberToken(2),
            Plus(),
            NumberToken(3),
            RightBrace(),
            Div(),
            LeftBrace(),
            NumberToken(7),
            Minus(),
            NumberToken(9),
            RightBrace(),
            Mul(),
            NumberToken(10)
        )
        val expected: MutableList<Token> = mutableListOf(
            NumberToken(2),
            NumberToken(3),
            Plus(),
            NumberToken(7),
            NumberToken(9),
            Minus(),
            Div(),
            NumberToken(10),
            Mul()
        )
        expr.forEach { it.accept(parserVisitor) }
        val actual = parserVisitor.reversedPolishNotation()
        assert(actual.map { ::toString } == expected.map { ::toString })
    }

    @Test(expected = IllegalArgumentException::class)
    fun notClosedBracketErrorTest() {
        val expr: MutableList<Token> = mutableListOf(RightBrace())
        expr.forEach { it.accept(parserVisitor) }
    }
}