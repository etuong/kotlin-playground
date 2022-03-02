// Assignment 5
//
// Create a project like you did for HW1, but call it HW5
//
// Copy this file into your project. Make sure you change the package and put it in the appropriate
//   folders in your project. DO NOT CHANGE ANY OF THE CODE THAT CAME IN THIS FILE
//

// Replace all comments that start with TODO
// Leave all other code as-is

package uong.ethan.hw5 // TODO change to YOUR package name here

// In this assignment, you'll work with Kotlin's Lists and Maps

// Create the following classes:
//    (you can put them in this file or separate file(s)
//
//      Mammal - sealed interface that defines three abstract properties, with no defaults:
//        name - a non-null String
//        age - a non-null Int
//        friends - a non-null list of Mammals (using Kotlin's List<Mammal>)
//      Note that these are NOT constructor properties - this allows us to define subclasses
//        that are data classes that define the properties in their constructors.
sealed interface Mammal {
    val name: String
    val age: Int
    var friends: List<Mammal>
}

//      Three subclasses (data classes) of Mammal. Each will have a constructor that has name, age and friends.
//      The friends parameter should default to an empty list.
//
//      Each class has a unique feature property that the others do not have. These properties 
//         should be added to the constructor BEFORE friends 
//         (friends should always be the last property)
//
//      Human - adds property nickname String (non-null)
//      Dog - adds property favoriteTreat String (non-null)
//      Cat - adds property numberOfMiceSlain Int (non-null)
data class Human(
    override val name: String,
    override val age: Int,
    val nickname: String,
    override var friends: List<Mammal> = emptyList()
) : Mammal

data class Dog(
    override val name: String,
    override val age: Int,
    val favoriteTreat: String,
    override var friends: List<Mammal> = emptyList()
) : Mammal

data class Cat(
    override val name: String,
    override val age: Int,
    val numberOfMiceSlain: Int,
    override var friends: List<Mammal> = emptyList()
) : Mammal

// Note - the classes must have an actual property named nickName, favoriteTreat or numberOfMiceSlain. Each
//        class will only have the one of these that is specified. The interface will NOT have these properties.
//        (Also - some students in the past tried setting up a "uniqueFeature" property in the interface and
//        implementing this in the subclasses. DO NOT do this. Each class should have a distinct property name
//        that no other class has to help you see how smart casting does some really cool things...)

// Implement the following top-level functions that are used in the code provided below.
// You can implement them in this file or other file(s)
// You can define additional helper functions if you would like
//
// All of these functions are top-level extension functions on List<Mammal>
// 
// Remember that extension function names are preceded by Type. For example:
//
//    fun List<Mammal>.askQuestion(question: String) { ... }
//
// Inside the extension function, "this" is the receiver on which the function was called.
// 
// For example, if you wrote
//
//    val mammals = listOf(...)
//    mammals.askQuestion("How old are you?")
//
// then the list that mammals pointed to at the time you call askQuestions is "this" 
// inside askQuestion().
//
// NOTE: When asked to work with all items AND their friends, you do NOT have to recurse.
//       Just work with the direct items in the list and their direct friends.  
//       We're only going one level deep to keep this a good bit simpler, especially when you might have
//       to consider friends causing circular references...
//
// DO NOT ASSUME ANYTHING ABOUT THE ACTUAL DATA
//    Your code should not make assumptions like "there is at least one Cat in the list".
//    The only thing your functions should know is that you have a list of Mammals that may 
//       have zero or more Mammals of any type in any order
//    (Actually... I didn't talk about distinct() in class, so it's ok to assume that all
//        animals will be unique and their names are unique.
//        IE friends will not contain any top-level animals. I didn't talk about distinct() in class...)
//
//      getMammalsAndDirectFriends()
//          return a list of all top-level mammals and all of their direct friends
//          you can use this inside many of the other functions
fun List<Mammal>.getMammalsAndDirectFriends(): List<Mammal> {
    // Note: This works too but it's a mutable list
    //return this.flatMap { m -> mutableListOf(m).also { it.addAll(m.friends) } }
    return this + this.flatMap { it.friends }
}

