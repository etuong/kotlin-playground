// Assignment 6
//
// Create a project like you did for HW5, but call it HW6
//
// Copy this file into your project. Make sure you change the package and put it in the appropriate
//   folders in your project. DO NOT CHANGE ANY OF THE CODE THAT CAME IN THIS FILE __UNLESS__ INSTRUCTED TO DO SO!
//
// Helpful functions
//    File.mkdirs()
//       creates the directory specified by a file and all of its parent directories (if they don't exist)
//       (note - don't call this on a file that represents a FILE; call it on one that represents a directory)
//
//    File.parentFile
//       returns a File that represents the parent of the current file
//
//    File.listFiles()
//       returns an nullable array of all children of the current directory
//
//    File.writeText("...") [kotlin extension function]
//       writes the text to a file - note - could be a raw string (triple-quoted)
//
//    File(parentFile, "child-name")
//       constructor that allows you to pass in a parent directory and the name of a child file
//
//
// Create the following directory structure with files
//    sample-files
//       kotlin
//          package1
//            Sample11.kt
//            Sample12.kt
//          package2
//            Sample21.kt
//            Sample22.kt
//          package3
//            Sample31.kt
//            Sample32.kt
//       resources
//          fee.txt
//          fie.txt
//          foo.txt
//
// Each Sample##.kt file should contain the following code (without the dashes); indentation should be 4 spaces;
//    the package# should match the package directory name, and the class name should match the name of the file
//    Create these using loops so you don't hardcode each one; in other words, I don't want to see explicit
//    separate file writes for each file above...
//
// Each foo.txt should have three lines that repeat the file base name (with no leading spaces) in three columns.
// For example:
//
// foo foo foo
// foo foo foo
// foo foo foo
//
// -----------------------------------------------
// package package#
//
// class Sample## {
//     fun foo() {
//         println("Hello Sample##!!!")
//     }
// }
// -----------------------------------------------
//
//
// In the main function:
//
// Create the files listed above. You may define any other functions you need.
//
// Recursively print all files in the sample-files directory, with 2-space indentation at each level. First level
// should have no indentation. You may define any other functions you need.
//
// Write a function called printSlices that prints how many slices of a 12-piece pizza each person would get,
//   where we pass in the number of people as a parameter. This function should not have any exception handling in it.
//
// Write another function called partyPlanning that takes a varying-length list of ints, and calls printSlices to
//   print the details for each number in the list
//
// Call partyPlanning from main, passing 3, 6, 2, 0, 1, 12
//
// Add exception handling to partyPlanning so it will
//   * print "Whoops! I couldn't divide the pizza among N people!" if there is a problem
//   * NOT skip any other numbers in the list
//
// EXPECTED OUTPUT
// ================
// sample-files
//   kotlin
//     package1
//       Sample11.kt
//       Sample12.kt
//     package2
//       Sample21.kt
//       Sample22.kt
//     package3
//       Sample31.kt
//       Sample32.kt
//   resources
//     fee.txt
//     fie.txt
//     foo.txt
//
// Number of slices per person for 3 people: 4
// Number of slices per person for 6 people: 2
// Number of slices per person for 2 people: 6
// Whoops! I couldn't divide the pizza among 0 people!
// Number of slices per person for 1 people: 12
// Number of slices per person for 12 people: 1

fun main() {
    // TODO create sample-files file structure and files

    // TODO recursively walk sample-files file structure and print file names with appropriate indentation

    // TODO print a blank line

    // TODO call partyPlanning passing 3, 6, 2, 0, 1
}
