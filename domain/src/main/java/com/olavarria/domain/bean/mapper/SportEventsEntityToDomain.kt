package com.olavarria.domain.bean.mapper

import com.olavarria.data.api.response.*
import com.olavarria.domain.bean.*

internal fun SportEventsResponse.toDomain(): SportEvents {
    return SportEvents(
        this.sections?.map { it.toDomain() },
        this.pagination?.toDomain(),
        this.items?.map { it.toDomain() }
    )
}

internal fun SectionResponse.toDomain(): Section {
    return Section(
        this.index,
        this._id,
        this.title?.toDomain()
    )
}

internal fun PaginationResponse.toDomain(): Pagination {
    return Pagination(
        this.first,
        this.items,
        this.page,
        this.next,
        this.pages,
        this.offset,
        this.last,
        this.totalItems
    )
}

internal fun ItemResponse.toDomain(): Item {
    return Item(
        this.matchDay?.toDomain(),
        this.belong?.toDomain(),
        this.children?.toDomain(),
        this._createDate,
        this._deleted,
        this._externalId,
        this._externalProvider,
        this._id,
        this._model,
        this._seed,
        this._updateDate,
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
        this.type,
        this.videos
    )
}

internal fun TitleOriginalResponse.toDomain(): TitleOriginal {
    return TitleOriginal(
        this.original
    )
}

internal fun MatchDayResponse.toDomain(): MatchDay {
    return MatchDay(
        this.start,
        this.end,
        this.name?.toDomain(),
        this.phase?.toDomain()
    )
}

internal fun BelongResponse.toDomain(): Belong {
    return Belong(
        this.accessgroup,
        this.tournament,
        this.client
    )
}

internal fun ChildrenResponse.toDomain(): Children {
    return Children(
        this.timeLine,
        this.formation,
        this.content
    )
}

internal fun TeamResponse.toDomain(): Team {
    return Team(
        this._id,
        this.name,
        this.shortName
    )
}

internal fun EventStatusResponse.toDomain(): EventStatus {
    return EventStatus(
        this._id,
        this.category,
        this.name?.toDomain()
    )
}

internal fun StatResponse.toDomain(): Stat {
    return Stat(
        this.awayColor,
        this.awayValue,
        this.format?.toDomain(),
        this.homeColor,
        this.homeValue,
        this.id,
        this.priority,
        this.title?.toDomain()
    )
}

internal fun NameResponse.toDomain(): Name {
    return Name(
        this.es,
        this.original
    )
}

internal fun TitleResponse.toDomain(): Title {
    return Title(
        this.en,
        this.original
    )
}
