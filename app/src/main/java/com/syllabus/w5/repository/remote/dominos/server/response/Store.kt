package com.syllabus.w5.repository.remote.dominos.server.response

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Store(
    val StoreID: String = "",
    val IsDeliveryStore: Boolean = false,
    val Phone: String = "",
    val AddressDescription: String = "",
    val HolidayDescription: String = "",
    val HoursDescription: String = "",
    // val serviceHoursDescription: ServiceHoursDescription,
    val IsOnlineCapable: Boolean = false,
    val IsOnlineNow: Boolean = false,
    val IsNEONow: Boolean = false,
    val IsSpanish: Boolean = false,
    val LocationInfo: String = "",
    // val languageLocationInfo: LanguageLocationInfo,
    val AllowDeliveryOrders: Boolean = false,
    val AllowCarryoutOrders: Boolean = false,
    val AllowDuc: Boolean = false,
    // val serviceMethodEstimateWaitMinutes,
    // val storeCoordinates: StoreCoordinates,
    val AllowPickupWindowOrders: Boolean = false,
    val ContactlessDelivery: String = "",
    val ContactlessCarryout: String = "",
    val IsOpen: Boolean = false,
    // val serviceIsOpen: ServiceIsOpen
)
