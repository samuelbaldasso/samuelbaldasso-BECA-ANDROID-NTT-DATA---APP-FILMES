package com.sbaldass.movieapp.ui.popular_movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sbaldass.movieapp.data.api.POST_PER_PAGE
import com.sbaldass.movieapp.data.api.TheMovieDBInterface
import com.sbaldass.movieapp.data.repository.MovieDataSource
import com.sbaldass.movieapp.data.repository.MovieDataSourceFactory
import com.sbaldass.movieapp.data.repository.NetworkState
import com.sbaldass.movieapp.data.vo.Movie
import io.reactivex.disposables.CompositeDisposable

class MoviePagedListRepo (private val apiService : TheMovieDBInterface) {

    lateinit var moviePagedList: LiveData<PagedList<Movie>>
    lateinit var moviesDataSourceFactory: MovieDataSourceFactory

    fun fetchLiveMoviePagedList (compositeDisposable: CompositeDisposable) : LiveData<PagedList<Movie>> {
        moviesDataSourceFactory = MovieDataSourceFactory(apiService, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()

        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<MovieDataSource, NetworkState>(
            moviesDataSourceFactory.moviesLiveDataSource, MovieDataSource::networkState)
    }
}