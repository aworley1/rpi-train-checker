package io.github.aworley1.rpi_train_checker

import io.github.aworley1.rpi_train_checker.ProblemStatus.NO_PROBLEM
import io.github.aworley1.rpi_train_checker.ProblemStatus.PROBLEM
import java.time.Clock
import java.time.Duration
import java.time.LocalTime
import java.time.ZonedDateTime

typealias ProblemDeterminer = (times: List<String>) -> ProblemStatus

fun createProblemDeterminer(
        departureStation: String,
        destinationStation: String,
        getTrains: GetTrains,
        clock: Clock
): ProblemDeterminer {
    return determine@{ requestedTrains ->
        val currentTime = ZonedDateTime.ofInstant(clock.instant(), TIMEZONE).toLocalTime()

        val requestedTimesWithinThreshold = requestedTrains
                .map { LocalTime.parse(it) }
                .filter { withinThreshold(it, currentTime) }

        if (requestedTimesWithinThreshold.isEmpty()) {
            return@determine NO_PROBLEM
        }

        val trains = getTrains(departureStation, destinationStation)

        return@determine when {
            !requestedTimesWithinThreshold.all { trains.hasTime(it) } -> PROBLEM
            else -> NO_PROBLEM
        }

    }
}

private fun List<Train>.hasTime(time: LocalTime) =
        this.any { it.scheduledTimeOfDeparture == time.toString() }

private fun withinThreshold(time: LocalTime, currentTime: LocalTime) = Duration.between(currentTime, time).toMinutes() <= 60L

enum class ProblemStatus {
    PROBLEM,
    NO_PROBLEM
}