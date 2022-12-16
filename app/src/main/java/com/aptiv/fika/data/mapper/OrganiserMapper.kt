package com.aptiv.fika.data.mapper

import com.aptiv.fika.data.entity.Organiser
import com.aptiv.fika.domain.entity.Person
import com.aptiv.fika.domain.mapper.IEntityMapper
import javax.inject.Inject

class OrganiserMapper  @Inject constructor() : IEntityMapper<Person, Organiser> {
    override fun mapFromEntity(person: Person): Organiser {
        return Organiser(person.id, person.name)
    }
}