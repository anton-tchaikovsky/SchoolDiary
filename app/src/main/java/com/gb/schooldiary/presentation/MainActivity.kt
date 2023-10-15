package com.gb.schooldiary.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.gb.schooldiary.R
import com.gb.schooldiary.databinding.ActivityMainBinding
import com.gb.schooldiary.presentation.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applyBackPressed()
        if (savedInstanceState != null)
            initView(savedInstanceState.getString(KEY_CURRENT_FRAGMENT) ?: TAG_HOME_FRAGMENT)
        else {
            openFragment(HomeFragment.newInstance(), TAG_HOME_FRAGMENT)
            initView(TAG_HOME_FRAGMENT)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(
            KEY_CURRENT_FRAGMENT,
            supportFragmentManager.findFragmentById(R.id.fragment_container)?.tag
                ?: TAG_HOME_FRAGMENT
        )
        super.onSaveInstanceState(outState)
    }

    private fun applyBackPressed() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount == 0)
                    finish()
                supportFragmentManager.popBackStack()
                initShowingItemsMenu()
            }
        })
    }

    private fun initView(tag: String) {
        initClickOnItemsMenu()
        initShowingItemsMenu(tag)
    }

    private fun openFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.popBackStack()
        if (tag == TAG_HOME_FRAGMENT)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, tag)
                .commitAllowingStateLoss()
        else
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss()
    }

    private fun resetShowingIconsMenu() {
        with(binding) {
            homeImageView.visibility = View.VISIBLE
            homeCardView.visibility = View.INVISIBLE
            classesImageView.visibility = View.VISIBLE
            classesCardView.visibility = View.INVISIBLE
            notesImageView.visibility = View.VISIBLE
            notesCardView.visibility = View.INVISIBLE
            marksImageView.visibility = View.VISIBLE
            marksCardView.visibility = View.INVISIBLE
        }
    }

    private fun initShowingItemsMenu(tag: String = TAG_HOME_FRAGMENT) {
        resetShowingIconsMenu()
        with(binding) {
            when (tag) {
                TAG_HOME_FRAGMENT -> {
                    homeImageView.visibility = View.INVISIBLE
                    homeCardView.visibility = View.VISIBLE
                }

                TAG_CLASSES_FRAGMENT -> {
                    classesImageView.visibility = View.INVISIBLE
                    classesCardView.visibility = View.VISIBLE
                }

                TAG_NOTES_FRAGMENT -> {
                    notesImageView.visibility = View.INVISIBLE
                    notesCardView.visibility = View.VISIBLE
                }

                TAG_MARKS_FRAGMENT -> {
                    marksImageView.visibility = View.INVISIBLE
                    marksCardView.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun initClickOnItemsMenu() {
        with(binding) {
            homeImageView.setOnClickListener {
                initShowingItemsMenu(TAG_HOME_FRAGMENT)
                openFragment(HomeFragment.newInstance(), TAG_HOME_FRAGMENT)
            }
            classesImageView.setOnClickListener {
                initShowingItemsMenu(TAG_CLASSES_FRAGMENT)
                openFragment(ClassesFragment.newInstance(), TAG_CLASSES_FRAGMENT)
            }
            notesImageView.setOnClickListener {
                initShowingItemsMenu(TAG_NOTES_FRAGMENT)
                openFragment(NotesFragment.newInstance(), TAG_NOTES_FRAGMENT)
            }
            marksImageView.setOnClickListener {
                initShowingItemsMenu(TAG_MARKS_FRAGMENT)
                openFragment(MarksFragment.newInstance(), TAG_MARKS_FRAGMENT)
            }
        }
    }

    companion object {
        private const val TAG_HOME_FRAGMENT = "home fragment"
        private const val TAG_CLASSES_FRAGMENT = "classes fragment"
        private const val TAG_NOTES_FRAGMENT = "notes fragment"
        private const val TAG_MARKS_FRAGMENT = "marks fragment"
        private const val KEY_CURRENT_FRAGMENT = "key current fragment"
    }
}