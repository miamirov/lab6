package tokens

import visitors.TokenVisitor

abstract class Operation : Token {
    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }
}
