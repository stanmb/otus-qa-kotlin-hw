import HW.dsl.testAround
import HW.tests.*

fun main() {
    testAround {
        runTest(BeforeTwiceAndAfterTwiceTestClass())
        {
            println("§ invoke test BeforeTwiceAndAfterTwiceTestClass RUNNING")
        }
    }

    testAround {
        runTest(BeforeAndAfterTestClass()) { println("§ invoke test BeforeAndAfterTestClass RUNNING") }
    }

    testAround {
        runTest(AfterOnlyTestClass()) { println("§ invoke test AfterOnlyTestClass RUNNING") }
    }

    testAround {
        runTest(BeforeOnlyTestClass()) { println("§ invoke test BeforeOnlyTestClass RUNNING") }
    }

    testAround {
        runTest(EmptyTestClass()) { println("§ invoke test EmptyTestClass RUNNING") }
    }

    println("### finish main ###")
}