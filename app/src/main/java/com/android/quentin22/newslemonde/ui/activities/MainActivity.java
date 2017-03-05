package com.android.quentin22.newslemonde.ui.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.quentin22.newslemonde.R;
import com.android.quentin22.newslemonde.model.News;
import com.android.quentin22.newslemonde.model.adapters.ItemNewsAdapter;
import com.android.quentin22.newslemonde.model.util.Constants;
import com.android.quentin22.newslemonde.model.util.XmlParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Main activity
 *
 * @author Quentin22
 */
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    public ArrayAdapter<News> mAdapter;
    private List<News> newsList = new ArrayList<>();
    private ListView mNewsListView;
    private SwipeRefreshLayout mNewsSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrieveViews();

        mAdapter = new ItemNewsAdapter(MainActivity.this, newsList);
        mNewsListView.setAdapter(mAdapter);
        mNewsListView.setOnItemClickListener(this);
        mNewsSwipeRefreshLayout.setOnRefreshListener(this);
        mNewsSwipeRefreshLayout.setColorSchemeResources(R.color.grey);

        refreshNews();
    }

    private void retrieveViews() {
        mNewsListView = (ListView) findViewById(R.id.listview_news);
        mNewsSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout_news);
    }

    private void refreshNews() {
        AsyncGetAndParseXml asyncGetAndParseXml = new AsyncGetAndParseXml();
        asyncGetAndParseXml.execute(Constants.URL_LEMONDE_RSS);
    }

    private void fillNewsList(List<News> newsList) {
        if (newsList != null) {
            this.newsList = newsList;
            mAdapter.clear();
            mAdapter.addAll(newsList);
        } else {
            Snackbar.make(findViewById(R.id.relativelayout_activity_main), R.string.error_refresh_news, Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onRefresh() {
        refreshNews();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news = (News) mNewsListView.getItemAtPosition(position);

        Intent intent = new Intent(this, DetailsNewsActivity.class);
        intent.putExtra(Constants.INTENT_TAG_NEWS, news);
        startActivity(intent);
    }

    /***************** Async request *****************/
    /**
     * Async task to get XML
     */
    private class AsyncGetAndParseXml extends AsyncTask<String, Void, List<News>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mNewsSwipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected List<News> doInBackground(String... params) {
            return XmlParser.retrieveNews(params[0]);
        }

        @Override
        protected void onPostExecute(List<News> result) {
            super.onPostExecute(result);
            fillNewsList(result);
            mNewsSwipeRefreshLayout.setRefreshing(false);
        }
    }
}