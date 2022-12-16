package com.aptiv.fika.data.mapper

import com.aptiv.fika.data.entity.Organiser
import com.aptiv.fika.domain.entity.Person
import com.aptiv.fika.domain.mapper.IEntityMapper
import javax.inject.Inject

class PersonMapper @Inject constructor() : IEntityMapper<Organiser,Person > {
    override fun mapFromEntity(organiser: Organiser): Person {
        return Person(organiser.id, organiser.name)
    }
}