package com.codingwithmithc.dotainfo.hero_interactors

import com.codingwithmitch.dotainfo.hero_datasource.cache.HeroCache
import com.codingwithmitch.dotainfo.hero_datasource.network.HeroService
import com.squareup.sqldelight.db.SqlDriver


//Holder for all use cases
class HeroInteractors(
    val getHeros: GetHeros
) {
    companion object Factory {
        fun build(sqlDriver: SqlDriver): HeroInteractors {
            val service = HeroService.build()
            val cache = HeroCache.build(sqlDriver)

            return HeroInteractors(getHeros = GetHeros(service, cache))
        }
        val schema: SqlDriver.Schema = HeroCache.schema

        val dbName: String = HeroCache.dbName
    }

}