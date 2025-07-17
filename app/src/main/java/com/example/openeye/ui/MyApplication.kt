package com.example.openeye.ui

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

 open class MyApplication :Application() {
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
    }
}