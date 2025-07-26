package com.example.module_hot.viewmodel

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.module_hot.bean.HotBean
import com.example.module_hot.retrofit.HotNet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class HotViewModel:ViewModel() {
    private var scrollPosition = mutableMapOf<String,Parcelable?>()

    private val _hotDataCache = mutableMapOf<String, HotBean?>()
    private val _hotStateFlows = mutableMapOf<String, MutableStateFlow<HotBean?>>()

        //private val _networkStates = mutableMapOf<String, MutableStateFlow<Boolean?>>()


    /*fun getNetworkStateFlow(type: String): StateFlow<Boolean?> {
        return _networkStates.getOrPut(type) {
            MutableStateFlow(null)
        }
    }*/

    // 根据type获取对应的StateFlow（不存在则创建）
    fun getHotStateFlow(type: String): StateFlow<HotBean?> {
        return _hotStateFlows.getOrPut(type) {
            MutableStateFlow(null)
        }
    }

    //private var _HotStateFlow = MutableStateFlow<HotBean?>(null)
    //val hotStateFlow :StateFlow<HotBean?>
        //get()=_HotStateFlow

    fun savePosition(type:String,parcelable: Parcelable){
        scrollPosition[type]=parcelable
    }
    fun getPosition(type: String):Parcelable?{
        return scrollPosition[type]
    }
    fun getHot(strategy:String){
        val cachedData = _hotDataCache[strategy]
        if (cachedData != null) {
            _hotStateFlows[strategy]?.value = cachedData
            //_networkStates[strategy]?.value = true
            return
        }

            viewModelScope.launch(Dispatchers.IO) {
                try {
                val response =HotNet.hot.getHot(strategy)
                    _hotDataCache[strategy] = response
                    _hotStateFlows[strategy]?.emit(response)
                    //_networkStates[strategy]?.emit(true) // 网络请求成功


            }catch (e:Exception){
                    e.printStackTrace()
                   /* if (e is IOException) { // 网络相关错误
                        //_networkStates[strategy]?.emit(false)
                    } else { // 其他错误（如解析失败）
                        //_networkStates[strategy]?.emit(null)
                    }*/
        }
        }
    }
}