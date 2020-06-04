package com.example.unittestdemo.testdouble


interface Repository {
    fun fetchData(): Int
}

class DummyRepository : Repository {

    override fun fetchData(): Int {
        throw NotImplementedError()
    }
}

class StubRepository : Repository {

    override fun fetchData(): Int {
        return 0
    }
}

class SpyRepository : Repository {

    var count: Int = 0

    override fun fetchData(): Int {
        count++
        return 0
    }
}