package com.aptiv.fika.domain.repository

import com.aptiv.fika.domain.entity.Person
import com.aptiv.fika.domain.extension.Result
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getAllPerson() : Flow<Result<List<Person>>>

    fun getPersonById(id: Int) : Result<Person>

    fun addPerson(person: Person) : Result<Boolean>

    fun removePerson(person: Person) : Result<Boolean>

}