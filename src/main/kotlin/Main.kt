import java.lang.reflect.Method

fun main() {
    val testRun = TestRunnerImplementation<Steps>()
    testRun.runTest(Steps()) {
        println("I am the test")
    }
}

interface TestRunner<T> { fun runTest(steps: T, test: () -> Unit) }

class TestRunnerImplementation<T>: TestRunner<T> {
    override fun runTest(steps: T, test: () -> Unit) {
        val listBefore = mutableListOf<Method>()
        val listAfter = mutableListOf<Method>()
        steps!!::class.java.declaredMethods.forEach {
            if (it.name.contains("before")) {
                listBefore.add(it)
            }
            else listAfter.add(it)
        }
        // run before methods
        for (method in listBefore) {
            println("Run before method")
            method.invoke(steps)
        }

        // run the test
        println("Run the test")
        test()

        // run after methods
        for (method in listAfter) {
            println("Run after method")
            method.invoke(steps)
        }
    }
}

class Steps {
    fun beforeAll() {
        println("I am before all")
    }

    fun beforeOne() {
        println("I am before one")
    }

    fun afterAll() {
        println("I am after all")
    }

    fun afterOne() {
        println("I am after one")
    }
}