package com.dfg.viewmodeldemo

import androidx.lifecycle.ViewModel

/**
 * countReserved记录之前的数据
 */
class MainViewModel(countReserved:Int) : ViewModel() {
    //与界面相关的数据都应该放在ViewModel中，实现 一个计数器的功能，在ViewModel中加入一个counter变量用于计数
    var counter = countReserved
}