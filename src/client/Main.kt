package client

import domain.facade.OrderFacade
import domain.inventory.Inventory
import domain.inventory.InventoryDisplay
import domain.memento.Caretaker
import domain.models.OrderDetailsBuilder
import domain.order.DiscountedOrder

fun main() {
    val caretaker = Caretaker()
    val orderDetailsBuilder = OrderDetailsBuilder("dinein")
    caretaker.saveState(orderDetailsBuilder.saveState())

    val inventoryDisplay = InventoryDisplay()
    Inventory.attach(inventoryDisplay)

    println("Welcome to the Restaurant Ordering System!")
    var isOrdering = true

    while (isOrdering) {
        println("\nPlease select an option:")
        println("1. Add Main Course")
        println("2. Add Drink")
        println("3. Add Dessert")
        println("4. Add Extra")
        println("5. Add Combo")
        println("6. Undo last action")
        println("7. Finish order")
        println("8. Show inventory")

        when (readLine()?.trim()) {
            "1" -> {
                println("Enter Main Course Name:")
                val item = readLine()?.trim()
                if (!item.isNullOrEmpty()) {
                    orderDetailsBuilder.setMainCourse(item)
                    caretaker.saveState(orderDetailsBuilder.saveState())
                    println("$item added as Main Course.")
                }
            }
            "2" -> {
                println("Enter Drink Name:")
                val item = readLine()?.trim()
                if (!item.isNullOrEmpty()) {
                    orderDetailsBuilder.setDrink(item)
                    caretaker.saveState(orderDetailsBuilder.saveState())
                    println("$item added as Drink.")
                }
            }
            "3" -> {
                println("Enter Dessert Name:")
                val item = readLine()?.trim()
                if (!item.isNullOrEmpty()) {
                    orderDetailsBuilder.setDessert(item)
                    caretaker.saveState(orderDetailsBuilder.saveState())
                    println("$item added as Dessert.")
                }
            }
            "4" -> {
                println("Enter Extra Item Name:")
                val item = readLine()?.trim()
                if (!item.isNullOrEmpty()) {
                    orderDetailsBuilder.addExtra(item)
                    caretaker.saveState(orderDetailsBuilder.saveState())
                    println("$item added as Extra.")
                }
            }
            "5" -> {
                println("Enter Combo Name:")
                val item = readLine()?.trim()
                if (!item.isNullOrEmpty()) {
                    orderDetailsBuilder.addCombo(item)
                    caretaker.saveState(orderDetailsBuilder.saveState())
                    println("$item added as Combo.")
                }
            }
            "6" -> {
                val previousState = caretaker.undo()
                if (previousState != null) {
                    orderDetailsBuilder.restoreState(previousState)
                    println("Last action undone.")
                } else {
                    println("Nothing to undo.")
                }
            }
            "7" -> {
                isOrdering = false
            }
            "8" -> {
                Inventory.showInventory()
            }
            else -> {
                println("Invalid option. Please try again.")
            }
        }
    }

    val orderDetails = orderDetailsBuilder.build()
    if (orderDetails.isEmpty()) {
        println("Your order is empty. Exiting.")
        return
    }

    val orderFacade = OrderFacade()
    var order = orderFacade.placeOrder(orderDetails) ?: return

    println("\nDo you have a discount card? (Y/N)")
    when (readLine()?.trim()?.lowercase()) {
        "y" -> order = DiscountedOrder(order)
    }

    order.printOrderTotal()
}
