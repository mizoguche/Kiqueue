package dev.mizoguche.kiqueue.data

import dev.mizoguche.kiqueue.domain.Podcast
import dev.mizoguche.kiqueue.domain.PodcastDescription
import dev.mizoguche.kiqueue.domain.PodcastFeedUrl
import dev.mizoguche.kiqueue.domain.PodcastImageUrl
import dev.mizoguche.kiqueue.domain.PodcastTitle
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.StringReader
import java.lang.IllegalArgumentException

class PodcastXmlPullParser : PodcastXmlParser {
    private val namespace: String? = null

    override fun parse(podcastXml: String): Podcast {
        val stringReader = StringReader(podcastXml)
        val factory = XmlPullParserFactory.newInstance()
        val parser = factory.newPullParser()
        stringReader.use {
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(stringReader)

            while (parser.nextTag() != XmlPullParser.END_TAG) {
                if (parser.eventType != XmlPullParser.START_TAG) {
                    continue
                }
                if (parser.name == "channel") {
                    return readPodcast(parser)
                }
            }
        }

        throw IllegalArgumentException("no channel tag found")
    }

    private fun readPodcast(parser: XmlPullParser): Podcast {
        parser.require(XmlPullParser.START_TAG, namespace, "channel")
        var title = ""
        var description = ""
        var feedUrl = ""
        var imageUrl = ""

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                "title" -> title = readText(parser, "title")
                "description" -> description = readText(parser, "description")
                "itunes:summary" -> description = readText(parser, "itunes:summary")
                "itunes:image" -> imageUrl = readAttribute(parser, "itunes:image", "href")
                "atom:link" -> {
                    val attr = readAttribute(parser, "atom:link", "href") {
                        it.getAttributeValue(null, "type") == "application/rss+xml"
                    }
                    if (attr != "") {
                        feedUrl = attr
                    }
                }
                else -> skip(parser)
            }
        }

        return Podcast(
            title = PodcastTitle(title),
            description = PodcastDescription(description),
            feedUrl = PodcastFeedUrl(feedUrl),
            imageUrl = PodcastImageUrl(imageUrl)
        )
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readText(parser: XmlPullParser, tag: String): String {
        parser.require(XmlPullParser.START_TAG, namespace, tag)
        val title = readText(parser)
        parser.require(XmlPullParser.END_TAG, namespace, tag)
        return title
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readText(parser: XmlPullParser): String {
        var result = ""
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.text
            parser.nextTag()
        }
        return result
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readAttribute(
        parser: XmlPullParser,
        tag: String,
        attribute: String,
        filter: (parser: XmlPullParser) -> Boolean = { true }
    ): String {
        parser.require(XmlPullParser.START_TAG, namespace, tag)
        var attributeValue = ""
        parser.nextTag()
        if (filter(parser)) {
            attributeValue = parser.getAttributeValue(null, attribute)
        }
        return attributeValue
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun skip(parser: XmlPullParser) {
        if (parser.eventType != XmlPullParser.START_TAG) {
            throw IllegalStateException()
        }
        var depth = 1
        while (depth != 0) {
            when (parser.next()) {
                XmlPullParser.END_TAG -> depth--
                XmlPullParser.START_TAG -> depth++
            }
        }
    }
}