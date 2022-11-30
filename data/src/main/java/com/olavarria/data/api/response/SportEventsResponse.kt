package com.olavarria.data.api.response

import com.squareup.moshi.Json

data class SportEventsResponse (
    val sections: List<SectionResponse>? = null,
    val pagination: PaginationResponse?,
    val items: List<ItemResponse>? = null
)

data class SectionResponse (
    val index: Int?,
    val _id: String?,
    val title: TitleOriginalResponse?
)

data class TitleOriginalResponse (
    val original: String?
)

data class PaginationResponse (
    val first: Int?,
    val items: Int?,
    val page: Int?,
    val next: Int?,
    val pages: Int?,
    val offset: Int?,
    val last: Int?,
    val totalItems: Int?
)

data class ItemResponse (
    val matchDay: MatchDayResponse? = null,
    @Json(name = "__belong")
    val belong: BelongResponse? = null,
    @Json(name = "__children")
    val children: ChildrenResponse? = null,
    val _createDate: String?,
    @Json(name = "_deleted")
    val _deleted: Boolean?,
    val _externalId: String?,
    val _externalProvider: String?,
    val _id: String?,
    val _model: String?,
    val _seed: Boolean?,
    val _updateDate: String?,
    val awayPenalties: Int? = null,
    val awayScore: Int?,
    val awayTeam: TeamResponse? = null,
    val cellType: Int?,
    val endDate: String?,
    val eventStatus: EventStatusResponse? = null,
    val homePenalties: Int? = null,
    val homeScore: Int?,
    val homeTeam: TeamResponse? = null,
    val id: String?,
    val location: TitleOriginalResponse? = null,
    val matchTime: Int?,
    val startDate: String?,
    val stats: List<StatResponse>? = null,
    val statusCategory: String?,
    val statusDate: String?,
    val type: String?,
    val videos: List<Any>? = null,

)

data class MatchDayResponse (
    val start: String?,
    val end: String?,
    val name: TitleOriginalResponse? = null,
    val phase: TitleOriginalResponse? = null
)

data class BelongResponse (
    @Json(name = "AccessGroup")
    val accessgroup: List<String>? = null,
    @Json(name = "Tournament")
    val tournament: List<String>? = null,
    @Json(name = "Client")
    val client: String?,
)

data class ChildrenResponse (
    @Json(name = "TimeLine")
    val timeLine: List<String>? = null,
    @Json(name = "Formation")
    val formation: List<String>? = null,
    @Json(name = "Content")
    val content: List<Any>? = null
)

data class TeamResponse (
    val _id: String?,
    val name: String?,
    val shortName: String?
)

data class EventStatusResponse (
    val _id: String?,
    val category: String?,
    val name: NameResponse? = null
)

data class StatResponse (
    val awayColor: String?,
    val awayValue: Int?,
    val format: TitleOriginalResponse? = null,
    val homeColor: String?,
    val homeValue: Int?,
    val id: Any? = null,
    val priority: Int?,
    val title: TitleResponse? = null
)

data class NameResponse (
    val es: String?,
    val original: String?
)

data class TitleResponse (
    val en: String?,
    val original: String?
)




