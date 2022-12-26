import Plugins.KSP.ksp

plugins {
    id(Plugins.AGP.library)
    kotlin(Plugins.Kotlin.android)
    id(Plugins.KSP.ksp) version Versions.KSP
}

android {
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":domain"))

    // Retrofit 2
    implementation(Libraries.Retrofit.retrofit)
    implementation(Libraries.Retrofit.converterGson)

    // OkHttp
    implementation(Libraries.OkHttp.bom)
    implementation(Libraries.OkHttp.okHttp) 
    implementation(Libraries.OkHttp.loggingInterceptor)

    // Room
    api(Libraries.Room.runtime)
    ksp(Libraries.Room.compiler)

    // Paging 3
    api(Libraries.Paging.runtime)
}