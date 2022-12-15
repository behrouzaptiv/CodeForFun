package com.aptiv.fika.domain.usecase

import com.aptiv.fika.domain.entity.Person
import com.aptiv.fika.domain.repository.Repository
import com.aptiv.fika.domain.extension.Result
import javax.inject.Inject

class AddPerson @Inject constructor(
    private val repository: Repository
) {
   suspend operator fun invoke(person: Person): Result<Boolean> = repository.addPerson(person)
}
