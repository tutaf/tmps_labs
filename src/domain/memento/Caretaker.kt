package domain.memento

import java.util.Stack

class Caretaker {
    private val mementos = Stack<OrderDetailsMemento>()

    fun saveState(memento: OrderDetailsMemento) {
        mementos.push(memento)
    }

    fun undo(): OrderDetailsMemento? {
        return if (mementos.size > 1) {
            mementos.pop()
            mementos.peek()
        } else {
            null
        }
    }
}