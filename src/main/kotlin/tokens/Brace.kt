package tokens

import visitors.TokenVisitor

abstract class Brace : Token {
    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }
}
