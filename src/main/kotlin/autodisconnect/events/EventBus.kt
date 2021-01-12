package autodisconnect.events

class EventBus {
    private val subscriptions: MutableMap<Events, MutableList<(x: Any) -> Unit>> = mutableMapOf()

    fun subscribe(event: Events, handler: (x: Any) -> Unit) {
        if (subscriptions.containsKey(event)) {
            subscriptions[event]!!.add(handler)
        } else {
            subscriptions[event] = mutableListOf(handler)
        }
    }

    fun publish(event: Events, x: Any): Boolean {
        if (subscriptions.containsKey(event)) {
            for (handler in subscriptions[event]!!) {
                handler(x)
            }
            return true
        }
        return false
    }
}