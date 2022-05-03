package com.sbaldass.movieapp.ui.single_movie_details

import androidx.lifecycle.LiveData
import com.sbaldass.movieapp.data.api.TheMovieDBInterface
import com.sbaldass.movieapp.data.repository.MovieDetailsNetworkDataSource
import com.sbaldass.movieapp.data.repository.NetworkState
import com.sbaldass.movieapp.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepo (private val apiService : TheMovieDBInterface) {

    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

    fun fetchSingleMovieDetails (compositeDisposable: CompositeDisposable, movieId: Int) : LiveData<MovieDetails> {

        movieDetailsNetworkDataSource = MovieDetailsNetworkDataSource(apiService,compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMovieResponse

    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }

}