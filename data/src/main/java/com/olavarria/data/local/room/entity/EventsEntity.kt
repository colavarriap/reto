package com.olavarria.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.olavarria.data.api.response.*

@Entity
data class EventsEntity (
    val matchDayName: String?,
    val matchDay: MatchDayResponse? = null,
    val belong: BelongResponse? = null,
    val children: ChildrenResponse? = null,
    val createDate: String?,
    val deleted: Boolean?,
    val externalId: String?,
    val externalProvider: String?,
    val model: String?,
    val seed: Boolean?,
    val updateDate: String?,
    val awayPenalties: Int? = null,
    val awayScore: Int?,
    val awayTeam: TeamResponse? = null,
    val cellType: Int?,
    val endDate: String?,
    val eventStatus: EventStatusResponse? = null,
    val homePenalties: Int? = null,
    val homeScore: Int?,
    val homeTeam: TeamResponse? = null,
    @PrimaryKey
    val id: String,
    val location: TitleOriginalResponse? = null,
    val matchTime: Int?,
    val startDate: String?,
    val stats: List<StatResponse>? = null,
    val statusCategory: String?,
    val statusDate: String?,
    val type: String?
)

