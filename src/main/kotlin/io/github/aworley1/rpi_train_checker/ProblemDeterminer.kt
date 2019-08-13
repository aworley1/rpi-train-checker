package io.github.aworley1.rpi_train_checker

import java.time.Clock
import java.time.Duration
import java.time.LocalTime
import java.time.ZonedDateTime

typealias ProblemDeterminer = (times: List<String>) -> Unit

fun createProblemDeterminer(
        departureStation: String,
        destinationStation: String,
        getTrains: GetTrains,
        clock: Clock
): ProblemDeterminer {
    return { requestedTrains ->
        val currentTime = ZonedDateTime.ofInstant(clock.instant(), TIMEZONE).toLocalTime()

        val requestedTimes = requestedTrains
                .map { LocalTime.parse(it) }

        if (requestedTimes.any { withinThreshold(it, currentTime) }) {
            getTrains(departureStation, destinationStation)
        }
    }
}

private fun withinThreshold(time: LocalTime, currentTime: LocalTime) = Duration.between(currentTime, time).toMinutes() <= 60L