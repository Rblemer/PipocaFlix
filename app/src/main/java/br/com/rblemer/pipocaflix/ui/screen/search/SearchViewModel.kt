package br.com.rblemer.pipocaflix.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.rblemer.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: br.com.rblemer.domain.usecase.SearchMoviesUseCase
) :  ViewModel() {

    private val _movieState: MutableStateFlow<PagingData<Movie>> = MutableStateFlow(value = PagingData.empty(
        LoadStates(
            append = LoadState.NotLoading(false),
            prepend = LoadState.NotLoading(false),
            refresh = LoadState.NotLoading(false),
        )
    ))
    val movieState: MutableStateFlow<PagingData<Movie>>
        get() = _movieState

    fun search(query: String) {
        _movieState.value = PagingData.empty(LoadStates(
            append = LoadState.NotLoading(false),
            prepend = LoadState.NotLoading(false),
            refresh = LoadState.Loading,
        ))
        viewModelScope.launch(Dispatchers.IO) {
            searchMoviesUseCase.execute(query).cachedIn(viewModelScope)
                .collect{
                    _movieState.value = it
                }
        }
    }
}