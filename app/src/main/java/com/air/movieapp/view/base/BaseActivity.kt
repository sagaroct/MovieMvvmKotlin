package com.air.movieapp.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


abstract class BaseActivity : AppCompatActivity(), IFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActivityComponent()
    }

    protected abstract fun setupActivityComponent()

    override fun loadFragment(fragmentContainerId: Int, fragment: BaseFragment) {
        performFragmentTranscation(fragmentContainerId, fragment, null,
                0, 0, BaseFragment.FragmentTransactionType.ADD)
    }

    private fun performFragmentTranscation(fragmentContainerId: Int,
                                           fragment: Fragment, tag: String?,
                                           enterAnimId: Int, exitAnimId: Int,
                                           fragmentTransactionType: BaseFragment.FragmentTransactionType?) {
        when (fragmentTransactionType) {
            BaseFragment.FragmentTransactionType.ADD -> addFragment(fragmentContainerId, fragment, tag, enterAnimId, exitAnimId)
            BaseFragment.FragmentTransactionType.REPLACE -> replaceFragment(fragmentContainerId, fragment, tag, enterAnimId, exitAnimId)
            BaseFragment.FragmentTransactionType.ADD_TO_BACK_STACK_AND_ADD -> addToBackStackAndAdd(fragmentContainerId, fragment, tag, enterAnimId, exitAnimId)
            BaseFragment.FragmentTransactionType.ADD_TO_BACK_STACK_AND_REPLACE -> addToBackStackAndReplace(fragmentContainerId, fragment, tag, enterAnimId, exitAnimId)
            BaseFragment.FragmentTransactionType.POP_BACK_STACK_AND_REPLACE -> popBackStackAndReplace(fragmentContainerId, fragment, tag, enterAnimId, exitAnimId)
            BaseFragment.FragmentTransactionType.CLEAR_BACK_STACK_AND_REPLACE -> clearBackStackAndReplace(fragmentContainerId, fragment, tag, enterAnimId, exitAnimId)
            else -> replaceFragment(fragmentContainerId, fragment, tag, enterAnimId, exitAnimId)
        }
    }

    private fun addToBackStackAndAdd(fragmentContainerId: Int, fragment: Fragment, tag: String?, enterAnimId: Int, exitAnimId: Int) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimId, 0, 0, exitAnimId)
                .add(fragmentContainerId, fragment, tag)
                .addToBackStack(tag)
                .commit()
    }

    protected fun addFragment(fragmentContainerId: Int, fragment: Fragment, tag: String?, enterAnimId: Int, exitAnimId: Int) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimId, exitAnimId)
                .add(fragmentContainerId, fragment, tag)
                .commit()
    }

    private fun replaceFragment(fragmentContainerId: Int, fragment: Fragment, tag: String?,
                                enterAnimId: Int, exitAnimId: Int) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimId, exitAnimId)
                .replace(fragmentContainerId, fragment, tag).commit()
    }

    private fun popBackStackAndReplace(fragmentContainerId: Int, fragment: Fragment,
                                       tag: String?, enterAnimId: Int, exitAnimId: Int) {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimId, exitAnimId)
                .replace(fragmentContainerId, fragment, tag).commit()
    }

    private fun addToBackStackAndReplace(fragmentContainerId: Int, fragment: Fragment,
                                         tag: String?, enterAnimId: Int, exitAnimId: Int) {
        getSupportFragmentManager().beginTransaction().addToBackStack(null)
                .setCustomAnimations(enterAnimId, 0, 0, exitAnimId)
                .replace(fragmentContainerId, fragment, tag).commit()
    }

    private fun clearBackStackAndReplace(fragmentContainerId: Int, fragment: Fragment,
                                         tag: String?, enterAnimId: Int, exitAnimId: Int) {
        clearBackStack(FragmentManager.POP_BACK_STACK_INCLUSIVE)
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimId, exitAnimId)
                .replace(fragmentContainerId, fragment, tag).commit()
    }

    private fun clearBackStack(flag: Int) {
        val fm: FragmentManager = getSupportFragmentManager()
        if (fm.getBackStackEntryCount() > 0) {
            val first: FragmentManager.BackStackEntry = fm.getBackStackEntryAt(0)
            fm.popBackStack(first.getId(), flag)
        }
    }
}