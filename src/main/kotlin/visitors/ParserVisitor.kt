package visitors

import tokens.*


class ParserVisitor : TokenVisitor {
    private val result = mutableListOf<Token>()
    private val deferred = mutableListOf<Token>()
    override fun visit(token: NumberToken) {
        result.add(token)
    }

    override fun visit(token: Brace) {
        if (token is LeftBrace) {
            deferred.add(token)
        } else while (true) {
            require(deferred.isNotEmpty()) { "Incorrect expression" }
            val prev = deferred.removeLast()
            if (prev is LeftBrace) break
            result.add(prev)
        }
    }

    override fun visit(token: Operation) {
        while (deferred.isNotEmpty()) {
            val prev = deferred.last()
            if (prev is Operation && priority(prev) <= priority(token)) {
                result.add(prev)
                deferred.removeLast()
            } else break
        }
        deferred.add(token)
    }

    fun reversedPolishNotation(): MutableList<Token> {
        while (deferred.isNotEmpty()) {
            val tok = deferred.removeLast()
            result.add(tok)
        }
        return result
    }

    companion object {
        fun priority(token: Token): Int {
            return when (token) {
                is Mul, is Div -> 0
                is Plus, is Minus -> 1
                else -> 2
            }
        }
    }

}