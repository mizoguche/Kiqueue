val kotlin_version: String by extra
val ktor_version: String by extra
val lifecycle_version: String by extra
val room_version: String by extra
val koin_version: String by extra

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(29)
    buildToolsVersion = ("29.0.2")
    defaultConfig {
        applicationId = ("dev.mizoguche.kiqueue")
        minSdkVersion(29)
        targetSdkVersion(29)
        versionCode = 1
        versionName = ("0.1.0")
        testInstrumentationRunner = ("androidx.test.runner.AndroidJUnitRunner")
    }
    buildTypes {
        val release by getting {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }
    dataBinding {
        isEnabled = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":common"))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation("androidx.appcompat:appcompat:1.0.2")
    implementation("androidx.core:core-ktx:1.0.2")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("com.google.android.material:material:1.0.0")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.1.1")

    implementation("io.ktor:ktor-client-android:$ktor_version")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    kapt("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:$lifecycle_version")
//    testImplementation("androidx.arch.core:core-testing:$lifecycle_version")

    // Room
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    implementation("androidx.room:room-rxjava2:$room_version")
    implementation("androidx.room:room-guava:$room_version")
    testImplementation("androidx.room:room-testing:$room_version")

    // Koin
    implementation("org.koin:koin-core:$koin_version")
    implementation("org.koin:koin-core-ext:$koin_version")
    testImplementation("org.koin:koin-test:$koin_version")
    implementation("org.koin:koin-android:$koin_version")
    implementation("org.koin:koin-android-scope:$koin_version")
    implementation("org.koin:koin-android-viewmodel:$koin_version")
    implementation("org.koin:koin-android-ext:$koin_version")
    implementation("org.koin:koin-androidx-scope:$koin_version")
    implementation("org.koin:koin-androidx-viewmodel:$koin_version")
//    implementation("org.koin:koin-androidx-fragment:$koin_version")
    implementation("org.koin:koin-androidx-ext:$koin_version")
}
