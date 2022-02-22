// Assignment 4
//
// Create a project like you did for HW1, but call it HW4
//
// Copy this file into your project. Make sure you change the package and put it in the appropriate
//   folders in your project. DO NOT CHANGE ANY OF THE CODE THAT CAME IN THIS FILE
//

// Replace all comments that start with TODO
// Leave all other code as-is

package uong.ethan.hw4 // TODO change to YOUR package name here

// In this assignment, you'll create a custom linked-list class

// TODO Create class MyList
//    You're implementing a linked-list by hand; DO NOT just wrap an existing linked list
//
//    You are allowed to look up the concepts of implementing a linked list,
//       but you must implement it in a similar manner to the Binary tree I implemented in class
//
//    Contains a nested Node class to hold each item in the linked list
//       A Node Contains
//          an integer value
//          a pointer to the next node (or null if we're at the end of the list)
//          this should be a data class, using a default value of null for next
//
//    Keeps track of head (possibly null) and last (possibly null) Nodes
//
//    Has an add function that does the following
//       if the head is null, create a new node and assign head and last to it
//       if the head is not null, add a new node at the end of the list (and point last to it)
//       you can implement this function as recursive or non-recursive
//
//    Has a forEach function that
//       takes a functional parameter for doing something at a given node
//          this function takes an integer value as its argument, and does not return a value
//
//    Has a contains() function (operator overload) that
//       takes an integer value as a parameter
//       walks the list to see if the value is present
//          if so, return true. if not, return false
//       Note - define this function by calling forEach; do not explicitly walk the items with a
//          while loop! Keep in mind that lambdas can access variables defined outside them
//       Write a comment in your implementation that briefly discusses the tradeoffs between implementing it
//          this way vs an explicit loop. There are advantages/disadvantages to each approach.
//
//    Has a get() function (operator overload) that
//        takes an integer index as a parameter (0-based)
//        returns the item at that index, or -1 if there are not enough items in the list
//        You can define this function using forEach, OR by explicitly walking the list in a while loop.
//
//    Create a top-level function named myListOf which takes any number of integers
//       as parameters, creates a MyList, adds all items to it, and returns the list.
//       Have this function use the "=" syntax for shortening the definition, with an apply to
//       fill in the list details

// SAMPLE OUTPUT
//
// 1
// 2
// 3
// 4
// 5
// number of items in list: 5
// average of items in list: 3.0
// 3 in list1: true
// 10 in list1: false
// 5 !in list1: false
// 20 !in list1: true
// list1[2]: 3
// list1[4]: 5
// list1[8]: -1


// DO NOT CHANGE THE FOLLOWING CODE!!!
// DO NOT CHANGE THE FOLLOWING CODE!!!
// DO NOT CHANGE THE FOLLOWING CODE!!!
fun main() {
    val list = myListOf(1, 2, 3, 4, 5)

    list.forEach {
        println(it)
    }

    println("number of items in list: ${list.size}")

    var sum = 0
    list.forEach {
        sum += it
    }

    println("average of items in list: ${sum.toDouble() / list.size}")

    println("3 in list1: ${3 in list}")
    println("10 in list1: ${10 in list}")
    println("5 !in list1: ${5 !in list}")
    println("20 !in list1: ${20 !in list}")

    println("list1[2]: ${list[2]}")
    println("list1[4]: ${list[4]}")
    println("list1[8]: ${list[8]}")
}
