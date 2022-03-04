package com.nackun.meetchecker

internal fun Any.log() {
    println("$this")
}

internal fun Any.log(name: String) {
    println("$name : $this")
}
