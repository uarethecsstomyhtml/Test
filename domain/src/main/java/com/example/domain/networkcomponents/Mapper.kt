package com.example.domain.networkcomponents


interface Mapper<I, O> {
    fun map(input: I): O
}