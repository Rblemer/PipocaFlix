package br.com.rblemer.pipocaflix.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.rblemer.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: br.com.rblemer.domain.usecase.GetPopularMoviesUseCase
): ViewModel() {

    private val _movieState: MutableStateFlow<PagingData<Movie>> = MutableStateFlow(value = PagingData.empty())
    val movieState: MutableStateFlow<PagingData<Movie>>
        get() = _movieState

    init {
        this.loadPopularMovieList()
    }

    fun loadPopularMovieList() {
        viewModelScope.launch(Dispatchers.IO) {
            getPopularMoviesUseCase.execute().cachedIn(viewModelScope)
                .collect{
                    _movieState.value = it
                }

        }
    }
}