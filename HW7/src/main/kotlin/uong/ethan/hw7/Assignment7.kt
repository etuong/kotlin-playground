package uong.ethan.hw7

// Assignment 7
//
// Create a project like you did for HW6, but call it HW7
//
// Copy this file into your project. Make sure you change the package and put it in the appropriate
//   folders in your project. DO NOT CHANGE ANY OF THE CODE THAT CAME IN THIS FILE __UNLESS__ INSTRUCTED TO DO SO!
//
// Define a simple class hierarchy as follows:
//    Car - has a mutable Boolean property shiny; initialized to false - dealers will shine the car before delivery
//          note - do not define shiny in the constructor!
//       ToyotaCar
//          Camry
//          Rav4
//          Prius
//       HondaCar
//          Accord
//          CRV
//          Pilot
abstract class Car {
    var shiny = false
}

open class ToyotaCar() : Car()
class Camry() : ToyotaCar()
class Rav4() : ToyotaCar()
class Prius() : ToyotaCar()
open class HondaCar() : Car()
class Accord() : HondaCar()
class CRV() : HondaCar()
class Pilot() : HondaCar()

// Define a Factory class that will create cars
//    is parameterized by the type of Car that it produces (generic parameter)
//    has an immutable assemblyLine passed into its constructor - this is a lambda that produces a list of cars each time it is called
//    keeps track of the current stock of cars produced by the last assembly line run
//    has a getCar() function that returns and instance of the type of car the factory produces
//       if there are no cars in the current stock, it calls the assemblyLine() to produce a list of new cars
//          these new cars are placed in current stock
//       then it returns the first car from stock (and removes it from stock) - use the removeAt(0) function
class Factory<T : Car>(val assemblyLine: () -> MutableList<T>) {
    var stock: MutableList<T> = mutableListOf()
    fun getCar(): T {
        if (stock.isEmpty()) {
            stock = this.assemblyLine()
        }
        return stock.removeAt(0)
    }
}

// Define a JunkYard class that will dispose of cars
//    is parameterized by the type of Car that it can dispose of (generic parameter)
//    it has an immutable name property in its constructor
//    it has a trashCar() function that takes in a car of the type they can dispose
//       prints out "crunching a [car type name] at [junkyard name]"
class JunkYard<T : Car>(private val name: String = "") {
    fun trashCar(car: T) {
        println("crunching a ${car::class} at ${this.name}")
    }
}

// Define a Dealer class that will allow people to buy and trade in cars
//    is parameterized by the type of Car that can be purchased
//    is passed immutable name, Factory and Junkyard properties to its constructor
//    has a purchaseCar() function that gets a car from the factory, sets its shine = true, and returns it
//    has a tradeIn() function that takes a car of the dealer's type and sends it to the junkYard's trashCar() function
class Dealer<T : Car>(val name: String, val factory: Factory<T>, val junkYard: JunkYard<T>) {
    fun purchaseCar(): T {
        val car: T = this.factory.getCar()
        car.shiny = true
        return car
    }

    fun tradeIn(car: T) {
        this.junkYard.trashCar(car)
    }
}

// Define a helper function purchaseCarAndReport
//    takes a Dealer as a parameter
//    purchases a car from the dealer
//    prints "Bought a [car type name] from [dealer name]"
//    returns the car
//    NOTE: this function should return the same type of car that the Dealer is parameterized with
fun <T : Car> purchaseCarAndReport(dealer: Dealer<T>): T {
    val car: T = dealer.purchaseCar()
    println("Bought a ${car::class} from ${dealer.name}")
    return car
}

// Define an extension function tradeIn()
//    is parameterized by the type of car being traded in
//    is an extension on List of that type of car
//    takes a varying-length argument list (vararg) of Dealers that can take that type of car
//    walks through the receiver list of cars (use forEachIndexed)
//        trades in the current car with the [n % dealers.size] dealer that was passed in
//           (this alternates through all dealers passed in)

//fun <T: Car> List.tradeIn() {
//
//}
// Fill in the missing details in the main function
//
// Expected Output
// =========================================
// Bought a Camry from Bob's Toyota
// Bought a Accord from Mick's Honda
// Bought a Rav4 from Sue's Toyota
// Bought a Camry from Overpay us or die!
// Bought a Prius from Bob's Toyota
// Bought a CRV from Mick's Honda
// Bought a Camry from Sue's Toyota
// Bought a Rav4 from Overpay us or die!
// Bought a Rav4 from Bob's Toyota
// Bought a Pilot from Mick's Honda
// Bought a Prius from Sue's Toyota
// Bought a Prius from Overpay us or die!
// Bought a Camry from Bob's Toyota
// Bought a Accord from Mick's Honda
// Bought a Rav4 from Sue's Toyota
// Bought a Accord from Overpay us or die!
// Bought a Prius from Bob's Toyota
// Bought a CRV from Mick's Honda
// Bought a Camry from Sue's Toyota
// Bought a CRV from Overpay us or die!
// crunching a Camry at Trashed Toyotas
// crunching a Rav4 at Junk R Us
// crunching a Camry at Junk R Us
// crunching a Prius at Trashed Toyotas
// crunching a Camry at Junk R Us
// crunching a Rav4 at Junk R Us
// crunching a Rav4 at Trashed Toyotas
// crunching a Prius at Junk R Us
// crunching a Prius at Junk R Us
// crunching a Camry at Trashed Toyotas
// crunching a Rav4 at Junk R Us
// crunching a Prius at Junk R Us
// crunching a Camry at Trashed Toyotas
// crunching a Accord at Hammered Hondas
// crunching a CRV at Junk R Us
// crunching a Pilot at Hammered Hondas
// crunching a Accord at Junk R Us
// crunching a Accord at Hammered Hondas
// crunching a CRV at Junk R Us
// crunching a CRV at Hammered Hondas

fun main() {
    val toyotaCars = mutableListOf(Camry(), Rav4(), Prius())
    val hondaCars = mutableListOf(Accord(), CRV(), Pilot())
    val toyotaFactory = Factory { toyotaCars }
    val hondaFactory = Factory { hondaCars }
    val evilMiddleman = Factory { mutableListOf(Camry(), Rav4(), Prius(), Accord(), CRV(), Pilot()) }

    val junkyard1 = JunkYard<ToyotaCar>("Trashed Toyotas")
    val junkyard2 = JunkYard<HondaCar>("Hammered Hondas")
    val junkyard3 = JunkYard<Car>("Junk R Us")

    val toyotaDealer1 = Dealer("Bob's Toyota", toyotaFactory, junkyard1)
    val hondaDealer1 = Dealer("Mick's Honda", hondaFactory, junkyard2)
    val toyotaDealer2 = Dealer("Sue's Toyoya", toyotaFactory, junkyard1)
    val allDealer = Dealer("Overpay us or die!", evilMiddleman, junkyard3)

    val dealers = mutableListOf(toyotaDealer1, hondaDealer1, toyotaDealer2, allDealer)

    val carsBought = (0..19).map {
        purchaseCarAndReport(dealers[it % dealers.size])
    }.toList()
    //val carsBought = TODO("map 0..19 into calls to purchaseCarAndReport() from dealer n % dealers.size")

    // TODO trade in toyotas
    //    filter carsBought to just toyotas
    //    call tradeIn on the result to dealers toyotaDealer1, toyotaDealer2 and allDealer

    // TODO trade in hondas
    //    filter carsBought to just hondas
    //    call tradeIn on the result to dealers hondaDealer1 and allDealer    
}