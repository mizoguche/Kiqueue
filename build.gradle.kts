
buildscript {
    val kotlin_version: String by extra
    val ktor_version: String by extra

    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.5.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        
    }
}

tasks.register<org.gradle.api.tasks.Delete>("clean") {
    delete(rootProject.buildDir)
}
