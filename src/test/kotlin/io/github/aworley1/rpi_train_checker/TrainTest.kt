package io.github.aworley1.rpi_train_checker

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object TrainTest : Spek({
    describe("Train") {
        it("should determine when there are no issues") {
            val train = Train(
                    scheduledTimeOfDeparture = "15:16",
                    estimatedTimeOfDeparture = "On time",
                    isCancelled = false,
                    isCancelledAtDestination = false
            )

            assertThat(train.hasIssues()).isFalse()
        }

        it("should have issues when cancelled") {
            val train = Train(
                    scheduledTimeOfDeparture = "15:16",
                    estimatedTimeOfDeparture = "On time",
                    isCancelled = true,
                    isCancelledAtDestination = false
            )

            assertThat(train.hasIssues()).isTrue()
        }

        it("should have issues when cancelled at destination") {
            val train = Train(
                    scheduledTimeOfDeparture = "15:16",
                    estimatedTimeOfDeparture = "On time",
                    isCancelled = false,
                    isCancelledAtDestination = true
            )

            assertThat(train.hasIssues()).isTrue()
        }

        it("should have issues when train is not on time") {
            val train = Train(
                    scheduledTimeOfDeparture = "15:16",
                    estimatedTimeOfDeparture = "Blobby",
                    isCancelled = false,
                    isCancelledAtDestination = false
            )

            assertThat(train.hasIssues()).isTrue()
        }
    }
})