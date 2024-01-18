package br.com.rblemer.data.di


import br.com.rblemer.data.repository.MovieRepository
import br.com.rblemer.data.repository.MovieRepositoryImpl
import br.com.rblemer.data.local.MovieLocalData
import br.com.rblemer.data.local.MovieLocalDataImpl
import br.com.rblemer.data.remote.MovieRemoteData
import br.com.rblemer.data.remote.MovieRemoteDataImpl
import br.com.rblemer.data.usecase.AddToFavoriteUseCaseImpl
import br.com.rblemer.data.usecase.GetFavoriteMoviesUseCaseImpl
import br.com.rblemer.data.usecase.GetPopularMoviesUseCaseImpl
import br.com.rblemer.data.usecase.IsFavoriteUseCaseImpl
import br.com.rblemer.data.usecase.IsWatchedUseCaseImpl
import br.com.rblemer.data.usecase.MarkMovieAsNotWatchedUseCaseImpl
import br.com.rblemer.data.usecase.MarkMovieAsWatchedUseCaseImpl
import br.com.rblemer.data.usecase.RemoveFromFavoriteUseCaseImpl
import br.com.rblemer.data.usecase.SearchMoviesUseCaseImpl
import br.com.rblemer.domain.usecase.AddToFavoriteUseCase
import br.com.rblemer.domain.usecase.GetFavoriteMoviesUseCase
import br.com.rblemer.domain.usecase.GetPopularMoviesUseCase
import br.com.rblemer.domain.usecase.IsFavoriteUseCase
import br.com.rblemer.domain.usecase.IsWatchedUseCase
import br.com.rblemer.domain.usecase.MarkMovieAsNotWatchedUseCase
import br.com.rblemer.domain.usecase.MarkMovieAsWatchedUseCase
import br.com.rblemer.domain.usecase.RemoveFromFavoriteUseCase
import br.com.rblemer.domain.usecase.SearchMoviesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun providesMovieRemoteData(remoteData: MovieRemoteDataImpl) : MovieRemoteData
    @Binds
    abstract fun providesMovieLocalData(remoteData: MovieLocalDataImpl) : MovieLocalData
    @Binds
    abstract fun providesMovieRepository(repository: MovieRepositoryImpl) : MovieRepository
    @Binds
    abstract fun providesGetPopularMoviesUseCase(getPopularMoviesUseCaseImpl: GetPopularMoviesUseCaseImpl) : GetPopularMoviesUseCase
    @Binds
    abstract fun providesGetFavoriteMoviesUseCase(getFavoriteMoviesUseCase: GetFavoriteMoviesUseCaseImpl) : GetFavoriteMoviesUseCase
    @Binds
    abstract fun providesAddToFavoritesUseCase(addToFavoriteUseCase: AddToFavoriteUseCaseImpl) : AddToFavoriteUseCase
    @Binds
    abstract fun providesRemoveToFavoritesUseCase(removeFromFavoriteUseCaseImpl: RemoveFromFavoriteUseCaseImpl) : RemoveFromFavoriteUseCase
    @Binds
    abstract fun providesIsFavoriteUseCase(isFavoriteUseCaseImpl: IsFavoriteUseCaseImpl) : IsFavoriteUseCase
    @Binds
    abstract fun providesIsWatchedUseCase(isWatchedUseCaseImpl: IsWatchedUseCaseImpl) : IsWatchedUseCase
    @Binds
    abstract fun providesMarkMovieAsWatchedUseCase(markMovieAsWatchedUseCaseImpl: MarkMovieAsWatchedUseCaseImpl) : MarkMovieAsWatchedUseCase
    @Binds
    abstract fun providesMarkMovieAsNotWatchedUseCase(markMovieAsNotWatchedUseCaseImpl: MarkMovieAsNotWatchedUseCaseImpl) : MarkMovieAsNotWatchedUseCase
    @Binds
    abstract fun providesSearchMoviesUseCase(searchMoviesUseCaseImpl: SearchMoviesUseCaseImpl) : SearchMoviesUseCase
}