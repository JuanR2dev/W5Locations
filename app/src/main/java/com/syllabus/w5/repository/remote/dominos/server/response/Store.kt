package com.syllabus.w5.repository.remote.dominos.server.response

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class Store(
    @Json(name = "StoreID")
    val storeID: String = "",
    @Json(name = "IsDeliveryStore")
    val isDeliveryStore: Boolean = false,
    @Json(name = "Phone")
    val phone: String = "",
    @Json(name = "AddressDescription")
    val addressDescription: String = "",
    @Json(name = "HolidayDescription")
    val holidayDescription: String = "",
    @Json(name = "HoursDescription")
    val hoursDescription: String = "",
    // val serviceHoursDescription: ServiceHoursDescription,
    @Json(name = "IsOnlineCapable")
    val isOnlineCapable: Boolean = false,
    @Json(name = "IsOnlineNow")
    val isOnlineNow: Boolean = false,
    @Json(name = "IsNEONow")
    val isNEONow: Boolean = false,
    @Json(name = "IsSpanish")
    val isSpanish: Boolean = false,
    @Json(name = "LocationInfo")
    val locationInfo: String = "",
    // val languageLocationInfo: LanguageLocationInfo,
    @Json(name = "AllowDeliveryOrders")
    val allowDeliveryOrders: Boolean = false,
    @Json(name = "AllowCarryoutOrders")
    val allowCarryoutOrders: Boolean = false,
    @Json(name = "AllowDuc")
    val allowDuc: Boolean = false,
    // val serviceMethodEstimateWaitMinutes,
    // val storeCoordinates: StoreCoordinates,
    @Json(name = "AllowPickupWindowOrders")
    val allowPickupWindowOrders: Boolean = false,
    @Json(name = "ContactlessDelivery")
    val contactlessDelivery: Boolean = false,
    @Json(name = "ContactlessCarryout")
    val contactlessCarryout: Boolean = false,
    @Json(name = "IsOpen")
    val isOpen: Boolean = false,
    // val serviceIsOpen: ServiceIsOpen
)
