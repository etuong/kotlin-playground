package uong.ethan.practice

// output of this main should be
//   Mr. Big: (Boss 1: (Thing 1, Thing 2), Boss 2: (Thing 3, Thing 4))
//   CEO: Mr. Big
//   Boss: Boss 1
//   Henchman: Thing 1
//   Henchman: Thing 2
//   Boss: Boss 2
//   Henchman: Thing 3
//   Henchman: Thing 4

interface Employee {
    val name: String
    fun generateOrgChart() = name
}

interface Manager : Employee {
    val minions: List<Employee>
    override fun generateOrgChart(): String {
        return "${name}: (${minions.map { it.generateOrgChart() }.joinToString()})"
    }
}

class CEO(override val name: String, override val minions: List<Employee>) : Manager {

}

class Henchman(override val name: String) : Employee {

}

class Boss(override val name: String, override val minions: List<Employee>) : Manager {

}

fun visit(person: Employee) {

    if (person is Manager) {
        println("${person::class.simpleName}: ${person.name}")
        person.minions.forEach {
            visit(it)
            println("${it::class.simpleName}: ${it.generateOrgChart()}")
        }
    }
}

fun main() {
    val ceo =
        CEO(
            name = "Mr. Big",
            minions = listOf(
                Boss(
                    name = "Boss 1",
                    minions = listOf(
                        Henchman("Thing 1"),
                        Henchman("Thing 2"),
                    )
                ),
                Boss(
                    name = "Boss 2",
                    minions = listOf(
                        Henchman("Thing 3"),
                        Henchman("Thing 4"),
                    )
                )
            )
        )
    println(ceo.generateOrgChart())
    visit(ceo)
}