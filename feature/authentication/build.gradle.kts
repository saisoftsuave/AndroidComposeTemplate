plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
    
}

android {
    namespace = "com.example.authentication"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //modules
    implementation(project(":core:ui"))
    implementation(project(":core:network"))
    implementation(project(":core:datastore"))

    //compose
    implementation(platform(libs.androidx.compose.bom.v20250200))
    androidTestImplementation(platform(libs.androidx.compose.bom.v20250200))
    implementation(libs.ui)

    // Android Studio Preview support
    implementation(libs.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)

    // UI Tests
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.test.manifest)

    //material3
    implementation(libs.material3)
    implementation(libs.androidx.material.icons.core)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.adaptive)

    //compose navigation
    implementation(libs.androidx.navigation.compose)

    //dagger hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0")

    //navigation
    implementation(libs.androidx.compose.material3.adaptive.navigation)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.navigation.compose)

    //data store
    implementation("androidx.datastore:datastore-preferences:1.1.4")

    //testing
    testImplementation(libs.junit.v44)
    testImplementation(libs.test.coroutines)
    testImplementation(libs.mockito)
testImplementation(libs.turbine)


    // Define versions
    val androidXTestVersion = "1.5.0"
    val espressoVersion = "3.5.1"
    val uiAutomatorVersion = "2.3.0"

    // AndroidX Test
    androidTestImplementation("androidx.test:runner:$androidXTestVersion")
    androidTestImplementation("androidx.test:rules:$androidXTestVersion")

    // UI testing with Espresso
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoVersion")

    // UI testing with UI Automator
    androidTestImplementation("androidx.test.uiautomator:uiautomator:$uiAutomatorVersion")

//    // UI testing with Jetpack Compose
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
//
//    // Needed for Compose UI testing
//    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")
}