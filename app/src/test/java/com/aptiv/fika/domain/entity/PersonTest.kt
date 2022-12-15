package com.aptiv.fika.domain.entity

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PersonTest {

    private lateinit var person: Person

    @Before
    fun setUp() {
        person = MockPersonList().getPerson()
    }

    @Test
    fun getName() {
        val data = person.copy(name = "John Doe")
        assertEquals(person.name, data.name)
    }

    @Test
    fun getOrder() {
        val data = person.copy(order = 1)
        assertEquals(person.order, data.order)
    }
}