package br.com.rblemer.pipocaflix.di

import br.com.rblemer.pipocaflix.ui.screen.detail.DialogViewModel
import br.com.rblemer.pipocaflix.ui.screen.detail.DialogViewModelImpl
import br.com.rblemer.pipocaflix.ui.screen.favorite.FavoriteViewModel
import br.com.rblemer.pipocaflix.ui.screen.favorite.FavoriteViewModelImpl
import br.com.rblemer.pipocaflix.ui.screen.home.HomeViewModel
import br.com.rblemer.pipocaflix.ui.screen.home.HomeViewModelImpl
import br.com.rblemer.pipocaflix.ui.screen.search.SearchViewModel
import br.com.rblemer.pipocaflix.ui.screen.search.SearchViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {
    @Binds
    abstract fun providesHomeViewModel(
        homeViewModel: HomeViewModelImpl
    ): HomeViewModel

    @Binds
    abstract fun providesFavoriteViewModel(
        favoriteViewModel: FavoriteViewModelImpl
    ): FavoriteViewModel

    @Binds
    abstract fun providesDialogViewModel(
        dialogViewModelImpl: DialogViewModelImpl
    ): DialogViewModel

    @Binds
    abstract fun providesSearchViewModel(
       searchViewModelImpl: SearchViewModelImpl
    ): SearchViewModel

}