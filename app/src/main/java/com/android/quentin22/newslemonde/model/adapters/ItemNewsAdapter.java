package com.android.quentin22.newslemonde.model.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.quentin22.newslemonde.R;
import com.android.quentin22.newslemonde.model.News;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adapter for news items
 *
 * @author Quentin22
 */

public class ItemNewsAdapter extends ArrayAdapter<News> {

    private Context mContext;
    private List<News> mNewsList;

    public ItemNewsAdapter(Context context, List<News> newsList) {
        super(context, R.layout.item_news, newsList);
        this.mNewsList = newsList;
        this.mContext = context;
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        News news = mNewsList.get(position);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_news, null);

        ImageView image = (ImageView) view.findViewById(R.id.image_item);
        TextView title = (TextView) view.findViewById(R.id.textview_item_title);

        title.setText(news.getTitle());
        Picasso.with(getContext()).load(news.getImageUrl()).into(image);

        return view;
    }
}
