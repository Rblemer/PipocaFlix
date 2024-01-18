package br.com.rblemer.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.rblemer.data.local.database.dao.MovieDao
import br.com.rblemer.data.local.model.MovieDB

@Database(entities = [MovieDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMoviesDAO() : MovieDao
}