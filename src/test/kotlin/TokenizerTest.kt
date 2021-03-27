package test

import org.junit.Test
import tokens.*
import java.lang.IllegalArgumentException

class TokenizerTest {
    private val tokenizer = Tokenizer()
    @Test
    fun baseTest() {
        val expected: MutableList<String> = mutableListOf(
            "LEFT",
            "NUMBER(1)",
            "MINUS",
            "NUMBER(2)",
            "RIGHT",
            "MUL",
            "LEFT",
            "NUMBER(4)",
            "PLUS",
            "NUMBER(3)",
            "RIGHT",
            "DIV",
            "NUMBER(3)"
            )
        val actual: List<Token> = tokenizer.tokenize("(1-2)*(4+3)/3")
        assert(expected == actual.map { it.toString() })
    }


    @Test(expected = IllegalArgumentException::class)
    fun badSymbolErrorTest() {
        tokenizer.tokenize("x")
    }
}