//      getNames()
//          return a comma-separated string of the names of the mammals that are directly 
//          in the list, NOT friends, in alphabetical order
fun List<Mammal>.getNames(): String {
    return this.map { it.name }.sorted().joinToString(separator = ", ")
}

//      getNamesAndFriendsAges()
//          creates a map of the name of each mammal and the sum of the ages of their friends
fun List<Mammal>.getNamesAndFriendsAges(): Map<String, Int> {
    return this.map { it.name to it.friends.sumOf { it.age } }.toMap()
}

//      getNamesAndDogFriendsAges()
//          creates a map of the name of each mammal and the sum of the ages of ONLY their
//              friends that are dogs
fun List<Mammal>.getNamesAndDogFriendsAges(): Map<String, Int> {
    return this.map { it.name to it.friends.filterIsInstance<Dog>().sumOf { it.age } }.toMap()
}

//      getTotalMiceSlain()
//          return the total number of mice slain by all cats (cats in the list AND cat friends
//              of any mammal in the list)
fun List<Mammal>.getTotalMiceSlain(): Int {
    return this.getMammalsAndDirectFriends().filterIsInstance<Cat>().sumOf { it.numberOfMiceSlain }
}

//      getAllNames()
//          return a comma-separated string of the names of all mammals, direct and friends,
//              in alphabetical order
fun List<Mammal>.getAllNames(): String {
    return this.getMammalsAndDirectFriends().map { it.name }.sorted().joinToString(separator = ", ")
}

//      groupByType()
//          create a map that groups all mammals, direct and friends, by their type name
fun List<Mammal>.groupByType(): Map<String?, List<Mammal>> {
    return this.getMammalsAndDirectFriends()
        .groupBy({ it::class.simpleName }, valueTransform = { it })
}

//      getUniqueFeatures()
//          create a list of strings reporting every mammals' (direct and friends)
//          name and unique feature, sorted. Don't worry about plural/singular wording
//          on the slain mice - just always use "mice", even if there were only one slain...
//          see the sample output below
fun List<Mammal>.getUniqueFeatures(): List<String> {
    val uniqueFeatures = arrayListOf<String>()
    this.getMammalsAndDirectFriends().forEach { m ->
        when (m) {
            is Human -> uniqueFeatures.add("${m.name} is nicknamed ${m.nickname}")
            is Dog -> uniqueFeatures.add("${m.name} loves ${m.favoriteTreat}")
            is Cat -> uniqueFeatures.add("${m.name} killed ${m.numberOfMiceSlain} mice")
        }
    }
    /*val allMammals = this.getMammalsAndDirectFriends()
    allMammals.filterIsInstance<Cat>().onEach { uniqueFeatures.add("${it.name} killed ${it.numberOfMiceSlain} mice") }
    allMammals.filterIsInstance<Human>().onEach {uniqueFeatures.add("${it.name} is nicknamed ${it.nickname}")  }
    allMammals.filterIsInstance<Dog>().onEach { uniqueFeatures.add("${it.name} loves ${it.favoriteTreat}") }*/
    return uniqueFeatures
}

//
// A few operators/functions you may need that I want to highlight:
//
//   is - tests if an object is an instance of some class
//      example: return a name for an object based on its actual class
//         val typeName = when(someObject) {
//            is Ahnold-> "Ahnold"
//            is SecretAgent -> "Secret Agent"
//            is EvilBadGuy -> "Evil Bad Guy"
//            else -> "Disposable Henchman"
//         }
//
//      NOTE: in each case, the object passed in is smart-cast to its type, 
//            so you can access data that's available to that type. For example
//
//         when(someObject) {
//            is Ahnold-> someObject.sayCatchPhrase()
//            is SecretAgent -> someObject.sneak()
//            is EvilBadGuy -> someObject.laughManiacally()
//            else -> { /* do nothing */ }
//         }
//
//   filterIsInstance<TYPE>() - filters a list to just those elements of a specific type
//      example:  list.filterIsInstance<Foo>() will return only items of type Foo
//
//   joinToString() - creates a string by applying a lambda to each item in the list and 
//                    separating by commas (by default)
//      example:  list.joinToString { it.age + 10 } will return a comma-separated string of
//                ages bumped up 10 years
//
//   list concatenation - joins two lists together (creating a new list)
//      example: (list1 + list2).doSomethingWithTheCombinedLists {...}
//      NOTE: You will need this to create a combined list of mammals and their friends
//            AGAIN - DO NOT ASSUME ANYTHING ABOUT THE DATA
//            Think about a simple way to gather all friends at once into a single list,
//              and concatenate that to the mammal list

