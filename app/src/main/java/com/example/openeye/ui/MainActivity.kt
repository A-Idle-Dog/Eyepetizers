

package com.example.openeye.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.module_found.FoundFragment
import com.example.module_home.HomeFragment
import com.example.module_hot.HotFragment
import com.example.module_my.mine
import com.example.module_squa.SquareFragment
import com.example.openeye.R

import com.example.openeye.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initView()

        }

    @SuppressLint("CommitTransaction")
    private fun initView() {
        val SquareFragment = SquareFragment()
        val foundFragment = FoundFragment()
        val homeFragment = HomeFragment()
        val hotFragment = HotFragment()
        val mineFragment = mine()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, homeFragment).commit()
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, foundFragment)
            add(R.id.fragment_container, SquareFragment)
            add(R.id.fragment_container, hotFragment)
            add(R.id.fragment_container, mineFragment)
            hide(mineFragment)
            hide(foundFragment)
            hide(SquareFragment)
            hide(hotFragment)
        }.commit()
        binding.navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.one -> {
                    supportFragmentManager.beginTransaction().apply {
                        hide(foundFragment)
                        hide(SquareFragment)
                        hide(hotFragment)
                        hide(mineFragment)
                        show(homeFragment)
                    }.commit()
                }
                R.id.two -> {
                    supportFragmentManager.beginTransaction().apply {
                        hide(foundFragment)
                        hide(homeFragment)
                        hide(hotFragment)
                        hide(mineFragment)
                        show(SquareFragment)

                    }.commit()
                }

                R.id.three -> {
                    supportFragmentManager.beginTransaction().apply {
                        hide(homeFragment)
                        hide(SquareFragment)
                        hide(mineFragment)
                        hide(hotFragment)
                        show(foundFragment)
                    }.commit()
                }

                R.id.four -> {
                    supportFragmentManager.beginTransaction().apply {
                        hide(foundFragment)
                        hide(SquareFragment)
                        hide(homeFragment)
                        hide(mineFragment)
                        show(hotFragment)
                    }.commit()
                }
                R.id.five -> {
                    supportFragmentManager.beginTransaction().apply {
                        hide(foundFragment)
                        hide(SquareFragment)
                        hide(homeFragment)
                        hide(hotFragment)
                        show(mineFragment)
                    }.commit()
                }
            }
            true
        }
    }
}