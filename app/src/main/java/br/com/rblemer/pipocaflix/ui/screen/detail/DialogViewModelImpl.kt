package br.com.rblemer.pipocaflix.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rblemer.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DialogViewModelImpl @Inject constructor(
    private val isFavoriteUseCase: br.com.rblemer.domain.usecase.IsFavoriteUseCase,
    private val isWatchedUseCase: br.com.rblemer.domain.usecase.IsWatchedUseCase,
    private val markMovieAsWatchedUseCase: br.com.rblemer.domain.usecase.MarkMovieAsWatchedUseCase,
    private val markMovieAsNotWatchedUseCase: br.com.rblemer.domain.usecase.MarkMovieAsNotWatchedUseCase,
    private val addToFavoriteUseCase: br.com.rblemer.domain.usecase.AddToFavoriteUseCase,
    private val removeFromFavoriteUseCase: br.com.rblemer.domain.usecase.RemoveFromFavoriteUseCase,
): ViewModel(), DialogViewModel {

    private val _screenDialogState: MutableStateFlow<ScreenDialogModel> = MutableStateFlow(
        ScreenDialogModel()
    )
    val screenDialogState: MutableStateFlow<ScreenDialogModel>
        get() = _screenDialogState

    override fun isFavorite(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            isFavoriteUseCase.execute(movie = movie)
                .catch {
                    _screenDialogState.value = _screenDialogState.value.copy(
                        isFavorite = false
                    )
                }
                .collect {
                    _screenDialogState.value = _screenDialogState.value.copy(
                        isFavorite = it
                    )
                }
        }
    }

    override fun isWatched(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            isWatchedUseCase.execute(movie = movie)
                .catch {
                    _screenDialogState.value = _screenDialogState.value.copy(
                        isWatched = false
                    )
                }
                .collect {
                    _screenDialogState.value = _screenDialogState.value.copy(
                        isWatched = it
                    )
                }
        }
    }

    override fun updateIsWatchedState(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            if (movie.isWatched){
                markMovieAsWatchedUseCase.execute(movie = movie)
            } else {
                markMovieAsNotWatchedUseCase.execute(movie = movie)
            }
        }
    }

    override fun updateIsFavoriteState(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            if (movie.isFavorite){
                addToFavoriteUseCase.execute(movie = movie)
            } else {
                removeFromFavoriteUseCase.execute(movie = movie)
            }
        }
    }

}