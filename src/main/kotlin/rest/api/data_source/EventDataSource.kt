package rest.api.data_source

import rest.api.domain.events.Event

object EventDataSource {
    fun getEvents(): List<Event> {
        return listOf(Event("football", "sports"), Event("rugby", "sports"), Event("handball", "sports"), Event("basketball", "sports"))
    }
}