package com.example.unittestdemo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TestCoroutineRule : TestRule {

    @ExperimentalCoroutinesApi
    val testDispatcher: TestCoroutineDispatcher =  TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    override fun apply(base: Statement, description: Description?): Statement = object : Statement() {
        override fun evaluate() {
            Dispatchers.setMain(testDispatcher)

            base.evaluate()

            Dispatchers.resetMain()
        }
    }

    //TODO: please refer to open issue: https://github.com/Kotlin/kotlinx.coroutines/issues/1204
    @ExperimentalCoroutinesApi
    fun runBlockingTest(block: suspend CoroutineScope.() -> Unit) {
        runBlocking {
            block()
        }
    }
}