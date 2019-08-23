package io.github.aworley1.rpi_train_checker
//
//import com.pi4j.io.gpio.GpioController
//import com.pi4j.io.gpio.GpioPinDigitalOutput
//import com.pi4j.io.gpio.PinState
//import com.pi4j.io.gpio.RaspiPin
//import io.github.aworley1.rpi_train_checker.ProblemStatus.NO_PROBLEM
//import io.github.aworley1.rpi_train_checker.ProblemStatus.NO_TRAINS_REQUESTED
//import io.github.aworley1.rpi_train_checker.ProblemStatus.PROBLEM
//import io.mockk.every
//import io.mockk.mockk
//import io.mockk.verify
//import org.spekframework.spek2.Spek
//import org.spekframework.spek2.style.specification.describe
//
//object StatusReporterTest : Spek({
//    describe("Given there is NO_PROBLEM") {
//        val mockGpioController = mockk<GpioController>()
//        val mockGreenPin = mockk<GpioPinDigitalOutput>(relaxed = true)
//        val mockRedPin = mockk<GpioPinDigitalOutput>(relaxed = true)
//
//        every {
//            mockGpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02, any<PinState>())
//        } returns mockGreenPin
//
//        every {
//            mockGpioController.provisionDigitalOutputPin(RaspiPin.GPIO_04, any<PinState>())
//        } returns mockRedPin
//
//        val statusReporter = createStatusReporter(mockGpioController)
//
//        statusReporter(NO_PROBLEM)
//
//        it("should provision GREEN led in low state") {
//            verify { mockGpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02, PinState.LOW) }
//        }
//
//        it("should provision RED led in low state") {
//            verify { mockGpioController.provisionDigitalOutputPin(RaspiPin.GPIO_04, PinState.LOW) }
//        }
//
//        it("should set GREEN led to high state") {
//            verify(exactly = 1) { mockGreenPin.high() }
//        }
//
//        it("should not set RED led to high state") {
//            verify(exactly = 0) { mockRedPin.high() }
//        }
//    }
//
//    describe("Given there is a PROBLEM") {
//        val mockGpioController = mockk<GpioController>()
//        val mockGreenPin = mockk<GpioPinDigitalOutput>(relaxed = true)
//        val mockRedPin = mockk<GpioPinDigitalOutput>(relaxed = true)
//
//        every {
//            mockGpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02, any<PinState>())
//        } returns mockGreenPin
//
//        every {
//            mockGpioController.provisionDigitalOutputPin(RaspiPin.GPIO_04, any<PinState>())
//        } returns mockRedPin
//
//        val statusReporter = createStatusReporter(mockGpioController)
//
//        statusReporter(PROBLEM)
//
//        it("should provision GREEN led in low state") {
//            verify { mockGpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02, PinState.LOW) }
//        }
//
//        it("should provision RED led in low state") {
//            verify { mockGpioController.provisionDigitalOutputPin(RaspiPin.GPIO_04, PinState.LOW) }
//        }
//
//        it("should set RED led to high state") {
//            verify(exactly = 1) { mockRedPin.high() }
//        }
//
//        it("should not set GREEN led to high state") {
//            verify(exactly = 0) { mockGreenPin.high() }
//        }
//    }
//
//        describe("Given no trains were requested") {
//        val mockGpioController = mockk<GpioController>()
//        val mockGreenPin = mockk<GpioPinDigitalOutput>(relaxed = true)
//        val mockRedPin = mockk<GpioPinDigitalOutput>(relaxed = true)
//
//        every {
//            mockGpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02, any<PinState>())
//        } returns mockGreenPin
//
//        every {
//            mockGpioController.provisionDigitalOutputPin(RaspiPin.GPIO_04, any<PinState>())
//        } returns mockRedPin
//
//        val statusReporter = createStatusReporter(mockGpioController)
//
//        statusReporter(NO_TRAINS_REQUESTED)
//
//        it("should provision GREEN led in low state") {
//            verify { mockGpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02, PinState.LOW) }
//        }
//
//        it("should provision RED led in low state") {
//            verify { mockGpioController.provisionDigitalOutputPin(RaspiPin.GPIO_04, PinState.LOW) }
//        }
//
//        it("should not set RED led to high state") {
//            verify(exactly = 0) { mockRedPin.high() }
//        }
//
//        it("should not set GREEN led to high state") {
//            verify(exactly = 0) { mockGreenPin.high() }
//        }
//    }
//})