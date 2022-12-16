package com.aptiv.fika.data

import android.util.Log
import com.aptiv.fika.data.dao.FikaDao
import com.aptiv.fika.data.dao.OrganizerDao
import com.aptiv.fika.data.entity.Fika
import com.aptiv.fika.data.mapper.OrganiserMapper
import com.aptiv.fika.data.mapper.PersonAndEventMapper
import com.aptiv.fika.data.mapper.PersonMapper
import com.aptiv.fika.domain.entity.Event
import com.aptiv.fika.domain.entity.Person
import com.aptiv.fika.domain.entity.PersonAndEvent
import com.aptiv.fika.domain.extension.Result
import com.aptiv.fika.domain.repository.DataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.Date
import javax.inject.Inject

class LocalDataSource @Inject constructor (private val organizerDao: OrganizerDao,
                                           private val fikaDao: FikaDao,
                                           private val dispatcher: CoroutineDispatcher,
                                           private val mapper: OrganiserMapper,
                                           private val personMapper: PersonMapper,
                                           private val personAndEventMapper: PersonAndEventMapper) : DataSource {

    private val TAG = "LocalDataSource"
    override fun getAllPerson(): Flow<Result<List<Person>>> {
        return flow <Result<List<Person>>>{
            addPerson(Person(1, "John"))
            addPerson(Person(2, "Alex"))
            addPerson(Person(3, "ABC"))
            addPerson(Person(4, "PQr"))
            assignFika(Person(1, "John"), Event(1, Date()))
            assignFika(Person(2, "Alex"), Event(2, Date()))
            try {
                val personList = organizerDao.getAll()
                    .map {
                        personMapper.mapFromEntity(it)
                    }
                Log.d(TAG, "getAllPerson() called $personList")

                emit(Result.Success(personList))
            } catch (e: Exception) {
                Result.Error(e.localizedMessage)
            }
        }.flowOn(dispatcher)
    }

    override fun getAllPersonAndFika(): Flow<Result<List<PersonAndEvent>>> {
        return flow <Result<List<PersonAndEvent>>>{
                        try {
                val personAndEventList = organizerDao.getAllFika().map {
                    personAndEventMapper.mapFromEntity(it)
                }
                Log.d(TAG, "getAllPersonAndFika() called $personAndEventList")

                emit(Result.Success(personAndEventList))
            } catch (e: Exception) {
                Result.Error(e.localizedMessage)
            }
        }.flowOn(dispatcher)
    }

    override suspend fun getPersonById(id: Int): Result<Person> {
        return try {
            val organiser = organizerDao.findByOrganiserId(id)
            Result.Success(personMapper.mapFromEntity(organiser))
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }

    override suspend fun addPerson(person: Person): Result<Boolean> {
        return try {
            val isAdded = organizerDao.insertAll(mapper.mapFromEntity(person))
            Log.d(TAG, "addPerson()  returned isAdded $isAdded")
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }

    override suspend fun removePerson(person: Person): Result<Boolean> {
        return try {
            val isRemoved = organizerDao.delete(mapper.mapFromEntity(person))
            Log.d(TAG, "removePerson() returned isRemoved $isRemoved")
            Result.Success(isRemoved!= 0)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }

    override suspend fun assignFika(person: Person,event: Event): Result<Boolean> {
        return try {
            val isAssigned = fikaDao.insertAll(Fika(organiserId = person.id, date = event.date))
            Log.d(TAG, "assignFika()  returned isAdded $isAssigned")
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }

}