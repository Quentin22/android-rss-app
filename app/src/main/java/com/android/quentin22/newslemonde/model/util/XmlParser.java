package com.android.quentin22.newslemonde.model.util;

import android.util.Log;
import android.util.Xml;

import com.android.quentin22.newslemonde.model.News;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * XML Parser with a given URL
 *
 * @author Quentin22
 */

public class XmlParser {

    private static final String TAG = XmlParser.class.getSimpleName();

    public static List retrieveNews(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setReadTimeout(10000 /* milliseconds */);
            connection.setConnectTimeout(15000 /* milliseconds */);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream stream = connection.getInputStream();
                return parseXml(stream);
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return null;
    }

    private static List parseXml(InputStream stream) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(stream, null);
            parser.nextTag();
            parser.nextTag();
            return readRss(parser);
        } catch (XmlPullParserException | IOException e1) {
            e1.printStackTrace();
        } finally {
            stream.close();
        }
        return null;
    }

    private static List readRss(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<News> newsList = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, null, Constants.TAG_CHANNEL);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals(Constants.TAG_ITEM)) {
                newsList.add(readItem(parser));
            } else {
                skip(parser);
            }
        }
        return newsList;
    }

    private static News readItem(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, null, Constants.TAG_ITEM);
        String title = null;
        String description = null;
        String link = null;
        String imageUrl = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            switch (parser.getName()) {
                case Constants.TAG_TITLE:
                    parser.require(XmlPullParser.START_TAG, null, Constants.TAG_TITLE);
                    title = readText(parser);

                    parser.require(XmlPullParser.END_TAG, null, Constants.TAG_TITLE);
                    break;
                case Constants.TAG_DESCRIPTION:
                    parser.require(XmlPullParser.START_TAG, null, Constants.TAG_DESCRIPTION);
                    description = readText(parser);
                    parser.require(XmlPullParser.END_TAG, null, Constants.TAG_DESCRIPTION);
                    break;
                case Constants.TAG_LINK:
                    parser.require(XmlPullParser.START_TAG, null, Constants.TAG_LINK);
                    link = readText(parser);
                    parser.require(XmlPullParser.END_TAG, null, Constants.TAG_LINK);
                    break;
                case Constants.TAG_ENCLOSURE:
                    parser.require(XmlPullParser.START_TAG, null, Constants.TAG_ENCLOSURE);
                    imageUrl = parser.getAttributeValue(null, Constants.TAG_URL);
                    parser.nextTag();
                    break;
                default:
                    skip(parser);
            }
        }
        return new News.NewsBuilder()
                .title(title)
                .description(description)
                .link(link)
                .imageUrl(imageUrl)
                .build();
    }

    private static String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private static void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}