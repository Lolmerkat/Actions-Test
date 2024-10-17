import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
        kotlin("jvm")
        id("org.jetbrains.compose")
        id("org.jetbrains.kotlin.plugin.compose")
        id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "cc.lolmerkat"
version = "1.0.0"

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
        implementation(compose.desktop.currentOs)
}

compose.desktop {
        application {
                mainClass = "MainKt"

                nativeDistributions {
                        targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
                        packageName = "GithubActions"
                        packageVersion = "1.0.0"
                }
        }
}

tasks {
        shadowJar {
                archiveBaseName = "github-actions"
                archiveVersion = version.toString()
                archiveClassifier = null

                manifest {
                        attributes(
                                "Manifest-Version" to "1.0",
                                "Main-Class" to "MainKt"
                        )
                }
        }
}