// The output of the following main should look like this
//
// SAMPLE OUTPUT
//
//  Core Mammal Names
//      Blackie, Fido, Patrick
//
//  Names and sums of their friends' ages
//      Fido=229
//      Blackie=55
//      Patrick=148
//
//  Names and sums of ages of friends who are dogs
//      Fido=12
//      Blackie=0
//      Patrick=12
//
//  Total mice slain by all cats
//      3548
//
//  Everybody's names!
//      Blackie, Fido, Fifi, Hugh, Ian, Little One, Mike, Patrick, Puffball, Q, Rover, Scott, Steven, Sue, Thing, Thunderbean, Worf
//
//  Mammals grouped by type
//      Dog: Fido, Fifi, Rover, Worf
//      Cat: Blackie, Little One, Puffball, Q, Thing, Thunderbean
//      Human: Hugh, Ian, Mike, Patrick, Scott, Steven, Sue
//
//  Unique Features
//      Blackie killed 22 mice
//      Fido loves Gravy Train
//      Fifi loves Milk Bone
//      Hugh is nicknamed Wolverine
//      Ian is nicknamed Gandalf
//      Little One killed 0 mice
//      Mike is nicknamed Mikey
//      Patrick is nicknamed Jean Luc
//      Puffball killed 13 mice
//      Q killed 3468 mice
//      Rover loves Real Bone
//      Scott is nicknamed Scooter
//      Steven is nicknamed Steve
//      Sue is nicknamed Susie
//      Thing killed 3 mice
//      Thunderbean killed 42 mice
//      Worf loves Gagh


// DO NOT CHANGE THE FOLLOWING CODE!!!
// DO NOT CHANGE THE FOLLOWING CODE!!!
// DO NOT CHANGE THE FOLLOWING CODE!!!
fun main() {
    val mammals = listOf(
        Dog(
            "Fido", 1, "Gravy Train",
            friends = listOf(
                Human("Scott", 53, "Scooter"),
                Human("Steven", 53, "Steve"),
                Cat("Thunderbean", 10, 42),
                Cat("Puffball", 101, 13),
                Dog("Fifi", 5, "Milk Bone"),
                Dog("Rover", 7, "Real Bone")
            )
        ),
        Cat(
            "Blackie", 2, 22,
            friends = listOf(
                Human("Mike", 23, "Mikey"),
                Human("Sue", 26, "Susie"),
                Cat("Thing", 5, 3),
                Cat("Little One", 1, 0)
            )
        ),
        Human(
            "Patrick", 79, "Jean Luc",
            friends = listOf(
                Human("Ian", 80, "Gandalf"),
                Human("Hugh", 51, "Wolverine"),
                Cat("Q", 5, 3468),
                Dog("Worf", 12, "Gagh")
            )
        )
    )

    println()
    println("Core Mammal Names")
    println("    ${mammals.getNames()}")

    println()
    println("Names and sums of their friends' ages")
    mammals.getNamesAndFriendsAges().forEach { println("    $it") }

    println()
    println("Names and sums of ages of friends who are dogs")
    mammals.getNamesAndDogFriendsAges().forEach { println("    $it") }

    println()
    println("Total mice slain by all cats")
    println("    ${mammals.getTotalMiceSlain()}")

    println()
    println("Everybody's names!")
    println("    ${mammals.getAllNames()}")

    println()
    println("Mammals grouped by type")
    mammals
        .groupByType()
        .forEach { (type, mammals) -> println("    $type: ${mammals.getNames()}") }

    println()
    println("Unique Features")
    mammals.getUniqueFeatures().forEach { println("    $it") }
}
