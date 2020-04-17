plugins {
    kotlin("jvm") version "1.3.72"
    `java-gradle-plugin`
}

group = "org.example"
version = "1.0-SNAPSHOT"



gradlePlugin {
    plugins {
        create("issue") {
            id = "com.nitro.issue"
            implementationClass = "com.nitro.issue.IssuePlugin"
        }
    }
}



repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("junit:junit:4.13")
    testImplementation(gradleTestKit())
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}