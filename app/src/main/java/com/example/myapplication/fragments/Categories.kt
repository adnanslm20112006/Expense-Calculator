package com.example.myapplication.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCategoriesBinding
import com.example.myapplication.fragments.viewPager.expense.FirstScreen2
import com.example.myapplication.fragments.viewPager.expense.SecondScreen2
import com.example.myapplication.fragments.viewPager.expense.ThirdScreen2
import com.example.myapplication.viewPager.ExpenseViewPagerAdapter
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_categories.*


class Categories : Fragment() {
    private lateinit var binding: FragmentCategoriesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val floatingActionButton: (FloatingActionButton) =
            activity?.findViewById(R.id.floatingActionButton)!!
        val bottomAppBar: (BottomAppBar) = activity?.findViewById(R.id.bottom_bar)!!
        val fragment = activity?.findViewById<FragmentContainerView>(R.id.ui_fragment)
        val fragmentList = arrayListOf(
            FirstScreen2(),
            SecondScreen2(),
            ThirdScreen2()
        )
        val fab = arrayOf(
            restaurant,
            shopping,
            transport,
            health,
            family,
            gifts,
            entertainment,
            emergency,
            travel,
        )
        for (i in fab) {
            onCategoryButtonClicked(i)
        }
        binding.other.setOnClickListener {
            bottomAppBar.visibility = View.INVISIBLE
            floatingActionButton.hide()
            val params = CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.MATCH_PARENT,
                CoordinatorLayout.LayoutParams.MATCH_PARENT
            )
            params.setMargins(0, 0, 0, 0)
            fragment?.layoutParams = params
            findNavController().navigate(R.id.action_expenses_to_otherInformation)
        }
        val adapter = ExpenseViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        binding.viewPager.adapter = adapter
    }

    private fun onCategoryButtonClicked(btn: FloatingActionButton) {
        val floatingActionButton: (FloatingActionButton) =
            activity?.findViewById(R.id.floatingActionButton)!!
        val bottomAppBar: (BottomAppBar) = activity?.findViewById(R.id.bottom_bar)!!
        btn.setOnClickListener {
            val text = when (btn) {
                restaurant -> getString(R.string.restaurant)
                shopping -> getString(R.string.shopping)
                transport -> getString(R.string.transport)
                health -> getString(R.string.health)
                family -> getString(R.string.family)
                gifts -> getString(R.string.gifts)
                entertainment -> getString(R.string.entertainment)
                emergency -> getString(R.string.emergency)
                else -> getString(R.string.travel)
            }
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.fading)
            requireActivity().findViewById<BottomAppBar>(R.id.bottom_bar).startAnimation(animation)
            animation.setAnimationListener(object : AnimationListener {
                override fun onAnimationStart(p0: Animation?) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        val params = CoordinatorLayout.LayoutParams(
                            CoordinatorLayout.LayoutParams.MATCH_PARENT,
                            CoordinatorLayout.LayoutParams.MATCH_PARENT
                        )
                        params.setMargins(0, 0, 0, 0)
                        activity?.findViewById<FragmentContainerView>(R.id.ui_fragment)?.layoutParams =
                            params
                    }, 400)
                    val action = CategoriesDirections.actionExpensesToInformation(text)
                    findNavController().navigate(action)
                }
                override fun onAnimationEnd(p0: Animation?) {
                    bottomAppBar.visibility = View.INVISIBLE
                }
                override fun onAnimationRepeat(p0: Animation?) {}
            })
            floatingActionButton.hide()
        }
    }
}