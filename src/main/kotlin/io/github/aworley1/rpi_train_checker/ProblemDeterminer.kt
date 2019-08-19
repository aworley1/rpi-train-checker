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
    return determiner@{ requestedTrainTimes ->
        val currentTime = ZonedDateTime.ofInstant(clock.instant(), TIMEZONE).toLocalTime()

        val requestedTimesWithinThreshold = requestedTrainTimes
                .map { LocalTime.parse(it) }
                .filter { withinThreshold(it, currentTime) }

        if (requestedTimesWithinThreshold.isEmpty()) {
            return@determiner NO_PROBLEM
        }

        val trains = getTrains(departureStation, destinationStation)

        val requestedTrains = trains.filter { requestedTrainTimes.contains(it.scheduledTimeOfDeparture) }

        return@determiner when {
            allRequestedTrainsNotReturned(requestedTimesWithinThreshold, trains) -> PROBLEM
            requestedTrains.notAllOnTime() -> PROBLEM
            else -> NO_PROBLEM
        }

    }
}

private fun List<Train>.notAllOnTime() =
        !this.all { it.getStatus() == TrainStatus.ON_TIME }

private fun allRequestedTrainsNotReturned(requestedTimes: List<LocalTime>, trains: List<Train>) =
        !requestedTimes.all { trains.hasTime(it) }

private fun List<Train>.hasTime(time: LocalTime) =
        this.any { it.scheduledTimeOfDeparture == time.toString() }

private fun withinThreshold(time: LocalTime, currentTime: LocalTime): Boolean {
    return Duration.between(currentTime, time).toMinutes() in 0L..60L
}

enum class ProblemStatus {
    PROBLEM,
    NO_PROBLEM
}