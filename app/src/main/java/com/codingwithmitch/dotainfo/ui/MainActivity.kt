package com.codingwithmitch.dotainfo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import com.codingwithmitch.dotainfo.R
import com.codingwithmitch.dotainfo.ui.theme.DotaInfoTheme
import com.codingwithmithc.dotainfo.ui_heroList.HeroList
import com.codingwithmithc.dotainfo.ui_heroList.ui.HeroListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: HeroListViewModel = hiltViewModel()

            DotaInfoTheme {
                HeroList(state = viewModel.state.value, imageLoader)
            }
        }
    }
}















