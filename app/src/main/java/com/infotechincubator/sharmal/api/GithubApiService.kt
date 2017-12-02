package com.infotechincubator.sharmal.api

import com.infotechincubator.sharmal.model.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by kyawagwin on 29/11/17.
 */
interface GithubApiService {

    @GET("/search/repositories")
    fun repoSearch(
            @Query("q") query: String,
            @Query("sort") sort: String,
            @Query("order") order: String
    ): Single<SearchResponse>
}