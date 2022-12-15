package com.aptiv.fika.domain.extension

import kotlinx.coroutines.flow.Flow
import org.junit.Assert
import org.junit.Test


internal class ResultTest{

    @Test
    fun `result is loading`() {
        val result = getResult(type = 1)
        Assert.assertEquals(result is Result.Loading,true)
    }

    @Test
    fun `result is success`() {
        val result = getResult(type = 2)

        Assert.assertEquals(result is Result.Success,true)
        Assert.assertEquals((result as Result.Success).data, "Value")
    }


    @Test
    fun `result is error`() {
        val result = getResult(type = 3)

        Assert.assertEquals(result is Result.Error,true)
        Assert.assertEquals((result as Result.Error).error, "Error")
    }


    @Test
    fun `repoFlow is flow of result`() {
        val repo = repoFlow { getRepoFlow() }
        Assert.assertEquals(repo is Flow<Result<String>>, true)
        Assert.assertEquals(repo , "Repo")
    }


    private fun getRepoFlow() = "Repo"

    private fun getResult(type: Int): Result<String> {
        return when (type) {
            1 -> {
                Result.Loading
            }
            2 -> {
                Result.Success(data = "Value")
            }
            else -> {
                Result.Error("Error")
            }
        }
    }

}