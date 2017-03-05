package com.android.quentin22.newslemonde.model.util;

/**
 * Constants
 *
 * @author Quentin22
 */

public interface Constants {

    String URL_LEMONDE_RSS = "http://www.lemonde.fr/rss/une.xml";

    // Tags for XML Parser
    String TAG_CHANNEL = "channel";
    String TAG_ITEM = "item";
    String TAG_TITLE = "title";
    String TAG_DESCRIPTION = "description";
    String TAG_LINK = "link";
    String TAG_ENCLOSURE = "enclosure";
    String TAG_URL = "url";

    //TAG for intent news between activities
    String INTENT_TAG_NEWS = "INTENT_NEWS";
}
