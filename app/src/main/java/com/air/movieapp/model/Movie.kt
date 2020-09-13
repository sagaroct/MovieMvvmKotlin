/*
 *
 *  * Copyright © 2016, Robosoft Technologies Pvt. Ltd
 *  * Written under contract by Robosoft Technologies Pvt. Ltd.
 *
 */
package com.air.movieapp.model

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.air.movieapp.BR
import kotlin.jvm.Throws

/**
 * Created by sagar on 20/8/16.
 */
@Entity
class Movie : BaseObservable {
    /*    public float getVoteAverage() {
        return vote_average;
    }

    public void setVoteAverage(float voteAverage) {
        this.vote_average = voteAverage;
    }*/
    @PrimaryKey(autoGenerate = true)
    var idAutoGenerated = 0

    //In title and overview @ColumnInfo is not written as member variable name should be the name of column
    var title: String? = null

    @ColumnInfo(name = "releaseDate")
    var release_date: String? = ""

    /*    public String getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(String releaseDate) {
        this.release_date = releaseDate;
    }*/ var overview: String? = null

    @ColumnInfo(name = "voteAverage")
    var vote_average = 0f
    var type: String? = ""

  /*  @get:Bindable
    var isVisible = true
        set(visible) {
            field = visible
            notifyPropertyChanged(BR.visible)
        }*/

    constructor() {}
    constructor(title: String?, releaseDate: String?, overview: String?, voteAverage: Float, type: String?) {
//        this.id = id;
        this.title = title
        release_date = releaseDate
        this.overview = overview
        vote_average = voteAverage
        this.type = type
    }

    fun onClick() {
        Log.d("Movie", "onClick: " + "In Movie")
    }
}