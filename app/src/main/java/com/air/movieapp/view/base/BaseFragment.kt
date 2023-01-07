package com.air.movieapp.view.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment


abstract class BaseFragment : Fragment() {
    protected var mFragmentInteractionListener: IFragmentInteractionListener? = null

    /**
     * enum for set fragment transaction type
     */
    enum class FragmentTransactionType {
        ADD, REPLACE, ADD_TO_BACK_STACK_AND_REPLACE, POP_BACK_STACK_AND_REPLACE, CLEAR_BACK_STACK_AND_REPLACE, ADD_TO_BACK_STACK_AND_ADD
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mFragmentInteractionListener = try {
            context as IFragmentInteractionListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString()
                    .toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupFragmentComponent()
    }

    protected abstract fun setupFragmentComponent()
}