package uong.ethan.practice

//data class Practice (var age: Int) {
//    var dob: Int = age
//
//    constructor(name: String, age: Int) : this(age)
//}

class TersePerson(
    var name: String = "",
    var age: Int = 0
)

class Person(name: String, age: Int) {
    var name2: String = ""
    var age2: Int = 0

    constructor(name: String): this(name, 0)

    init {
        this.name2 = name
        this.age2 = age
    }

    // java equivalent
    //   private String name = "";
    //   private int age = 0;
    //   public void setName(name: String) { this.name = name; }
    //   public void setAge(age: Int) { this.age = age; }
    //   public String getName() { return this.name; }
    //   public int getAge() { return this.age; }
    //   public Person(String name, int age) {
    //      this.name = name;
    //      this.age = age;
    //   }
}

data class Person2(var name: String, val age: Int) {
    var age2: Int = age
        get() = field + 1

    constructor(name: String) : this(name, 27)
}

interface IntList {
    var list: List<Int>
    fun add(n: Int) {
        list = list + n
    }
}

interface Calculator {
    var value: Int
    fun add(n: Int) {
        value += n
    }
}

fun Int.add(number: Int): Int {
    return this + number
}

class Stuff: IntList, Calculator {
    override var list = emptyList<Int>()
    override var value = 0

    //    override fun add(n: Int) = super<Calculator>.add(n)
//    override fun add(n: Int) = super<IntList>.add(n)
    override fun add(n: Int) {
        super<Calculator>.add(n)
        super<IntList>.add(n)
    }
}

fun main() {
    val stuff = Stuff()
    stuff.add(4)
    stuff.add(5)
    println(stuff.value)
    println(stuff.list)
}