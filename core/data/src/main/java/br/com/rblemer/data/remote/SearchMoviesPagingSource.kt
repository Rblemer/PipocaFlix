package br.com.rblemer.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.rblemer.domain.model.Movie
import br.com.rblemer.data.mapper.toMovie
import br.com.rblemer.domain.error.EmptyResponseError
import java.lang.Exception

class SearchMoviesPagingSource(
    private val remoteData: MovieRemoteData,
    private val query: String
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            val movies = remoteData.searchMovies(currentPage, query)

            if (movies.results.isEmpty() && currentPage == 1){
                LoadResult.Error(EmptyResponseError())
            } else {
                LoadResult.Page(
                    data = movies.results.map { it.toMovie() },
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (movies.results.isEmpty()) null else movies.page + 1
                )
            }

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}