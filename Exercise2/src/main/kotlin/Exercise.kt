// TODO Create three classes: Person, Doctor and FixedPerson
//    (you can put them in this file or separate file(s)
//
// Class Person has the following properties:
//   name - a mutable string that's passed into its constructor
//   age - a mutable int that's passed into its constructor
//   bio - an immutable string that always has the value "I am <name> and I'm <age> years old"
//           (the <name> and <age> parts mean you need to replace them with the current name and age)
open class Person(var name: String, var age: Int) {
    open val bio: String // no backing field
        get() = "I am $name and I'm $age years old" // good if dependent properties are VARS
    // Class Person has the following functions
//   tellMeWhoYouAre (no parameters) - returns the <bio> followed by "!!!!"
//   shoutThis (takes a string parameter named phrase) - returns the phrase followed by "!!!!"
//   shoutThis
//       takes a function named phraseGenerator as a parameter - that function takes a String and Int
//       calls the phraseGenerator passing the person's name and age to create a phrase
//       calls the OTHER shoutThis passing in that generated phrase
    open fun tellMeWhoYouAre(): String {
        return this.bio.plus("!!!!")
    }

    open fun shoutThis(phrase: String): String {
        return phrase.plus("!!!!")
    }

    open fun shoutThis(phraseGenerator: (String, Int) -> String): String {
        val phrase: String = phraseGenerator(this.name, this.age)
        return this.shoutThis(phrase)
    }
}

// Class Doctor is a subclass of Person, that overrides the bio property
//    The overridden bio property changes the text to include "Dr." in front of the name
//      (note - you may hardwire the string template; you do NOT need to do any string surgery here)
//    Note that Doctor still has the same constructor parameters as Person, and passes them to Person
class Doctor(name: String, age: Int) : Person(name, age) {
    override val bio: String
        get() = "I am Dr. $name and I'm $age years old"
}

// Class Fixed Person is a subclass of Person, that overrides all three functions
//   The functions only return the bio (that's all they'll say)
//    Note that FixedPerson still has the same constructor parameters as Person, and passes them to Person
class FixedPerson(name: String, age: Int) : Person(name, age) {
    override fun tellMeWhoYouAre(): String {
        return this.bio
    }

    override fun shoutThis(phrase: String): String {
        return this.bio
    }

    override fun shoutThis(phraseGenerator: (String, Int) -> String): String {
        return this.bio
    }
}

// The output of the following main should look like this
//
// SAMPLE OUTPUT
//
//      ---- Normal Person -----
//      I am Ethan and I'm 53 years old
//      I am Ethan and I'm 53 years old!!!!
//      Hello!!!!
//      Ethan has had 53 birthdays!!!!
//
//      ---- Normal Person -----
//      I am Ethan and I'm 54 years old
//      I am Ethan and I'm 54 years old!!!!
//      Hello!!!!
//      Ethan has had 54 birthdays!!!!
//
//      ---- Doctor Person -----
//      I am Dr. Ethan and I'm 53 years old
//      I am Dr. Ethan and I'm 53 years old!!!!
//      Hello!!!!
//      Ethan has had 53 birthdays!!!!
//
//      ---- Doctor Person -----
//      I am Dr. Ethan and I'm 54 years old
//      I am Dr. Ethan and I'm 54 years old!!!!
//      Hello!!!!
//      Ethan has had 54 birthdays!!!!
//
//      ---- Fixed Person -----
//      I am Ethan and I'm 53 years old
//      I am Ethan and I'm 53 years old
//      I am Ethan and I'm 53 years old
//      I am Ethan and I'm 53 years old
//
//      ---- Fixed Person -----
//      I am Ethan and I'm 54 years old
//      I am Ethan and I'm 54 years old
//      I am Ethan and I'm 54 years old
//      I am Ethan and I'm 54 years old

fun main() {
    val Ethan = Person("Ethan", 53)
    doStuff("Normal", Ethan)

    val drEthan = Doctor("Ethan", 53)
    doStuff("Doctor", drEthan)

    doStuff("Fixed", FixedPerson("Ethan", 53))
    // just to show you don't need the val...
}

fun doStuff(type: String, person: Person) {
    for (suffix in listOf("", "y")) {
        person.name += suffix
        println()
        println("---- $type Person -----")
        println(person.bio)
        println(person.tellMeWhoYouAre())
        println(person.shoutThis("Hello"))
        println(person.shoutThis { name, age -> "$name has had $age birthdays" })
        person.age++ // the "y" makes them feel older
    }
}
