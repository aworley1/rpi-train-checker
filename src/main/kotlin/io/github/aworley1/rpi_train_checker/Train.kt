package io.github.aworley1.rpi_train_checker

import io.github.aworley1.rpi_train_checker.TrainStatus.CANCELLED
import io.github.aworley1.rpi_train_checker.TrainStatus.DELAYED_OVER_THRESHOLD
import io.github.aworley1.rpi_train_checker.TrainStatus.DELAYED_UNDER_THRESHOLD
import io.github.aworley1.rpi_train_checker.TrainStatus.ON_TIME
import io.github.aworley1.rpi_train_checker.TrainStatus.UNKNOWN
import java.time.Duration
import java.time.LocalTime

const val DELAY_THRESHOLD = 2

data class Train(
        val scheduledTimeOfDeparture: String?,
        val estimatedTimeOfDeparture: String?,
        val isCancelled: Boolean,
        val isCancelledAtDestination: Boolean
) {
    fun getStatus(): TrainStatus {
        return when {
            isCancelled -> CANCELLED
            isCancelledAtDestination -> CANCELLED
            estimatedTimeOfDeparture.equals("On time", true) -> ON_TIME
            !estimatedTimeOfDeparture.isTime() -> DELAYED_OVER_THRESHOLD
            hasDelayUnderThreshold() -> DELAYED_UNDER_THRESHOLD
            hasDelayOverThreshold() -> DELAYED_OVER_THRESHOLD
            else -> UNKNOWN
        }
    }

    private fun hasDelayUnderThreshold(): Boolean {
        if (scheduledTimeOfDeparture.isTime() && estimatedTimeOfDeparture.isTime()) {
            val delay = Duration.between(scheduledTimeOfDeparture!!.toTime(), estimatedTimeOfDeparture!!.toTime())
                    .toMinutes()
                    .toInt()

            return delay <= DELAY_THRESHOLD
        } else {
            return false
        }
    }

    private fun hasDelayOverThreshold(): Boolean {
        if (scheduledTimeOfDeparture.isTime() && estimatedTimeOfDeparture.isTime()) {
            val delay = Duration.between(scheduledTimeOfDeparture!!.toTime(), estimatedTimeOfDeparture!!.toTime())
                    .toMinutes()
                    .toInt()

            return delay > DELAY_THRESHOLD
        } else {
            return false
        }
    }
}

enum class TrainStatus {
    ON_TIME,
    CANCELLED,
    DELAYED_UNDER_THRESHOLD,
    DELAYED_OVER_THRESHOLD,
    UNKNOWN
}

private fun String?.isTime(): Boolean {
    return if (this?.toTime() != null) true else false
}

private fun String.toTime(): LocalTime? {
    return try {
        LocalTime.parse(this)
    } catch (e: Exception) {
        return null
    }
}

