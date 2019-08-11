package io.github.aworley1.rpi_train_checker

import com.thalesgroup.rtti._2013_11_28.token.types.AccessToken
import com.thalesgroup.rtti._2017_10_01.ldb.GetBoardRequestParams
import com.thalesgroup.rtti._2017_10_01.ldb.LDBServiceSoap

typealias GetTrains = (departureStation: String) -> List<Train>

fun createGetTrains(ldbService: LDBServiceSoap, accessToken: AccessToken): GetTrains {
    return { departureStation ->
        val requestParams = GetBoardRequestParams().apply {
            crs = departureStation
        }
        ldbService.getDepBoardWithDetails(requestParams, accessToken)
        emptyList()
    }
}

class Train