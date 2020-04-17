package com.nitro.issue

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class TaskWithLongMappingTreatement : DefaultTask() {
    @get:Input
    abstract val input: Property<List<String>>

    //    .files(it.asFile.listFiles()).filter { it.extension == "txt" }
    val computedProperty: Provider<List<ParsedData>> = input.map {
        logger.info("starting long treatment")
        it.map {
            Thread.sleep(100L)
            ParsedData(it)
        }
    }

    @TaskAction
    fun convertTwineFiles() {
        logger.info("inputs :$inputs")
        val datas = computedProperty.get()
        val datas2 = computedProperty.get()
    }
}

data class ParsedData(val data: String)