package uong.ethan.practice

val x: Any? = "Hello"

fun main() {
    x?.let {
        if (x is String) {
            println(x.toString())
        } else {
            println("x is not a string")
        }
    }
}

open class A(val x: Int) {
    open fun foo(): String {
        println("a")
        return "a"
    }
}

//class B(x: Int) : A(x) {
//    fun foo(): String {
//        println("b")
//        return "A"
//    }
//}


/*
fun List<String>.foo() =
    map { "($it)" }
        .filter { it.length > 1 }
        .joinToString { it }

tailrec fun foo3(n: Int, soFar: Int = 0): Int {
    println(n)
    return if (n == 0) 0 else foo3(n - 1, soFar + n)
}

fun foo(x: Int = 10, g: (Int) -> String) {
    println(g(x))
}

abstract class Ethan {
    abstract val legs: Int
    abstract fun run()
}*/