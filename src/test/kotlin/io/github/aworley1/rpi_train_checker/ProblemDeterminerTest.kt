package io.github.aworley1.rpi_train_checker

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

val ELEVEN_AM_CLOCK = Clock.fixed(
        Instant.parse("2015-01-01T11:00:00Z"),
        ZoneId.of("Europe/London")
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

        problemDeterminer(times)

        it("should call getTrains with correct departure station") {
            assertThat(mockGetTrains.wasCalledWith?.departureStation)
                    .isEqualTo("departure-station")
        }

        it("should call getTrains with correct destination station") {
            assertThat(mockGetTrains.wasCalledWith?.destinationStation)
                    .isEqualTo("destination-station")
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

        problemDeterminer(times)

        it("should NOT call GetTrains") {
            assertThat(mockGetTrains.wasCalledWith)
                    .isNull()
        }
    }
})

class MockGetTrains {
    var wasCalledWith: Stations? = null

    val mockGetTrains: GetTrains = { departureStation, destinationStation ->
        wasCalledWith = Stations(departureStation, destinationStation)
        emptyList()
    }

    data class Stations(val departureStation: String, val destinationStation: String)
}