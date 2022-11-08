package com.betsson.foursquare.data.remote.model

import com.betsson.foursquare.model.*
import com.google.gson.annotations.SerializedName

data class NetworkResult(
    val categories: List<NetworkCategory>?,
    val distance: Int,
    @SerializedName("fsq_id")
    val fsqId: String,
    val hours: NetworkHours?,
    val location: NetworkLocation?,
    val name: String?,
    val photos: List<NetworkPhoto>?,
    val popularity: Float,
    val price: Int,
    val rating: Float,
    val tel: String?,
    val tips: List<NetworkTip?>?,
    val verified: Boolean,
    val website: String?
)

fun NetworkResult.asListModel() = VenueItem(
    distance = distance,
    fsqId = fsqId,
    name = name,
    photoUrl = if (photos != null && photos.isNotEmpty()) photos.first().asModel("200x200") else "",
    price = price,
    rating = rating,
)

fun NetworkResult.asModel() = Venue(
    categories = categories?.map { it.name },
    distance = distance,
    fsq_id = fsqId,
    hours = Hours(
        display = hours?.display,
        isLocalHoliday = hours?.isLocalHoliday,
        openNow = hours?.openNow,
        regular = hours?.regular?.map {
            Regular(
                close = it.close,
                day = it.day,
                open = it.open,
            )
        }
    ),
    address = location?.address,
    name = name,
    photos = photos?.map { it.tip?.url },
    popularity = popularity,
    price = price,
    rating = rating,
    tel = tel,
    tips = tips?.let {
            it.filterNotNull().map { tip ->
                Tip(
                    agreeCount = tip.agreeCount,
                    createdAt = tip.created_at,
                    disagreeCount = tip.disagreeCount,
                    id = tip.id,
                    lang = tip.lang,
                    text = tip.text,
                    url = tip.url,
                )
            }
        },
    verified = verified,
    website = website,
)
