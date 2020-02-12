package com.example.githubrepo.mapper

abstract class Mapper<in T, out R> {

    abstract fun map(input: T): R
}