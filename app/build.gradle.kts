plugins {
    id("kotlin-kapt")
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.openeye"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.openeye"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        /*// 关键修复：添加 javaCompileOptions 配置 ARouter
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["AROUTER_MODULE_NAME"] = project.name
            }
        }*/
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

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildFeatures {
        dataBinding = false
        viewBinding = true
    }
}

// 删除这个冲突的 kapt 块

kapt {
    arguments {
        arg("AROUTER_MODULE_NAME", project.name)
    }
}


dependencies {
    // 项目模块
    implementation(project(":module_my"))
    implementation(project(":data"))
    implementation(project(":LIB"))
    implementation(project(":module_found"))
    implementation(project(":module_hot"))
    implementation(project(":module_home"))
    implementation(project(":module_square"))

    // ARouter 配置 - 使用最新版本
    implementation("com.alibaba:arouter-api:1.5.2")
    kapt("com.alibaba:arouter-compiler:1.5.2")

    // AndroidX 依赖
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Material Design
    implementation(libs.material)

    // 测试依赖
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    debugImplementation ("com.squareup.leakcanary:leakcanary-android:2.8.1")
}

// 添加 KAPT 内存配置（解决常见问题）
kapt {
    javacOptions {
        option("-Xmx2048m") // 增加内存限制
    }
}