package dev.mizoguche.kiqueue.data

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.utils.io.core.use

class XmlClient {
    suspend fun Get(url: String): String {
        val client = HttpClient()
        return client.use {
            it.get(url)
        }
    }
}