plugins {
    id("com.android.library")
    id("kotlin-kapt")
    //alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.module_home"
    compileSdk = 35

    defaultConfig {
        //applicationId = "com.example.module_home"
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
    
    //material
    implementation("com.google.android.material:material:1.10.0")

    //glide
    implementation ("com.github.bumptech.glide:glide:4.13.2")
    kapt ("com.github.bumptech.glide:compiler:4.13.2")

    implementation(project(":module_video"))
    implementation(project(":lib_network"))
    implementation(project(":LIB"))
    implementation(project(":lib_net"))
    implementation(project(":module_square"))
    implementation(project(":module_found"))
    implementation(project(":module_hot"))
    implementation(project(":module_found"))

    //router
    kapt  ("com.alibaba:arouter-compiler:1.5.2")
    implementation ("com.alibaba:arouter-api:1.5.2")
    //retrofit
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")

    //paging
    implementation ("androidx.paging:paging-runtime:3.0.0-beta01")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}