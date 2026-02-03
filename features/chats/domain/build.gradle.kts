plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {

    api(project(":core:essentials"))

    //Coroutines
    implementation(libs.coroutines.core)

    //Coroutines Test
    implementation(libs.coroutines.test)

    //Inject
    implementation(libs.javax.inject)

    testImplementation(libs.junit)

}
