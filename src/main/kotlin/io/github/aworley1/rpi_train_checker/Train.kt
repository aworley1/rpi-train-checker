package io.github.aworley1.rpi_train_checker

import io.github.aworley1.rpi_train_checker.TrainStatus.CANCELLED
import io.github.aworley1.rpi_train_checker.TrainStatus.DELAYED_OVER_THRESHOLD
import io.github.aworley1.rpi_train_checker.TrainStatus.ON_TIME
import io.github.aworley1.rpi_train_checker.TrainStatus.UNKNOWN
import java.time.LocalTime

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
            else -> UNKNOWN
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
    try {
        LocalTime.parse(this)
    } catch (e: Exception) {
        return false
    }

    return true
}