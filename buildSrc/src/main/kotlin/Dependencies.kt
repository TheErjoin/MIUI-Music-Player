object Versions {
    const val AGP = "8.1.2"
    const val kotlin = "1.9.22"
    const val coroutines = "1.6.4"
    const val core = "1.9.0"
    const val splashScreen = "1.0.0"
    const val activity = "1.6.0"
    const val lifecycle = "2.5.1"
    const val dagger = "2.50"
    const val retrofit = "2.9.0"
    const val KSP = "1.9.22-1.0.16"
    const val constraintCompose = "1.0.1"
    const val composeUiVersion = "1.5.4 "
    const val composeCompiler = "1.5.8"
    const val composeMaterial = "1.5.4"
    const val coilCompose = "2.5.0"
    const val accompanistPermission = "0.33.2-alpha"
    const val navigationCompose = "2.7.6"
    const val hiltNavigation = "1.1.0"
    const val preference = "1.2.0"
}

object Libraries {

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${
            Versions.coroutines
        }"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${
            Versions.coroutines
        }"
    }

    object AndroidX {
        const val preference = "androidx.preference:preference:${Versions.preference}"
    }

    object Compose {
        const val composeUi = "androidx.compose.ui:ui:${
            Versions.composeUiVersion
        }"
        const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${
            Versions.composeUiVersion
        }"
        const val composeMaterial = "androidx.compose.material:material:${
            Versions.composeMaterial
        }"
        const val constraintCompose =
            "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintCompose}"
        const val coilCompose = "io.coil-kt:coil-compose:${Versions.coilCompose}"
        const val hiltNavigation =
            "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigation}"
        const val navigationCompose =
            "androidx.navigation:navigation-compose:${Versions.navigationCompose}"
        const val accompanistPermission =
            "com.google.accompanist:accompanist-permissions:${Versions.accompanistPermission}"
    }

    object Core {
        const val core = "androidx.core:core-ktx:${Versions.core}"
        const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreen}"
    }

    object Activity {
        const val activity = "androidx.activity:activity-compose:${Versions.activity}"
    }

    object Lifecycle {
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    }

    object Javax {
        const val inject = "javax.inject:javax.inject:1"
    }

    object Hilt {
        const val android = "com.google.dagger:hilt-android:${Versions.dagger}"
        const val compiler = "com.google.dagger:hilt-compiler:${Versions.dagger}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }
}

object Plugins {

    object AGP {
        const val application = "com.android.application"
        const val library = "com.android.library"
    }

    object KSP {

        const val ksp = "com.google.devtools.ksp"
    }

    object Kotlin {
        const val android = "android"
        const val jvm = "jvm"
        const val kapt = "kapt"
        const val parcelize = "kotlin-parcelize"
    }

    object Hilt {
        const val plugin = "com.google.dagger.hilt.android"
    }
}