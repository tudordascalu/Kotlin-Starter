package rest.api.db

import rest.api.domain.events.Event
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement

class DbHelper(val connection: Connection) {
    fun getEvents(): List<Event> {
        val statement: Statement = connection.createStatement()
        val resultSet: ResultSet =  statement.executeQuery("SELECT * FROM events")
        val eventList = mutableListOf<Event>()

        while(resultSet.next()) {
            val name = resultSet.getString("name")
            val type = resultSet.getString("type")
            eventList.add(Event(name, type))
        }

        return eventList
    }
}