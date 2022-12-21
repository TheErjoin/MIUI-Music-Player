plugins {
    id (Plugins.AGP.application) version Versions.AGP apply false
    id (Plugins.AGP.library) version Versions.AGP apply false
    kotlin(Plugins.Kotlin.android) version Versions.kotlin apply false

    // Hilt
    id(Plugins.Hilt.plugin) version Versions.dagger apply false
    id("org.jetbrains.kotlin.jvm") version "1.7.20" apply false
}