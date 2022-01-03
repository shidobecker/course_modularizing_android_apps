package com.codingwithmithc.dotainfo.hero_interactors

import com.codingwithmitch.dotainfo.hero_datasource.network.HeroService


//Holder for all use cases
class HeroInteractors(
    val getHeros: GetHeros
) {
    companion object Factory {
        fun build(): HeroInteractors {
            val service = HeroService.build()

            return HeroInteractors(getHeros =  GetHeros(service))
        }

    }

}