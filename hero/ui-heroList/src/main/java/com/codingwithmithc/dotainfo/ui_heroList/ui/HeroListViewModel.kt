package com.codingwithmithc.dotainfo.ui_heroList.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithmitch.dotainfo.core.DataState
import com.codingwithmitch.dotainfo.core.Logger
import com.codingwithmitch.dotainfo.core.UIComponent
import com.codingwithmithc.dotainfo.hero_interactors.GetHeros
import com.codingwithmithc.dotainfo.ui_heroList.HeroListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val getHeros: GetHeros,
    @Named("heroListLogger") private val logger: Logger,
) : ViewModel() {

    val state: MutableState<HeroListState> = mutableStateOf(HeroListState())

    init {
        onTriggerEvent(HeroListEvents.GetHeros)
    }

    private fun getHeros() {
        getHeros.execute().onEach { dataState ->
            when (dataState) {
                is DataState.Response -> {
                    when (dataState.uiComponent) {
                        is UIComponent.Dialog -> {
                            logger.log((dataState.uiComponent as UIComponent.Dialog).description)
                        }

                        is UIComponent.None -> {
                            logger.log((dataState.uiComponent as UIComponent.None).message)
                        }
                    }
                }

                is DataState.Data -> {
                    state.value = state.value.copy(heros = dataState.data ?: listOf())
                }

                is DataState.Loading -> {
                    state.value = state.value.copy(progressBarState = state.value.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onTriggerEvent(event: HeroListEvents) {
        when (event) {
            is HeroListEvents.GetHeros -> {
                getHeros()
            }
        }
    }

}