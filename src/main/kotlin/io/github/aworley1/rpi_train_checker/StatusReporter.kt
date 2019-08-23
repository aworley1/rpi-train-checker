package io.github.aworley1.rpi_train_checker

import com.pi4j.io.gpio.GpioController
import com.pi4j.io.gpio.PinState
import com.pi4j.io.gpio.RaspiPin

val GREEN_LED_PIN = RaspiPin.GPIO_02
val RED_LED_PIN = RaspiPin.GPIO_04

typealias StatusReporter = (ProblemStatus) -> Unit

fun createStatusReporter(gpioController: GpioController): StatusReporter {
    return { problemStatus ->
        val greenLed = gpioController.provisionDigitalOutputPin(GREEN_LED_PIN, PinState.LOW)
        val redLed = gpioController.provisionDigitalOutputPin(RED_LED_PIN, PinState.LOW)

        redLed.high()
        greenLed.high()
    }
}