package com.betsson.foursquare.data.remote.model

import com.betsson.foursquare.model.*

data class NetworkResult(
    val categories: List<NetworkCategory>,
    val distance: Int,
    val fsq_id: String,
    val networkHours: NetworkHours,
    val networkLocation: NetworkLocation,
    val name: String,
    val networkPhotos: List<NetworkPhoto>,
    val popularity: Int,
    val price: Int,
    val rating: Int,
    val tel: String,
    val networkTips: List<NetworkTip>,
    val verified: Boolean,
    val website: String
)

fun NetworkResult.asListModel() = VenueItem(
    distance = distance,
    fsq_id = fsq_id,
    name = name,
    photoUrl = networkPhotos.first().tip.url,
    price = price,
    rating = rating,
)

fun NetworkResult.asModel() = Venue(
    categories = categories.map { it.name },
    distance = distance,
    fsq_id = fsq_id,
    hours = Hours(
        display = networkHours.display,
        isLocalHoliday = networkHours.is_local_holiday,
        openNow = networkHours.open_now,
        regular = networkHours.networkRegular.map {
            Regular(
                close = it.close,
                day = it.day,
                open = it.open,
            )
        }
    ),
    location = Location(networkLocation.address),
    name = name,
    photos = networkPhotos.map { Photo(url = it.tip.url) },
    popularity = popularity,
    price = price,
    rating = rating,
    tel = tel,
    tips = networkTips.map {
        Tip(
            agreeCount = it.agree_count,
            createdAt = it.created_at,
            disagreeCount = it.disagree_count,
            id = it.id,
            lang = it.lang,
            text = it.text,
            url = it.url,
        )
    },
    verified = verified,
    website = website,
)
