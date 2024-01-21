package br.com.rblemer.pipocaflix

import android.content.Context
import androidx.room.Room
import br.com.rblemer.data.local.database.AppDatabase
import br.com.rblemer.data.local.database.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext applicationContext: Context) : AppDatabase {
        return Room
            .inMemoryDatabaseBuilder(context = applicationContext, AppDatabase::class.java)
            .build()
    }

    @Provides
    @Singleton
    fun providesMovieDAO(appDatabase: AppDatabase) : MovieDao {
        return appDatabase.getMoviesDAO()
    }
}