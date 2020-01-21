import org.jetbrains.kotlin.cli.jvm.main
import org.jetbrains.kotlin.gradle.tasks.*

val ideaActive = System.getProperty("idea.active") == "true"

val kotlin_version: String by extra
val ktor_version: String by extra
val coroutines_version: String by extra
val serialization_version: String by extra

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("kotlinx-serialization")
}

android {
    compileSdkVersion(29)
    buildToolsVersion = "29.0.2"
    defaultConfig {
        minSdkVersion(29)
        targetSdkVersion(29)
    }
}

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$coroutines_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$serialization_version")

                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-serialization:$ktor_version")
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)

            dependencies {
                api("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
                api("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")

                api("io.ktor:ktor-client-serialization-jvm:$ktor_version")
                api("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serialization_version")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
                api("io.ktor:ktor-client-core-jvm:$ktor_version")
                api("com.android.support:support-compat:29.0.0")
            }
        }
    }
}