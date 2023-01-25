plugins {
    id(Plugins.AGP.application)
    kotlin(Plugins.Kotlin.android)
    kotlin(Plugins.Kotlin.kapt)

    // Hilt
    id(Plugins.Hilt.plugin)

    // Parcelize
    id(Plugins.Kotlin.parcelize)
}

android {
    namespace = "kg.erjan.musicplayer"
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        applicationId = "kg.erjan.musicplayer"
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
        debug {
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = Options.compileOptions
        targetCompatibility = Options.compileOptions
    }
    kotlinOptions {
        jvmTarget = Options.kotlinOptions
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
}

dependencies {

    //Module
    implementation(project(":data"))
    implementation(project(":domain"))

    // Kotlin
    implementation(Libraries.Coroutines.android)

    // Core
    implementation(Libraries.Core.core)
    implementation(Libraries.Core.splashScreen)

    // Lifecycle
    implementation(Libraries.Lifecycle.viewModel)
    implementation(Libraries.Lifecycle.runtime)

    // Activity
    implementation(Libraries.Activity.activity)

    // Hilt
    implementation(Libraries.Hilt.android)
    kapt(Libraries.Hilt.compiler)

    //Compose
    implementation(Libraries.Compose.composeUi)
    implementation(Libraries.Compose.composeUiToolingPreview)
    implementation(Libraries.Compose.composeMaterial)
    implementation(Libraries.Compose.navigationCompose)
    implementation(Libraries.Compose.accompanistPager)
    implementation(Libraries.Compose.accompanistPagerIndicators)
    implementation(Libraries.Compose.hiltNavigation)
    implementation(Libraries.Compose.accompanistPermission)
    implementation(Libraries.Compose.constraintCompose)
    implementation(Libraries.Compose.animatedNavHost)

    // Retrofit
    implementation(Libraries.Retrofit.retrofit)
    implementation(Libraries.Retrofit.converterGson)

    //Coil
    implementation(Libraries.Compose.coilCompose)

    // OkHttp
    implementation(Libraries.OkHttp.bom)
    implementation(Libraries.OkHttp.okHttp)
    implementation(Libraries.OkHttp.loggingInterceptor)
//    implementation(Libraries.OkHttp.prettyLoggingInterceptor) {
//        exclude(group = "org.json", module = "json")
//    }

    // Paging
    api(Libraries.Paging.runtime)
    implementation(Libraries.Paging.common)
    implementation(Libraries.Paging.pagingCompose)
}