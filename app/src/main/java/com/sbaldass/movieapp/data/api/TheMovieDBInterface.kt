package com.sbaldass.movieapp.data.api

import com.sbaldass.movieapp.data.vo.MovieDetails
import com.sbaldass.movieapp.data.vo.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBInterface {

    // https://api.themoviedb.org/3/movie/popular?api_key=bea61ff5054f9ae165b6a33635b0e9c2&page=1
    // https://api.themoviedb.org/3/movie/299534?api_key=bea61ff5054f9ae165b6a33635b0e9c2
    // https://api.themoviedb.org/3/


    @GET("movie/popular")
    fun getPopularMovie(@Query("page") page: Int): Single<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>
}