package io.github.aworley1.rpi_train_checker

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object ProblemDeterminerTest : Spek({
    describe("Given there are trains within the next hour") {
        val times = listOf("11:23")
        val mockGetTrains = MockGetTrains()
        val problemDeterminer = createProblemDeterminer(
                "departure-station",
                "destination-station",
                mockGetTrains.mockGetTrains
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
})

class MockGetTrains {
    var wasCalledWith: Stations? = null

    val mockGetTrains: GetTrains = { departureStation, destinationStation ->
        wasCalledWith = Stations(departureStation, destinationStation)
        emptyList()
    }

    data class Stations(val departureStation: String, val destinationStation: String)
}