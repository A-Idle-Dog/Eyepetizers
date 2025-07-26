package com.example.lib.net

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
object NetWork {
    //全局标记
    @Volatile
    private var _hasShownNetworkError = false

    fun hasShownNetworkError(): Boolean = _hasShownNetworkError

    fun setShownNetworkError() {
        _hasShownNetworkError = true
    }

    fun resetNetworkErrorState() {
        _hasShownNetworkError = false
    }
}