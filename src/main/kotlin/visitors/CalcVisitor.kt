package visitors


import tokens.*
import java.lang.IllegalArgumentException

class CalcVisitor : TokenVisitor {
    private val stack = mutableListOf<Int>()
    override fun visit(token: NumberToken) {
        stack.add(token.value)
    }

    override fun visit(token: Brace) {
        throw IllegalArgumentException("Brace in RPN error")
    }

    override fun visit(token: Operation) {
        require(stack.size >= 2) { "Bad number of operands error" }
        val b = stack.removeLast()
        val a = stack.removeLast()
        stack.add(
            when (token) {
                is Plus -> a + b
                is Minus -> a - b
                is Div -> {
                    require(b != 0)
                    a / b
                }
                is Mul -> {
                    a * b
                }
                else -> throw IllegalArgumentException()
            }
        )
    }

    val result: Int
        get() {
            require(stack.size == 1) { "Incorrect expression" }
            return stack.removeAt(0)
        }


}