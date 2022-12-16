package com.aptiv.fika.data

import com.aptiv.fika.domain.entity.Event
import com.aptiv.fika.domain.entity.Person
import com.aptiv.fika.domain.entity.PersonAndEvent
import com.aptiv.fika.domain.extension.Result
import com.aptiv.fika.domain.repository.DataSource
import com.aptiv.fika.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val dataSource: DataSource): Repository{
    override fun getAllPerson(): Flow<Result<List<Person>>> {
       return dataSource.getAllPerson()
    }

    override fun getAllPersonAndFika(): Flow<Result<List<PersonAndEvent>>> {
        return dataSource.getAllPersonAndFika()
    }

    override suspend fun getPersonById(id: Int): Result<Person> {
        return dataSource.getPersonById(id)
    }

    override suspend fun addPerson(person: Person): Result<Boolean> {
        return dataSource.addPerson(person)
    }

    override suspend fun removePerson(person: Person): Result<Boolean> {
        return dataSource.removePerson(person)
    }

    override suspend fun assignFika(person: Person, event: Event): Result<Boolean> {
        return dataSource.assignFika(person,event)
    }
}