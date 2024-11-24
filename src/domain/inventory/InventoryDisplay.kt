package domain.inventory

import domain.observer.Observer
import domain.observer.Subject

class InventoryDisplay : Observer {
    override fun update(subject: Subject) {
        if (subject is Inventory) {
            println("Inventory has been updated:")
            println("----------")
            subject.showInventory()
            println("----------")
        }
    }
}