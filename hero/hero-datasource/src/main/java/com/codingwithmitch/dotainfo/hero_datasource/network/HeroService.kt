package com.codingwithmitch.dotainfo.hero_datasource.network

import com.codingwithmithc.dotainfo.hero_domain.Hero
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

interface HeroService {

    suspend fun getHeroStats(): List<Hero>

    companion object Factory {

        fun build(): HeroService {
            return HeroServiceImplementation(
                httpClient = HttpClient(Android) {
                    install(JsonFeature) {
                        serializer = KotlinxSerializer(
                            json = kotlinx.serialization.json.Json {
                                ignoreUnknownKeys = true
                            }
                        )
                    }
                }
            )
        }


    }



}