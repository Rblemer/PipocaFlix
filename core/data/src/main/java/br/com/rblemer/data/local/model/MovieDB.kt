package br.com.rblemer.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieDB(
    @PrimaryKey val id: Int,
    @ColumnInfo val adult: Boolean,
    @ColumnInfo val original_title: String,
    @ColumnInfo val overview: String,
    @ColumnInfo val poster_path: String,
    @ColumnInfo val release_date: String,
    @ColumnInfo val title: String,
    @ColumnInfo val vote_average: Double,
    @ColumnInfo val vote_count: Int,
    @ColumnInfo val alreadyWatched: Boolean = false
)