package com.example.myapplication.activities

import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAppUiBinding

class AppUiActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private var backPressedTime: Long = 0
    private lateinit var binding: FragmentAppUiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAppUiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.ui_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        binding.categories.setOnClickListener {
            when (navController.currentDestination?.id) {
                R.id.calculate2 -> findNavController(R.id.ui_fragment).navigate(R.id.action_calculate2_to_expenses)
                R.id.details2 -> findNavController(R.id.ui_fragment).navigate(R.id.action_details2_to_expenses)
                R.id.total2 -> findNavController(R.id.ui_fragment).navigate(R.id.action_total2_to_expenses)
            }
        }
        binding.details.setOnClickListener {
            when (navController.currentDestination?.id) {
                R.id.calculate2 -> findNavController(R.id.ui_fragment).navigate(R.id.action_calculate2_to_details2)
                R.id.total2 -> findNavController(R.id.ui_fragment).navigate(R.id.action_total2_to_details2)
                R.id.expenses -> findNavController(R.id.ui_fragment).navigate(R.id.action_expenses_to_details2)
            }
        }
        binding.calculate.setOnClickListener {
            when (navController.currentDestination?.id) {
                R.id.total2 -> findNavController(R.id.ui_fragment).navigate(R.id.action_total2_to_calculate2)
                R.id.details2 -> findNavController(R.id.ui_fragment).navigate(R.id.action_details2_to_calculate2)
                R.id.expenses -> findNavController(R.id.ui_fragment).navigate(R.id.action_expenses_to_calculate2)
            }
        }
        binding.total.setOnClickListener {
            when (navController.currentDestination?.id) {
                R.id.calculate2 -> findNavController(R.id.ui_fragment).navigate(R.id.action_calculate2_to_total2)
                R.id.details2 -> findNavController(R.id.ui_fragment).navigate(R.id.action_details2_to_total2)
                R.id.expenses -> findNavController(R.id.ui_fragment).navigate(R.id.action_expenses_to_total2)
            }
        }
        binding.floatingActionButton.setOnClickListener {
            when (navController.currentDestination?.id) {
                R.id.calculate2 -> findNavController(R.id.ui_fragment).navigate(R.id.action_calculate2_to_expenseDialog)
                R.id.details2 -> findNavController(R.id.ui_fragment).navigate(R.id.action_details2_to_expenseDialog)
                R.id.expenses -> findNavController(R.id.ui_fragment).navigate(R.id.action_expenses_to_expenseDialog)
                R.id.total2 -> findNavController(R.id.ui_fragment).navigate(R.id.action_total2_to_expenseDialog)
            }
            val animation = AnimationUtils.loadAnimation(this, R.anim.fading)
            binding.bottomBar.startAnimation(animation)
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        val params = CoordinatorLayout.LayoutParams(
                            CoordinatorLayout.LayoutParams.MATCH_PARENT,
                            CoordinatorLayout.LayoutParams.MATCH_PARENT
                        )
                        params.setMargins(0, 0, 0, 0)
                        binding.uiFragment.layoutParams =
                            params
                    }, 400)
                }
                override fun onAnimationEnd(p0: Animation?) {
                    binding.bottomBar.visibility = View.INVISIBLE
                }
                override fun onAnimationRepeat(p0: Animation?) {}
            })
            binding.floatingActionButton.hide()
        }
    }
    override fun onBackPressed() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.ui_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        if (
            navController.currentDestination?.id != R.id.total2 &&
            navController.currentDestination?.id != R.id.details2 &&
            navController.currentDestination?.id != R.id.calculate2 &&
            navController.currentDestination?.id != R.id.expenses
        ) {
            val params = CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.MATCH_PARENT,
                CoordinatorLayout.LayoutParams.MATCH_PARENT
            )
            val r: Resources = resources
            val px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                55F,
                r.displayMetrics
            ).toInt()
            params.setMargins(0,0,0,px)
            binding.uiFragment.layoutParams = params
            findNavController(R.id.ui_fragment).navigate(R.id.details2)
            binding.bottomBar.visibility = View.VISIBLE
            val animation = AnimationUtils.loadAnimation(this, R.anim.blink)
            binding.bottomBar.startAnimation(animation)
            binding.uiFragment.startAnimation(animation)
            binding.floatingActionButton.show()
            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                super.onBackPressed()
                when(navController.currentDestination?.id) {
                    R.id.calculate2 -> findNavController(R.id.ui_fragment).navigate(R.id.action_calculate2_to_details2)
                    R.id.total2 -> findNavController(R.id.ui_fragment).navigate(R.id.action_total2_to_details2)
                    R.id.expenses -> findNavController(R.id.ui_fragment).navigate(R.id.action_expenses_to_details2)
                }
                finish()
            } else {
                when (navController.currentDestination?.id) {
                    R.id.calculate2 -> findNavController(R.id.ui_fragment).navigate(R.id.action_calculate2_to_details2)
                    R.id.total2 -> findNavController(R.id.ui_fragment).navigate(R.id.action_total2_to_details2)
                    R.id.expenses -> findNavController(R.id.ui_fragment).navigate(R.id.action_expenses_to_details2)
                }
            }
            backPressedTime = System.currentTimeMillis()
        }
        else {
            when(navController.currentDestination?.id) {
                R.id.calculate2 -> findNavController(R.id.ui_fragment).navigate(R.id.action_calculate2_to_details2)
                R.id.total2 -> findNavController(R.id.ui_fragment).navigate(R.id.action_total2_to_details2)
                R.id.expenses -> findNavController(R.id.ui_fragment).navigate(R.id.action_expenses_to_details2)
            }
            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                super.onBackPressed()
                when(navController.currentDestination?.id) {
                    R.id.calculate2 -> findNavController(R.id.ui_fragment).navigate(R.id.action_calculate2_to_details2)
                    R.id.total2 -> findNavController(R.id.ui_fragment).navigate(R.id.action_total2_to_details2)
                    R.id.expenses -> findNavController(R.id.ui_fragment).navigate(R.id.action_expenses_to_details2)
                }
                finish()
            } else {
                when(navController.currentDestination?.id) {
                    R.id.calculate2 -> findNavController(R.id.ui_fragment).navigate(R.id.action_calculate2_to_details2)
                    R.id.total2 -> findNavController(R.id.ui_fragment).navigate(R.id.action_total2_to_details2)
                    R.id.expenses -> findNavController(R.id.ui_fragment).navigate(R.id.action_expenses_to_details2)
                }
            }
            backPressedTime = System.currentTimeMillis()
        }
    }

}