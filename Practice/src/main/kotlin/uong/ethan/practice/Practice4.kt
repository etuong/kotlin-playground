package uong.ethan.practice


interface Animal {
    val name: String
    val age: Int
}

data class Dog(
    override val name: String,
    override val age: Int,
) : Animal {
    fun bark() {

    }
}

data class Cat(
    override val name: String,
    override val age: Int,
) : Animal {
    fun annoy() {

    }
}

data class Platypus(
    override val name: String,
    override val age: Int,
) : Animal {
    fun quack() {

    }
}

fun main(args: Array<String>) {
    val animals: List<Animal> = listOf(
        Dog("Ethan", 1),
        Dog("Jon", 2),
        Cat("Jenny", 3),
        Cat("ALice", 4),
        Platypus("Tom", 5),
        Platypus("Bob", 6)
    )
    println(animals.sortedBy { it.name }.forEach { print("${it.name} ") })

    // Make all dogs bark
    animals.filterIsInstance<Dog>().forEach { it.bark() }

    // Sum of cats and pa
    val sum = animals.filter { it is Cat || it is Platypus }.sumOf { it.age }
    println(sum)

    // Another way
    val c = animals.filterIsInstance<Cat>().sumOf { it.age }
    val p = animals.filterIsInstance<Platypus>().sumOf { it.age }
    println(c + p)

}