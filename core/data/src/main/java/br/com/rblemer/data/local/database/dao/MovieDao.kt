package br.com.rblemer.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.rblemer.data.local.model.MovieDB
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieDB")
    fun getFavoriteMovies() : Flow<List<MovieDB>>

    @Query("UPDATE MovieDB SET alreadyWatched = 1 WHERE id == :movieId")
    fun markMovieAsWatched(movieId: Int)

    @Query("UPDATE MovieDB SET alreadyWatched = 0 WHERE id == :movieId")
    fun markMovieAsNotWatched(movieId: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMovie(movie: MovieDB)

    @Delete
    fun deleteMovie(movie: MovieDB)

    @Query("SELECT EXISTS (SELECT 1 FROM MovieDB WHERE id = :movieId)")
    fun isFavorite(movieId: Int) : Flow<Boolean>

    @Query("SELECT alreadyWatched FROM MovieDB WHERE id = :movieId")
    fun isWatched(movieId: Int) : Flow<Int>
}