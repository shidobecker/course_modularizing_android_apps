package com.codingwithmithc.dotainfo.ui_heroList

import com.codingwithmitch.dotainfo.core.ProgressBarState
import com.codingwithmithc.dotainfo.hero_domain.Hero

data class HeroListState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val heros: List<Hero> = listOf()
)