package com.dfg.viewmodeldemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(val countReserved: Int) : ViewModelProvider.Factory {
    //ViewModelProvider.Factory接口要求必须实现create()方法

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // 创建了MainViewModel的实例，并将countReserved参数传了进去。
        // 创建MainViewModel的实例了？因为create()的执行时机和 Activity的生命周期无关，所以不会产生之前提到的问题。
        return MainViewModel(countReserved) as T
    }
}