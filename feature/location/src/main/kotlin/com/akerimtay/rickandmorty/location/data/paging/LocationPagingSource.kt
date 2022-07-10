package com.akerimtay.rickandmorty.location.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.akerimtay.rickandmorty.core.common.model.Location
import com.akerimtay.rickandmorty.location.data.remote.LocationRemoteDataSource
import java.io.IOException
import retrofit2.HttpException

internal class LocationPagingSource(
    private val locationRemoteDataSource: LocationRemoteDataSource
) : PagingSource<Int, Location>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Location> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val locations = locationRemoteDataSource.getLocations(page = page)
            LoadResult.Page(
                data = locations,
                prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (locations.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Location>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {

        private const val DEFAULT_PAGE_INDEX = 1
    }
}