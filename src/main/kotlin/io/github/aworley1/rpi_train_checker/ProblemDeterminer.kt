package io.github.aworley1.rpi_train_checker

typealias ProblemDeterminer = (times: List<String>) -> Unit

fun createProblemDeterminer(
        departureStation: String,
        destinationStation: String,
        getTrains: GetTrains
): ProblemDeterminer {
    return {
        getTrains(departureStation, destinationStation)
    }
}