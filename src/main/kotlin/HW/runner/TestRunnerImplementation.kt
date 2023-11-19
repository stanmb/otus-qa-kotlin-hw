package HW.runner

import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredFunctions

class TestRunnerImplementation
<T : Any> : TestRunner<T> {
    private lateinit var classMethods: Collection<KFunction<*>>
    private lateinit var steps: T
    override fun runTest(steps: T, test: () -> Unit) {
        println(">>> STARTing ${steps::class.simpleName}")
        classMethods = steps::class.declaredFunctions
        this.steps = steps
        runBeforeAfter(classMethods, MethodName.BEFORE)
        test.invoke()
        runBeforeAfter(classMethods, MethodName.AFTER)
    }

    private fun runBeforeAfter(functions: Collection<KFunction<*>>, methodNameContains: MethodName) {
        functions
            .filter {function ->
                function.name.startsWith(methodNameContains.name.lowercase())
            }
            .run {
                forEach {function ->
                    function.call(steps)
                }
            }
    }
}