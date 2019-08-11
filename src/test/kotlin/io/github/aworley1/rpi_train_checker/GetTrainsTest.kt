package io.github.aworley1.rpi_train_checker

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.thalesgroup.rtti._2007_10_10.ldb.commontypes.FilterType
import com.thalesgroup.rtti._2013_11_28.token.types.AccessToken
import com.thalesgroup.rtti._2017_10_01.ldb.GetBoardRequestParams
import com.thalesgroup.rtti._2017_10_01.ldb.LDBServiceSoap
import com.thalesgroup.rtti._2017_10_01.ldb.StationBoardWithDetailsResponseType
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object GetTrainsTest : Spek({
    describe("Get Trains") {
        val capturedAccessToken = slot<AccessToken>()
        val capturedParameters = slot<GetBoardRequestParams>()
        val accessToken = AccessToken().apply { tokenValue = "test-token-value" }
        val ldbServiceSoap = mockk<LDBServiceSoap>()

        every {
            ldbServiceSoap.getDepBoardWithDetails(
                    capture(capturedParameters),
                    capture(capturedAccessToken)
            )
        } answers {
            mockk<StationBoardWithDetailsResponseType>()
        }

        val getTrains = createGetTrains(ldbServiceSoap, accessToken)

        getTrains("HFN", "HHY")

        it("should call LdbService with correct access token") {
            assertThat(capturedAccessToken.captured.tokenValue).isEqualTo("test-token-value")
        }

        it("should call LdbService with correct departure station") {
            assertThat(capturedParameters.captured.crs).isEqualTo("HFN")
        }

        it("should call LdbService with correct destination station") {
            assertThat(capturedParameters.captured.filterCrs).isEqualTo("HHY")
            assertThat(capturedParameters.captured.filterType).isEqualTo(FilterType.TO)
        }
    }
})