package com.dfg.viewmodeldemo

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.dfg.viewmodeldemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //这是一种SharedPreferences 接口中的一个默认实现，它会使用当前活动的类名作为 SharedPreferences 文件的名称。
        sp = getPreferences(MODE_PRIVATE)

        val countReserved = sp.getInt("count_reserved", 0)

        // 不可以直接去创建ViewModel的实例，而是一定要通过ViewModelProvider来获取ViewModel的实例
        // viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // 额外传入一个MainViewModelFactory参数，这里将读取到的计数值传给了MainViewModelFactory的构造函数。
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(countReserved)
        ).get(MainViewModel::class.java)

        binding?.btnPlus?.setOnClickListener {
            viewModel.counter++
            refreshCounter()
        }
        binding?.btnClear?.setOnClickListener {
            viewModel.counter = 0;
            refreshCounter()
        }
    }

    /**
     * UI刷新
     */
    private fun refreshCounter() {
        binding?.textview?.text = viewModel.counter.toString()
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter)
        }
    }
}