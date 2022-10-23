// Assignment 2
//
// Create a project like you did for HW1, but call it HW2
//
// Copy this file into your project. Make sure you change the package and put it in the appropriate
//   folders in your project. DO NOT CHANGE ANY OF THE CODE THAT CAME IN THIS FILE
//

// Replace all comments that start with TODO
// Leave all other code as-is

// TODO define a function named graduation that
//    takes three parameters
//       names - a list of Strings
//       giveKeyNoteAddress - a function that takes no parameters and doesn't return anything
//       createTitle - a function that takes a String and returns a String
//    prints "Keynote address" followed by a blank line
//       (note that println() called with no arguments prints a blank line)
//    calls giveKeyNoteAddress - it will print the keynote address text
//    prints a blank line, then "Graduation Walk" followed by another blank line
//    calls createTitle on each name and prints separate lines for "<name> is now <new title>"
//       <name> is the original name passed in
//       <title> is the result of the createTitle call

fun graduation(names: List<String>, giveKeyNoteAddress: () -> Unit, createTitle: (String) -> String) {
    println("Keynote address")
    println()
    giveKeyNoteAddress()
    println()
    println("Graduation Walk")
    println()
    for (name in names) {
        val title = createTitle(name)
        println("$name is now $title")
    }
}

// TODO define a printFullLine function that
//    takes two parameters
//       width - an integer
//       char - a character (use type Char)
//    uses print(char) print characters
//      (note that print does not write a new line to the screen)
//    prints as many copies of char as specified by width
//    be sure to call println() when ready to end the line
fun printFullLine(width: Int, char: Char) {
    for (n in 0 until width) {
        print(char)
    }
    println()
}

// TODO define a printStartAndEndOfLine function that
//    takes two parameters
//       width - an integer
//       char - a character (use type Char)
//    uses print(char) or print(' ') to print characters
//      (note that print does not write a new line to the screen)
//    writes the character at the start and end of the width and spaces everywhere else in the line
//       e.g. if width is 5, the result should be (without the numbers)
//           *   *
//           12345
//    use a when expression to determine which character to print (space or the specified char)
//       note that a single case in the when expression can have multiple values, like
//          1, 4 -> xxx
//       and the values can be expressions/variables
//    be sure to call println() when ready to end the line
fun printStartAndEndOfLine(width: Int, char: Char) {
    for (n in 0 until width) {
        when (n) {
            in 1 until width - 1 -> print(' ')
            else -> print(char)
        }
    }
    println()
}

// TODO define a printBox function that
//    takes three parameters:
//       width - an integer
//       height - an integer
//       char - a character (use type Char)
//              default value is '*'
//    this function will "draw" a box by printing characters to the screen
//    call printFullLine to draw the top and bottom of the box
//    call printStartAndEndLine to draw the side lines of the box
fun printBox(width: Int, height: Int, char: Char) {
    for (n in 0 until height) {
        when (n) {
            in 1 until height - 1 -> printStartAndEndOfLine(width, char)
            else -> printFullLine(width, char)
        }
    }
}

fun main() {
    val names = listOf("Scott Stanchfield", "Eden Espinosa", "Tony Stark", "Sutton Foster", "Zachary Smith")

    val keynote: String =
        """Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
    incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
    nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
    Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
    fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
    culpa qui officia deserunt mollit anim id est laborum.""".trimIndent()

    // TODO call graduation with
    //    the above name list
    //    lambda for giveKeyNoteAddress that prints the following dummy text using a raw string
    //       Note that raw strings are the ones surrounded by """...""".trimIndent()
    //                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
    //                incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
    //                nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
    //                Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
    //                fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
    //                culpa qui officia deserunt mollit anim id est laborum.
    //    lambda for createTitle that takes a name and returns that name with Dr. in front of it

    graduation(names, { println(keynote) }, { name -> "Dr. $name" })

    println()

    // TODO print a box 10, 10 with the default character
    printBox(10, 10, '*')
    println()

    // TODO print a box 5, 5 with '=' as the character
    printBox(5, 5, '=')
}


// SAMPLE OUTPUT
//
// Keynote address
//
// Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
// incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
// nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
// Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
// fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
// culpa qui officia deserunt mollit anim id est laborum.
//
// Graduation Walk
//
// Scott Stanchfield is now Dr. Scott Stanchfield
// Eden Espinosa is now Dr. Eden Espinosa
// Tony Stark is now Dr. Tony Stark
// Sutton Foster is now Dr. Sutton Foster
// Zachary Smith is now Dr. Zachary Smith
//
// **********
// *        *
// *        *
// *        *
// *        *
// *        *
// *        *
// *        *
// *        *
// **********
//
// =====
// =   =
// =   =
// =   =
// =====
