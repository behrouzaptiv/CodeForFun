package com.aptiv.fika.domain.usecase

import com.aptiv.fika.domain.entity.Person
import com.aptiv.fika.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.aptiv.fika.domain.extension.Result
class GetPersonById @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(id: Int):  Result<Person> =
        repository.getPersonById(id)
}
