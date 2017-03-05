package com.android.quentin22.newslemonde.ui.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.quentin22.newslemonde.R;
import com.android.quentin22.newslemonde.model.News;
import com.android.quentin22.newslemonde.model.util.Constants;
import com.squareup.picasso.Picasso;

/**
 * Details of news activity
 *
 * @author Quentin22
 */

public class DetailsNewsActivity extends AppCompatActivity {

    private ImageView mImageImageView;
    private TextView mTitleTextView;
    private TextView mDescriptionTextView;
    private TextView mLinkTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_news);
        retrieveViews();

        Intent intent = getIntent();
        News news = (News) intent.getExtras().getSerializable(Constants.INTENT_TAG_NEWS);
        displayNews(news);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void retrieveViews() {
        mImageImageView = (ImageView) findViewById(R.id.image_item);
        mTitleTextView = (TextView) findViewById(R.id.textview_item_title);
        mDescriptionTextView = (TextView) findViewById(R.id.textview_description);
        mLinkTextView = (TextView) findViewById(R.id.textview_link);
    }

    private void displayNews(News news) {
        if (news == null || news.getImageUrl() == null) {
            finish();
        } else {
            Picasso.with(this).load(news.getImageUrl()).into(mImageImageView);
            mTitleTextView.setText(news.getTitle());
            mDescriptionTextView.setText(news.getDescription());

            String text = "<a href=" + news.getLink() + ">Source Link</a>";
            mLinkTextView.setMovementMethod(LinkMovementMethod.getInstance());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mLinkTextView.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY));
            } else {
                mLinkTextView.setText(Html.fromHtml(text));
            }
        }
    }
}
