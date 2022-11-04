class MyList {
    //    Contains a nested Node class to hold each item in the linked list
    //       A Node Contains
    //          an integer value
    //          a pointer to the next node (or null if we're at the end of the list)
    //          this should be a data class, using a default value of null for next
    private inner class Node(
        val value: Int,
        var next: Node? = null,
    )

    //    Keeps track of head (possibly null) and last (possibly null) Nodes
    private var head: Node? = null
    private var tail: Node? = null
    var size: Int = 0
        private set

    //    Has an add function that does the following
    //       if the head is null, create a new node and assign head and last to it
    //       if the head is not null, add a new node at the end of the list (and point last to it)
    //       you can implement this function as recursive or non-recursive
    fun add(value: Int): Unit {
        val newNode = Node(value)
        if (this.head == null) {
            this.head = newNode
            this.tail = newNode
        } else {
            var current = this.head
            newNode.next = null
            current?.next = newNode
            this.head = newNode
        }
        this.size += 1
    }

    //    Has a forEach function that
    //       takes a functional parameter for doing something at a given node
    //          this function takes an integer value as its argument, and does not return a value
    fun forEach(action: (Int) -> Unit): Unit {
        var current = this.tail
        while (current != null) {
            action(current.value)
            current = current.next
        }
    }

    //    Has a contains() function (operator overload) that
    //       takes an integer value as a parameter
    //       walks the list to see if the value is present
    //          if so, return true. if not, return false
    //       Note - define this function by calling forEach; do not explicitly walk the items with a
    //          while loop! Keep in mind that lambdas can access variables defined outside them
    //       Write a comment in your implementation that briefly discusses the tradeoffs between implementing it
    //          this way vs an explicit loop. There are advantages/disadvantages to each approach.
    operator fun contains(value: Int): Boolean {
        // Explicit loop is more declarative and you can "break" out of the loop as soon as you find the index of
        // forEach is more functional and generic and can take in a functional parameter. Having closure here is a great help, too.
        var found = false
        forEach { found = found || it == value }
        return found
    }

    //    Has a get() function (operator overload) that
    //        takes an integer index as a parameter (0-based)
    //        returns the item at that index, or -1 if there are not enough items in the list
    //        You can define this function using forEach, OR by explicitly walking the list in a while loop.
    operator fun get(index: Int): Int? {
        var current = this.tail
        var counter = 0
        if (index < 0 || index > this.size) return -1
        while (counter != index) {
            current = current?.next
            counter++
        }
        return current?.value
    }
}

//    Create a top-level function named myListOf which takes any number of integers
//       as parameters, creates a MyList, adds all items to it, and returns the list.
//       Have this function use the "=" syntax for shortening the definition, with an apply to
//       fill in the list details
fun myListOf(vararg inputs: Int): MyList = MyList().apply { inputs.forEach { this.add(it) } }

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
