package io.github.aworley1.rpi_train_checker

data class Train(
        val scheduledTimeOfDeparture: String?,
        val estimatedTimeOfDeparture: String?,
        val isCancelled: Boolean,
        val isCancelledAtDestination: Boolean
) {
    fun hasIssues(): Boolean {
        return when {
            isCancelled -> true
            isCancelledAtDestination -> true
            estimatedTimeOfDeparture != "On time" -> true
            else -> false
        }
    }
}