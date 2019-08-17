package io.github.aworley1.rpi_train_checker

import com.pi4j.io.gpio.GpioFactory
import com.thalesgroup.rtti._2013_11_28.token.types.AccessToken
import com.thalesgroup.rtti._2017_10_01.ldb.Ldb
import java.time.Clock
import java.time.ZoneId

val TIMEZONE = ZoneId.of("Europe/London")

fun main() {
    val requestedTimes = System.getProperty("TRAINS_REQUESTED_TIMES").split(",")
    val departureStation = System.getProperty("TRAINS_DEPARTURE_STATION")
    val destinationStation = System.getProperty("TRAINS_DESTINATION_STATION")
    val accessToken = System.getProperty("TRAINS_ACCESS_TOKEN")

    val statusReporter = createStatusReporter(GpioFactory.getInstance())

    val problemDeterminer = createProblemDeterminer(
            departureStation = departureStation,
            destinationStation = destinationStation,
            getTrains = createGetTrains(
                    ldbService = Ldb().ldbServiceSoap,
                    accessToken = AccessToken().apply { tokenValue = accessToken }
            ),
            clock = Clock.systemUTC()
    )

    statusReporter(problemDeterminer(requestedTimes))
}