package com.aptiv.fika.domain.usecase

import com.aptiv.fika.domain.entity.Person
import com.aptiv.fika.domain.entity.PersonAndEvent
import com.aptiv.fika.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import com.aptiv.fika.domain.extension.Result
import javax.inject.Inject

class GetPersonAndEvent @Inject constructor(
    private val repository: Repository
) {
   operator fun invoke(): Flow<Result<List<PersonAndEvent>>> =
        repository.getAllPersonAndFika()
}
