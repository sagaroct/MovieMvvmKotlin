/*
 * *
 *  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  *
 *  * @File: IFragmentInteractionListener.java
 *  * @Project: Devote
 *  * @Abstract:
 *  * @Copyright: Copyright © 2015, Saregama India Ltd. All Rights Reserved
 *  * Written under contract by Robosoft Technologies Pvt. Ltd.
 *  * <p/>
 *  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 */
package com.air.movieapp.view.base

import kotlin.jvm.Throws

interface IFragmentInteractionListener {
    /*   public void loadFragment(int fragmentContainerId, BaseFragment fragment,
                             @Nullable String tag, int enterAnimId, int exitAnimId,
                             BaseFragment.FragmentTransactionType fragmentTransactionType);*/
    fun loadFragment(fragmentContainerId: Int, fragment: BaseFragment)
}