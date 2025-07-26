package com.example.module_home

import android.annotation.SuppressLint


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.module_found.FoundFragment
import com.example.module_home.databinding.ActivityHomeBinding // 导入生成的绑定类
import com.example.module_hot.HotFragment
import com.example.module_squa.SquareFragment

@Route(path = "/home/HomeActivity")
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val list  = listOf(
            HomeFragment(),
            SquareFragment(),
            HotFragment(),
            FoundFragment()
        )
        startFragment(list.get(0))

        binding.navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.one -> {
                    startFragment(list.get(0))
                    true
                }
                R.id.two -> {
                    startFragment(list.get(1))
                    true
                }
                R.id.three -> {
                    startFragment(list.get(2))
                    true
                }
                R.id.four -> {
                    startFragment(list.get(3))
                    true
                }

                else -> false
            }
        }


    }
    fun startFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}