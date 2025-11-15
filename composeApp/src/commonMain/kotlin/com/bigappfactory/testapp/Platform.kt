package com.bigappfactory.testapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform