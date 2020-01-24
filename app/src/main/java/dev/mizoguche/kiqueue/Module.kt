package dev.mizoguche.kiqueue

import androidx.room.Room
import dev.mizoguche.kiqueue.data.PodcastRepository
import dev.mizoguche.kiqueue.data.PodcastXmlParser
import dev.mizoguche.kiqueue.data.PodcastXmlPullParser
import dev.mizoguche.kiqueue.data.XmlClient
import dev.mizoguche.kiqueue.data.room.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val kiqueueModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "kiqueue.db"
        ).build()
    }

    single { get<AppDatabase>().podcastDao() }
    single { PodcastXmlPullParser() as PodcastXmlParser }
    single { XmlClient() }
    single { PodcastRepository(get(), get(), get()) }
}