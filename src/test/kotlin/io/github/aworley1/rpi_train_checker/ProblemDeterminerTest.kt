package io.github.aworley1.rpi_train_checker

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import io.github.aworley1.rpi_train_checker.ProblemStatus.NO_PROBLEM
import io.github.aworley1.rpi_train_checker.ProblemStatus.PROBLEM
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.time.Clock
import java.time.Instant

val ELEVEN_AM_CLOCK = Clock.fixed(
        Instant.parse("2015-01-01T11:00:00Z"),
        TIMEZONE
)

object ProblemDeterminerTest : Spek({
    describe("Given there are trains requested within the next hour") {
        val times = listOf("11:23")
        val mockGetTrains = MockGetTrains()
        val problemDeterminer = createProblemDeterminer(
                "departure-station",
                "destination-station",
                mockGetTrains.mockGetTrains,
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
                mockGetTrains.mockGetTrains,
                ELEVEN_AM_CLOCK
        )

        it("should determine there is a problem when a train is missing") {
            val times = listOf("11:23", "11:30")
            assertThat(problemDeterminer(times)).isEqualTo(PROBLEM)
        }
    }

    describe("Given there are NO trains requested within the next hour") {
        val times = listOf("15:00")
        val mockGetTrains = MockGetTrains()
        val problemDeterminer = createProblemDeterminer(
                "departure-station",
                "destination-station",
                mockGetTrains.mockGetTrains,
                ELEVEN_AM_CLOCK
        )

        val result = problemDeterminer(times)

        it("should NOT call GetTrains") {
            assertThat(mockGetTrains.wasCalledWith)
                    .isNull()
        }

        it("should return no problem") {
            assertThat(result).isEqualTo(NO_PROBLEM)
        }
    }
})

class MockGetTrains {
    var wasCalledWith: Stations? = null

    val mockGetTrains: GetTrains = { departureStation, destinationStation ->
        wasCalledWith = Stations(departureStation, destinationStation)
        listOf(
                Train(
                        scheduledTimeOfDeparture = "11:23",
                        estimatedTimeOfDeparture = "On time",
                        isCancelled = false,
                        isCancelledAtDestination = false
                )
        )
    }

    data class Stations(val departureStation: String, val destinationStation: String)
}