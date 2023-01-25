object Versions {
    const val AGP = "7.3.0"
    const val kotlin = "1.7.20"
    const val coroutines = "1.6.4"
    const val core = "1.9.0"
    const val splashScreen = "1.0.0"
    const val activity = "1.6.0"
    const val lifecycle = "2.5.1"
    const val dagger = "2.44"
    const val retrofit = "2.9.0"
    const val KSP = "1.7.21-1.0.8"
    const val room = "2.4.3"
    const val okHttp = "5.0.0-alpha.10"
    const val prettyLoggingInterceptor = "3.1.0"
    const val paging = "3.1.1"
    const val constraintCompose = "1.0.1"
    const val composeUiVersion = "1.2.1"
    const val composeCompiler = "1.3.2"
    const val composeFoundation = "1.2.1"
    const val pagingCompose = "1.0.0-alpha16"
    const val composeMaterial = "1.2.1"
    const val composeRuntime = "1.2.1"
    const val coilCompose = "2.2.2"
    const val accompanistPager = "0.28.0"
    const val accompanistPermission = "0.28.0"
    const val navigationCompose = "2.5.3"
    const val hiltNavigation = "1.0.0"
    const val preference = "1.2.0"
    const val animationNavHost = "0.27.0"
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
        const val animatedNavHost =
            "com.google.accompanist:accompanist-navigation-animation:${Versions.animationNavHost}"
        const val accompanistPager =
            "com.google.accompanist:accompanist-pager:${Versions.accompanistPager}"
        const val accompanistPermission =
            "com.google.accompanist:accompanist-permissions:${Versions.accompanistPermission}"
        const val accompanistPagerIndicators =
            "com.google.accompanist:accompanist-pager-indicators:${Versions.accompanistPager}"
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
    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object Hilt {
        const val android = "com.google.dagger:hilt-android:${Versions.dagger}"
        const val compiler = "com.google.dagger:hilt-compiler:${Versions.dagger}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }

    object OkHttp {
        const val bom = "com.squareup.okhttp3:okhttp-bom:${Versions.okHttp}"
        const val okHttp = "com.squareup.okhttp3:okhttp"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor"
        const val prettyLoggingInterceptor = "com.github.ihsanbal:LoggingInterceptor:${
            Versions.prettyLoggingInterceptor
        }"
    }

    object Paging {
        const val runtime = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
        const val common = "androidx.paging:paging-common:${Versions.paging}"
        const val pagingCompose = "androidx.paging:paging-compose:${Versions.pagingCompose}"
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