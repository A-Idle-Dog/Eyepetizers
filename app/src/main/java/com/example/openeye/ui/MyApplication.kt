package com.example.openeye.ui

import android.app.Application
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.example.data.AppDatabase

open class MyApplication :Application() {
    var hasShownNetworkError = false
    val database: AppDatabase by lazy {
        AppDatabase.getInstance(this)
    }
    companion object {
        lateinit var mContext: MyApplication
    }
    private var isDebugARouter = true
    override fun onCreate() {
        super.onCreate()
        mContext=this
        if (isDebugARouter) {
            //下面两行必须写在init之前，否则这些配置在init中将无效
            ARouter.openLog()
            //开启调试模式（如果在InstantRun模式下运行，必须开启调试模式！
            // 线上版本需要关闭，否则有安全风险）
            ARouter.openDebug()
        }
        //官方推荐放到Application中初始化
        ARouter.init(mContext)
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e("CrashHandler", "Uncaught exception: ${throwable.message}", throwable)
            // 可以在这里记录崩溃日志或执行其他操作
        }
    }
}