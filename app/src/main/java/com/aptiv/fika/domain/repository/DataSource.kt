package com.aptiv.fika.domain.repository

import com.aptiv.fika.domain.entity.Event
import com.aptiv.fika.domain.entity.Person
import com.aptiv.fika.domain.entity.PersonAndEvent
import com.aptiv.fika.domain.extension.Result
import kotlinx.coroutines.flow.Flow

interface DataSource {

    fun getAllPerson() : Flow<Result<List<Person>>>

    fun getAllPersonAndFika() : Flow<Result<List<PersonAndEvent>>>

    suspend fun getPersonById(id: Int) : Result<Person>

    suspend fun addPerson(person: Person) : Result<Boolean>

    suspend fun removePerson(person: Person) : Result<Boolean>

    suspend fun assignFika(person: Person,event: Event ) : Result<Boolean>
}