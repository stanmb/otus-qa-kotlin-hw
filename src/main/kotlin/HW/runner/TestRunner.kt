package HW.runner

interface TestRunner<T> { fun runTest(steps: T, test: () -> Unit) }