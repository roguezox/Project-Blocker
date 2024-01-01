import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

group = "com.blocker"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()

}

dependencies {

    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation("com.lordcodes.turtle:turtle:0.8.0")
    implementation ("org.apache.commons:commons-exec:1.3")
    implementation("commons-net:commons-net:3.10.0")

    implementation("org.jetbrains.skiko:skiko-awt-runtime-windows-x64:0.7.70")
    api("dev.icerock.moko:mvvm-core:0.16.1") // only ViewModel, EventsDispatcher, Dispatchers.UI
    api("dev.icerock.moko:mvvm-compose:0.16.1")
    implementation ("org.xerial:sqlite-jdbc:3.34.0")
    implementation(compose.desktop.windows_x64)
    implementation("org.jetbrains.compose.ui:ui-tooling-preview:1.4.1")

    implementation("org.jetbrains.kotlinx:dataframe-jdbc:0.13.0-dev-2617")
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Blocker"
            packageVersion = "1.0.0"
        }
    }
}
