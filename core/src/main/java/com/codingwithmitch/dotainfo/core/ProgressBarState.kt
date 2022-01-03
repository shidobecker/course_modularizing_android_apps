package com.codingwithmitch.dotainfo.core

sealed class ProgressBarState {

    object Loading : ProgressBarState()

    object Idle : ProgressBarState()

}
