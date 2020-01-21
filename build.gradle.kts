
buildscript {
    val kotlin_version: String by extra

    repositories {
        google()
        jcenter()

        maven("https://kotlin.bintray.com/kotlinx")
        maven("https://dl.bintray.com/jetbrains/kotlin-native-dependencies")
        maven("https://dl.bintray.com/kotlin/kotlin-dev")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.5.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlin_version")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenLocal()
        mavenCentral()

        maven("https://dl.bintray.com/kotlin/kotlinx")
        maven("https://dl.bintray.com/kotlin/ktor")
        maven("https://dl.bintray.com/sargunster/maven")
        maven("https://dl.bintray.com/kotlin/squash")
        maven("https://dl.bintray.com/kotlin/kotlin-dev")
    }
}

tasks.register<org.gradle.api.tasks.Delete>("clean") {
    delete(rootProject.buildDir)
}
