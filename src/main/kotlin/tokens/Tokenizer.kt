package tokens;


import java.lang.IllegalArgumentException

class Tokenizer {
    var pos = 0

    fun tokenize(s: String): MutableList<Token> {
        pos = 0
        val tokens = mutableListOf<Token>()
        while (pos < s.length) {
            val c = s[pos]
            when (c) {
                ' ' -> {
                }
                '(' -> tokens.add(LeftBrace())
                ')' -> tokens.add(RightBrace())
                '+' -> tokens.add(Plus())
                '*' -> tokens.add(Mul())
                '/' -> tokens.add(Div())
                '-' -> {
                    if ((tokens.size > 0) and (tokens.last() !is Operation)) {
                        tokens.add(Minus())
                    } else {
                        pos++
                        tokens.add(NumberToken(-parseNumber(s)))
                    }
                }
                in '0'..'9' -> {
                    tokens.add(NumberToken(parseNumber(s)))
                }
                else -> throw  IllegalArgumentException()
            }
            pos++

        }
        return tokens
    }

    private fun parseNumber(s: String): Int {
        var num = 0
        require(pos < s.length && s[pos].isDigit())
        while (pos < s.length && s[pos].isDigit()) {
            num = num * 10 + Character.getNumericValue(s[pos])
            pos++
        }
        pos--
        return num
    }
}


