package io.github.aworley1.rpi_train_checker

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import io.github.aworley1.rpi_train_checker.ProblemStatus.NO_PROBLEM
import io.github.aworley1.rpi_train_checker.ProblemStatus.PROBLEM
import io.github.aworley1.rpi_train_checker.ProblemStatus.NO_TRAINS_REQUESTED
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.time.Clock
import java.time.Instant

val ELEVEN_AM_CLOCK = Clock.fixed(
        Instant.parse("2015-01-01T11:00:00Z"),
        TIMEZONE
)

object ProblemDeterminerTest : Spek({
    describe("Given there are trains requested within the next 90 mins") {
        val times = listOf("11:23")
        val mockGetTrains = MockGetTrains()
        val problemDeterminer = createProblemDeterminer(
                "departure-station",
                "destination-station",
                mockGetTrains.mockGetOnTimeTrains,
                ELEVEN_AM_CLOCK
        )

        val result = problemDeterminer(times)

        it("should call getTrains with correct departure station") {
            assertThat(mockGetTrains.wasCalledWith?.departureStation)
                    .isEqualTo("departure-station")
        }

        it("should call getTrains with correct destination station") {
            assertThat(mockGetTrains.wasCalledWith?.destinationStation)
                    .isEqualTo("destination-station")
        }

        it("should return No Problem") {
            assertThat(result).isEqualTo(NO_PROBLEM)
        }
    }

    describe("Given requested trains are missing from the list") {
        val mockGetTrains = MockGetTrains()
        val problemDeterminer = createProblemDeterminer(
                "departure-station",
                "destination-station",
                mockGetTrains.mockGetOnTimeTrains,
                ELEVEN_AM_CLOCK
        )

        it("should determine there is a problem when a train is missing") {
            val times = listOf("11:23", "11:30")
            assertThat(problemDeterminer(times)).isEqualTo(PROBLEM)
        }
    }

    describe("Given there are NO trains requested within the next 90 mins") {
        val times = listOf("10:30", "15:00")
        val mockGetTrains = MockGetTrains()
        val problemDeterminer = createProblemDeterminer(
                "departure-station",
                "destination-station",
                mockGetTrains.mockGetOnTimeTrains,
                ELEVEN_AM_CLOCK
        )

        val result = problemDeterminer(times)

        it("should NOT call GetTrains") {
            assertThat(mockGetTrains.wasCalledWith)
                    .isNull()
        }

        it("should return no problem") {
            assertThat(result).isEqualTo(NO_TRAINS_REQUESTED)
        }
    }

    describe("Given trains are returned") {
        it("should return no problem status when all trains are on time") {
            val times = listOf("11:23", "11:32")
            val mockGetTrains = MockGetTrains()
            val problemDeterminer = createProblemDeterminer(
                    "departure-station",
                    "destination-station",
                    mockGetTrains.mockGetOnTimeTrains,
                    ELEVEN_AM_CLOCK
            )

            val result = problemDeterminer(times)

            assertThat(result).isEqualTo(NO_PROBLEM)
        }

        it("should return problem status when a requested train is cancelled") {
            val times = listOf("11:23", "11:32")
            val mockGetTrains = MockGetTrains()
            val problemDeterminer = createProblemDeterminer(
                    "departure-station",
                    "destination-station",
                    mockGetTrains.mockTrainsWithOneCancelled,
                    ELEVEN_AM_CLOCK
            )

            val result = problemDeterminer(times)

            assertThat(result).isEqualTo(PROBLEM)
        }
    }
})

class MockGetTrains {
    var wasCalledWith: Stations? = null

    val mockGetOnTimeTrains: GetTrains = { departureStation, destinationStation ->
        wasCalledWith = Stations(departureStation, destinationStation)
        listOf(
                Train(
                        scheduledTimeOfDeparture = "11:23",
                        estimatedTimeOfDeparture = "On time",
                        isCancelled = false,
                        isCancelledAtDestination = false
                ),
                Train(
                        scheduledTimeOfDeparture = "11:32",
                        estimatedTimeOfDeparture = "On time",
                        isCancelled = false,
                        isCancelledAtDestination = false
                )
        )
    }

    val mockTrainsWithOneCancelled: GetTrains = { departureStation, destinationStation ->
        wasCalledWith = Stations(departureStation, destinationStation)
        listOf(
                Train(
                        scheduledTimeOfDeparture = "11:23",
                        estimatedTimeOfDeparture = "On time",
                        isCancelled = false,
                        isCancelledAtDestination = false
                ),
                Train(
                        scheduledTimeOfDeparture = "11:32",
                        estimatedTimeOfDeparture = "Cancelled",
                        isCancelled = true,
                        isCancelledAtDestination = false
                )
        )
    }

    data class Stations(val departureStation: String, val destinationStation: String)
}