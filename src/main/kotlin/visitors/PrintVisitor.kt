package visitors

import tokens.Brace
import tokens.NumberToken
import tokens.Operation


class PrintVisitor : TokenVisitor {
    override fun visit(token: NumberToken) {
        print(token.toString())
    }

    override fun visit(token: Brace) {
        print(token.toString())
    }

    override fun visit(token: Operation) {
        print(token.toString())
    }
}