package com.olavarria.domain.bean

data class SportEvents (
    val sections: List<Section>? = null,
    val pagination: Pagination?,
    val items: List<Item>? = null
)

data class Section (
    val index: Int?,
    val _id: String?,
    val title: TitleOriginal?
)

data class TitleOriginal (
    val original: String? = null
)

data class Pagination (
    val first: Int?,
    val items: Int?,
    val page: Int?,
    val next: Int?,
    val pages: Int?,
    val offset: Int?,
    val last: Int?,
    val totalItems: Int?
)

data class Item (
    val matchDay: MatchDay? = null,
    val belong: Belong? = null,
    val children: Children? = null,
    val _createDate: String?,
    val _deleted: Boolean?,
    val _externalId: String?,
    val _externalProvider: String?,
    val _id: String?,
    val _model: String?,
    val _seed: Boolean?,
    val _updateDate: String?,
    val awayPenalties: Int? = null,
    val awayScore: Int?,
    val awayTeam: Team? = null,
    val cellType: Int?,
    val endDate: String?,
    val eventStatus: EventStatus? = null,
    val homePenalties: Int? = null,
    val homeScore: Int?,
    val homeTeam: Team? = null,
    val id: String?,
    val location: TitleOriginal? = null,
    val matchTime: Int?,
    val startDate: String?,
    val stats: List<Stat>? = null,
    val statusCategory: String?,
    val statusDate: String?,
    val type: String?,
    val videos: List<Any>? = null,

)

data class MatchDay (
    val start: String?,
    val end: String?,
    val name: TitleOriginal? = null,
    val phase: TitleOriginal? = null
)

data class Belong (
    val accessgroup: List<String>? = null,
    val tournament: List<String>? = null,
    val client: String?,
)

data class Children (
    val timeLine: List<String>? = null,
    val formation: List<String>? = null,
    val content: List<Any>? = null
)

data class Team (
    val _id: String?,
    val name: String?,
    val shortName: String?
)

data class EventStatus (
    val _id: String?,
    val category: String?,
    val name: Name? = null
)

data class Stat (
    val awayColor: String?,
    val awayValue: Int?,
    val format: TitleOriginal? = null,
    val homeColor: String?,
    val homeValue: Int?,
    val id: Any? = null,
    val priority: Int?,
    val title: Title? = null
)

data class Name (
    val es: String?,
    val original: String?
)

data class Title (
    val en: String?,
    val original: String?
)




