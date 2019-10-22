package rest.api.domain.events

data class EventResponse(val message: String, val events: List<Event>? = null)