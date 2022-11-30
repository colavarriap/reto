package com.olavarria.domain.bean.mapper

import com.olavarria.data.api.response.ItemResponse
import com.olavarria.data.local.room.entity.EventsEntity

internal fun ItemResponse.toLocal(): EventsEntity {
    return EventsEntity(
        this.matchDay?.name?.original,
        this.matchDay,
        this.belong,
        this.children,
        this._createDate,
        this._deleted,
        this._externalId,
        this._externalProvider,
        this._model,
        this._seed,
        this._updateDate,
        this.awayPenalties,
        this.awayScore,
        this.awayTeam,
        this.cellType,
        this.endDate,
        this.eventStatus,
        this.homePenalties,
        this.homeScore,
        this.homeTeam,
        this.id!!,
        this.location,
        this.matchTime,
        this.startDate,
        this.stats,
        this.statusCategory,
        this.statusDate,
        this.type
    )
}