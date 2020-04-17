# GradleIssue

A sample project to show some issues I have with gradle custom plugin creation

## Issues :

- Can't run a test that depends of the plugin :
 ```
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
 ```
 ```
  Line 2:             `com.nitro.issue`
                      ^ Unresolved reference: `com.nitro.issue`
 ```
- Can't reference a task in the source :
```
        buildFile.writeText(
            """
            import com.nitro.issue.TaskWithLongMappingTreatement
            tasks.register("helloWorld", TaskWithLongMappingTreatement::class.java) {
                doLast {
                    println("Hello world!")
                }
            }                
            """.trimMargin()
        )
```
```
  Line 1:             import com.nitro.issue.TaskWithLongMappingTreatement
                                 ^ Unresolved reference: nitro
```