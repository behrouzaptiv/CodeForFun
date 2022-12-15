package com.aptiv.fika.domain.usecase

import com.aptiv.fika.domain.entity.MockPersonList
import com.aptiv.fika.domain.entity.Person
import com.aptiv.fika.domain.extension.Result
import com.aptiv.fika.domain.repository.Repository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetAllPersonTest {

    @RelaxedMockK
    lateinit var repository: Repository

    private lateinit var getAllPerson: GetAllPerson
    private lateinit var personList: List<Person>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getAllPerson = GetAllPerson(repository)
        personList = MockPersonList().getPersonList()
    }


    @Test
    fun `flow is success result personList`() {
        runBlocking {
            coEvery { repository.getAllPerson() } returns flow { emit(Result.Success(personList)) }

            val data =  getAllPerson.invoke()

            coVerify { getAllPerson.invoke() }

            data.collectLatest { personList ->
                Assert.assertEquals(personList is Result.Success<*>, true)
            }
        }
    }


    @Test
    fun `flow is loading result personList`() {
        runBlocking {
            coEvery { repository.getAllPerson() } returns flow { emit(Result.Loading) }

            val data =  getAllPerson.invoke()

            coVerify { getAllPerson.invoke() }

            data.collectLatest { personList ->
                Assert.assertEquals(personList is Result.Loading, true)
            }
        }
    }


    @Test
    fun `flow is error result personList`() {
        runBlocking {
            coEvery { repository.getAllPerson() } returns flow { emit(Result.Error("Error")) }

            val data =  getAllPerson.invoke()

            coVerify { getAllPerson.invoke() }

            data.collectLatest { personList ->
                Assert.assertEquals(personList is Result.Error, true)
                Assert.assertEquals((personList as Result.Error).error, "Error")
            }
        }
    }

}