plugins {
    alias(libs.plugins.android.application) // Bu kalacak
    alias(libs.plugins.kotlin.android)      // Bu kalacak
    alias(libs.plugins.compose.compiler)


    id("com.google.gms.google-services") // Google Services için alias'ın yoksa bu kalabilir
    // Eğer varsa, bunu da alias ile değiştir:
    // alias(libs.plugins.google.gms.services) gibi
}

android {
    namespace = "com.example.yumly"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.yumly"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

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
        kotlinCompilerExtensionVersion = "1.5.15" // İdealde bu da libs.versions.toml'dan gelmeli
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:34.3.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Compose BOM
    implementation(platform("androidx.compose:compose-bom:2024.10.01"))

    // Temel Compose kütüphaneleri
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Compose Activity entegrasyonu
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("com.google.firebase:firebase-firestore")

}

