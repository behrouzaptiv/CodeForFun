package com.aptiv.fika.data.mapper

import com.aptiv.fika.data.entity.OrganiserAndFika
import com.aptiv.fika.domain.entity.Event
import com.aptiv.fika.domain.entity.Person
import com.aptiv.fika.domain.entity.PersonAndEvent
import com.aptiv.fika.domain.mapper.IEntityMapper
import javax.inject.Inject


class PersonAndEventMapper  @Inject constructor() : IEntityMapper<OrganiserAndFika,PersonAndEvent > {
    override fun mapFromEntity(organiserAndFika: OrganiserAndFika): PersonAndEvent {
        return PersonAndEvent(person = Person(organiserAndFika.organiser.id, organiserAndFika.organiser.name),
            organiserAndFika.fikaList.map { Event(it.id,it.date) }
        )
    }
}