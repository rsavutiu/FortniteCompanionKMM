package org.smartmuseum.fortnitecompanion.di

import androidx.compose.ui.text.intl.Locale
import com.plusmobileapps.konnectivity.Konnectivity
import de.jensklingenberg.ktorfit.converter.CallConverterFactory
import de.jensklingenberg.ktorfit.converter.FlowConverterFactory
import de.jensklingenberg.ktorfit.ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import org.lighthousegames.logging.KmLog
import org.lighthousegames.logging.logging
import org.smartmuseum.fortnitecompanion.Greeting
import org.smartmuseum.fortnitecompanion.getPlatform
import org.smartmuseum.fortnitecompanion.networking.BASE_URL
import org.smartmuseum.fortnitecompanion.networking.FortniteAPISupportedLanguages
import org.smartmuseum.fortnitecompanion.networking.FortniteApiInterface
import org.smartmuseum.fortnitecompanion.networking.ResponseConverter
import org.smartmuseum.fortnitecompanion.networking.createFortniteApiInterface
import org.smartmuseum.fortnitecompanion.usecases.FindStatsUseCase
import org.smartmuseum.fortnitecompanion.usecases.GetCosmeticsUseCase
import org.smartmuseum.fortnitecompanion.usecases.GetShopDataUseCase
import org.smartmuseum.fortnitecompanion.utils.ExternalIntents
import org.smartmuseum.fortnitecompanion.utils.TextUtils

val commonModule = module {
    single { getPlatform() }
    single { Greeting(platform = get()) }
    factory<KmLog> { params ->
        if (params.isNotEmpty()) {
            logging(tag = params[0])
        } else {
            logging()
        }
    }

    single<TextUtils> { TextUtils() }

    single<ExternalIntents> { parameters ->
        ExternalIntents(parameters[0])
    }

    single<Konnectivity> {
        Konnectivity()
    }

    single<FortniteApiInterface> {
        val ktorfit = ktorfit {
            baseUrl(BASE_URL)
            httpClient(HttpClient {
                install(HttpRequestRetry) {
                    retryOnServerErrors(maxRetries = 3)
                    exponentialDelay()
                }
                install(Logging) {
                    val log: KmLog by inject { parametersOf("HttpClient") }
                    level = LogLevel.BODY
                    sanitizeHeader { header -> header == HttpHeaders.Authorization }
                    logger = object : Logger {
                        override fun log(message: String) {
                            log.info { message }
                        }

                    }
                }
                install(ContentNegotiation) {
                    json(Json { isLenient = true; ignoreUnknownKeys = true; prettyPrint = true })
                }
            })
            converterFactories(
                FlowConverterFactory(),
                CallConverterFactory(),
                ResponseConverter()
            )
        }
        ktorfit.createFortniteApiInterface()
    }

    single<GetCosmeticsUseCase> {
        GetCosmeticsUseCase(
            fortniteApi = get(),
            language = FortniteAPISupportedLanguages.getSupportedLanguage(Locale.current.language)
        )
    }

    single<FindStatsUseCase> {
        FindStatsUseCase(
            fortniteApi = get(),
            language = FortniteAPISupportedLanguages.getSupportedLanguage(Locale.current.language)
        )
    }

    single<GetShopDataUseCase> {
        GetShopDataUseCase(
            fortniteApi = get(),
            language = FortniteAPISupportedLanguages.getSupportedLanguage(Locale.current.language)
        )
    }

    single<ResponseConverter> {
        ResponseConverter()
    }

}