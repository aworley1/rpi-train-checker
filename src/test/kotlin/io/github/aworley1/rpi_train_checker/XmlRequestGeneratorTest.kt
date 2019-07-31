package io.github.aworley1.rpi_train_checker

import assertk.assertThat
import assertk.assertions.contains
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object XmlRequestGeneratorTest : Spek({
 describe("GetDepBoardWithDetailsRequestGenerator") {
     val result = getDepBoardWithDetailsRequestGenerator(departureStation = "departureStation", destinationStation = "destinationStation", accessToken = "accessToken")

     it("should include access token") {
         assertThat(result).contains("<typ:TokenValue>accessToken</typ:TokenValue>")
     }

     it("should include departure station") {
         assertThat(result).contains("<ldb:crs>departureStation</ldb:crs>")
     }

     it("should include destination station") {
         assertThat(result).contains("<ldb:filterCrs>destinationStation</ldb:filterCrs>")
     }
 }
})