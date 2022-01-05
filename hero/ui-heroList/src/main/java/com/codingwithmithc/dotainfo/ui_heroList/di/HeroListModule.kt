package com.codingwithmithc.dotainfo.ui_heroList.di

import com.codingwithmitch.dotainfo.core.Logger
import com.codingwithmithc.dotainfo.hero_interactors.GetHeros
import com.codingwithmithc.dotainfo.hero_interactors.HeroInteractors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeroListModule {


    @Provides
    @Singleton
    fun provideGetHeros(interactors: HeroInteractors): GetHeros {
        return interactors.getHeros
    }

    @Provides
    @Singleton
    @Named("heroListLogger")
    fun provideLogger(): Logger {
        return Logger(tag = "HeroList", isDebug = true)
    }

}