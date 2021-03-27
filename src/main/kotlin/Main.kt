import visitors.CalcVisitor
import visitors.ParserVisitor
import visitors.PrintVisitor
import tokens.Tokenizer

fun main() {


    val tokens = Tokenizer().tokenize(readLine()!!)

    val parserVisitor = ParserVisitor()
    tokens.forEach { it.accept(parserVisitor) }
    val rpn = parserVisitor.reversedPolishNotation()
    val printVisitor = PrintVisitor()
    rpn.forEach {
        it.accept(printVisitor)
        print(" ")
    }
    println()
    val calcVisitor = CalcVisitor()
    rpn.forEach { it.accept(calcVisitor) }
    println(calcVisitor.result)
}
