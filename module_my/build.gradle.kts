plugins {
    id("com.android.library")
    id("kotlin-kapt")
    //alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.module_my"
    compileSdk = 34

    defaultConfig {
        //applicationId = "com.example.module_my"
        minSdk = 24
        targetSdk = 34
        //versionCode = 1
        //versionName = "1.0"

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
    buildFeatures {
        dataBinding = false
        viewBinding = true
    }
}
kapt {
    arguments {
        arg("AROUTER_MODULE_NAME", project.name)
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":module_video"))
    //room
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    //glide
    implementation ("com.github.bumptech.glide:glide:4.13.2")
    kapt ("com.github.bumptech.glide:compiler:4.13.2")

    //router
    kapt  ("com.alibaba:arouter-compiler:1.5.2")
    implementation ("com.alibaba:arouter-api:1.5.2")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}