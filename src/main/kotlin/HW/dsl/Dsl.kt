package HW.dsl

import HW.runner.TestRunnerImplementation

fun <T: Any> testAround(init: TestRunnerImplementation<T>.() -> Unit): TestRunnerImplementation<T> = TestRunnerImplementation<T>().also { it.init() }