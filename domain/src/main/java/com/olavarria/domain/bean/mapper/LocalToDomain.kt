package com.olavarria.domain.bean.mapper

import com.olavarria.data.local.room.entity.EventsEntity
import com.olavarria.domain.bean.Item

internal fun EventsEntity.toDomain(): Item {
    return Item(
        this.matchDay?.toDomain(),
        this.belong?.toDomain(),
        this.children?.toDomain(),
        this.createDate,
        this.deleted,
        this.externalId,
        this.externalProvider,
        this.id,
        this.model,
        this.seed,
        this.updateDate,
        this.awayPenalties,
        this.awayScore,
        this.awayTeam?.toDomain(),
        this.cellType,
        this.endDate,
        this.eventStatus?.toDomain(),
        this.homePenalties,
        this.homeScore,
        this.homeTeam?.toDomain(),
        this.id,
        this.location?.toDomain(),
        this.matchTime,
        this.startDate,
        this.stats?.map { it.toDomain() },
        this.statusCategory,
        this.statusDate,
        this.type
    )
}