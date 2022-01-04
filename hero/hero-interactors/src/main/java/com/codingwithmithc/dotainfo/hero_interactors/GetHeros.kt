package com.codingwithmithc.dotainfo.hero_interactors

import com.codingwithmitch.dotainfo.core.DataState
import com.codingwithmitch.dotainfo.core.ProgressBarState
import com.codingwithmitch.dotainfo.core.UIComponent
import com.codingwithmitch.dotainfo.hero_datasource.cache.HeroCache
import com.codingwithmitch.dotainfo.hero_datasource.network.HeroService
import com.codingwithmithc.dotainfo.hero_domain.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetHeros(
    private val service: HeroService, private val cache: HeroCache
) {

    fun execute(): Flow<DataState<List<Hero>>> = flow {
        try {

            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))

            val heros = try {
                service.getHeroStats()
            } catch (e: Exception) {
                e.printStackTrace()

                emit(
                    DataState.Response(
                        uiComponent = UIComponent.Dialog(
                            title = "Network Data Error",
                            description = e.message ?: "Unknown Error"
                        )
                    )
                )

                listOf()
            }


            // cache the network data
            cache.insert(heros)

            //Emit data from cache
            val cachedHeros = cache.selectAll()

            emit(DataState.Data(cachedHeros))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(
                DataState.Response(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        description = e.message ?: "Unknown Error"
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }

}