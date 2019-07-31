package io.github.aworley1.rpi_train_checker

import org.http4k.core.HttpHandler

fun getDepartureBoardInfo(client: HttpHandler): DepartureBoardInfo {

    return DepartureBoardInfo()
}

class DepartureBoardInfo()