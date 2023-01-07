
package com.air.movieapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by sagar on 20/8/16.
 */
@Entity
data class Movie(

    @PrimaryKey
    var id : Int,

    //In title and overview @ColumnInfo is not written as member variable name should be the name of column
    var title: String? = null,

    @ColumnInfo(name = "releaseDate")
    var release_date: String? = "",

    var overview: String? = null,

    @ColumnInfo(name = "voteAverage")
    var vote_average: Float = 0f,

    var type: String? = "")


   /* constructor(title: String?, releaseDate: String?, overview: String?, voteAverage: Float, type: String?) {
//        this.id = id;
        this.title = title
        release_date = releaseDate
        this.overview = overview
        vote_average = voteAverage
        this.type = type
    }*/
