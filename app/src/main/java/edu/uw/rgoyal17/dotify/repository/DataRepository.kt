package edu.uw.rgoyal17.dotify.repository

import edu.uw.rgoyal17.dotify.model.SongList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class DataRepository {

    val songService = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SongService::class.java)

    suspend fun getSongs(): SongList = songService.getSongs()

}

interface SongService {

    @GET("echeeUW/codesnippets/master/musiclibrary.json")
    suspend fun getSongs(): SongList

}