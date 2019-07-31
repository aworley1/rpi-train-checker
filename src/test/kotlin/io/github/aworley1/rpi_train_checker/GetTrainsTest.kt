package io.github.aworley1.rpi_train_checker

import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotEmpty
import assertk.assertions.size
import org.http4k.client.OkHttp
import org.http4k.core.HttpHandler
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.core.with
import org.http4k.filter.ClientFilters
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object GetDepartureBoardInfoTest : Spek({

    val stubDarwinServer = stubDarwin.asServer(Jetty(0))
    val stubDarwinPort by lazy { stubDarwinServer.port() }

    val client by lazy { ClientFilters.SetBaseUriFrom(Uri.of("http://localhost:$stubDarwinPort")).then(OkHttp()) }

    val result by lazy { getDepartureBoardInfo(client) }

    beforeGroup {
        stubDarwinServer.start()
    }

    describe("getDepartureBoardInfo") {
        it("should do something") {
            assertThat(result).hasSize(1)
        }
    }


    afterGroup {
        stubDarwinServer.stop()
    }

})

val stubDarwin: HttpHandler = { request ->
    Response(Status.OK).body(responseXml)
}

val responseXml = ""