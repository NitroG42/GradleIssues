import groovy.util.GroovyTestCase.assertEquals
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import java.io.IOException


class TestIssue {
    @Rule
    @JvmField
    val testProjectDir: TemporaryFolder = TemporaryFolder()
    private lateinit var settingsFile: File
    private lateinit var buildFile: File


    @Before
    @Throws(IOException::class)
    fun setup() {
        settingsFile = testProjectDir.newFile("settings.gradle.kts")
        buildFile = testProjectDir.newFile("build.gradle.kts")
    }

    @Test
    fun testLongRun() {
        settingsFile.writeText(
            """
            rootProject.name = "hello-world"
        """.trimIndent()
        )
        buildFile.writeText(
            """
        plugins {
            `com.nitro.issue`
        }
            tasks.register<TaskWithLongMappingTreatement>("helloWorld") {
                doLast {
                    println("Hello world!")
                }
            }                
            """.trimMargin()
        )
        val result = GradleRunner.create()
            .withProjectDir(testProjectDir.root)
            .withArguments("helloWorld", "--info")
            .withPluginClasspath()
            .build()

        assertTrue(result.output.contains("Hello world!"))
        assertEquals(TaskOutcome.SUCCESS, result.task(":helloWorld")?.outcome);
    }
}