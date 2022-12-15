package com.aptiv.fika.domain.entity

class MockPersonList {

    fun getPersonList() = listOf<Person>(
        Person(
            id= 1,
            name = "John Doe",
            order = 1
        )
    )

    fun getPerson() =
        Person(
            id= 1,
            name = "John Doe",
            order = 1
        )
}