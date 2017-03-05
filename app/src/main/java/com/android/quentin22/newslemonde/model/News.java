package com.android.quentin22.newslemonde.model;

import java.io.Serializable;

/**
 * News model
 *
 * @author Quentin22.
 */
public class News implements Serializable {

    private final String imageUrl;
    private final String title;
    private final String description;
    private final String link;

    private News(NewsBuilder builder) {
        this.imageUrl = builder.imageUrl;
        this.title = builder.title;
        this.description = builder.description;
        this.link = builder.link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public static class NewsBuilder {

        private String imageUrl;
        private String title;
        private String description;
        private String link;

        public NewsBuilder() {
        }

        public NewsBuilder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public NewsBuilder title(String title) {
            this.title = title;
            return this;
        }

        public NewsBuilder description(String description) {
            this.description = description;
            return this;
        }


        public NewsBuilder link(String link) {
            this.link = link;
            return this;
        }

        public News build() {
            return new News(this);
        }
    }
}
