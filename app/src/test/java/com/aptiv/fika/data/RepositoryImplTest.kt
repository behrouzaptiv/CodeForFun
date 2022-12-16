package com.aptiv.fika.data

import com.aptiv.fika.domain.entity.MockPersonList
import com.aptiv.fika.domain.entity.Person
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import com.aptiv.fika.domain.extension.Result
import org.junit.Assert

import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryImplTest {

    private val coroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var repositoryImpl: RepositoryImpl

    @RelaxedMockK
    private lateinit var localeDataSource: LocalDataSource

    private lateinit var personList: List<Person>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repositoryImpl = RepositoryImpl(localeDataSource)
        personList = MockPersonList().getPersonList()
    }


    @Test
    fun testGetAllPersonListOnSuccess() {
        coroutineDispatcher.runBlockingTest {
            coEvery { localeDataSource.getAllPerson() } returns flowOf(
                Result.Success(
                    personList
                )
            )

            val repo = repositoryImpl.getAllPerson()

            repo.collectLatest {
                println(it)
                if(it is Result.Success) {
                    Assert.assertEquals(it.data.size, personList.size)
                }
            }
        }
    }

    @Test
    fun testGetAllPersonOnError() {
        coroutineDispatcher.runBlockingTest {
            coEvery { localeDataSource.getAllPerson() } returns flowOf(
                Result.Error(
                    "Error"
                )
            )

            val repo = repositoryImpl.getAllPerson()

            repo.collectLatest {
                println(it)
                if(it is Result.Error) {
                    Assert.assertEquals(it.error, "Error")
                }
            }
        }
    }
}