package com.example.andrei.catalog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrei on 7/4/2017.
 */

public class CustomAdapter extends ArrayAdapter<Card> {

    Context context;
    CacheDatabase cacheDB;
    private ArrayList<Card> cardList;

    private final String WEBAPPURL = "http://10.0.2.2:8080/catalog-webapp";

    public CustomAdapter(Context context, int textViewResourceId,
                         ArrayList<Card> list) {
        super(context, textViewResourceId, list);
        this.context = context;
        this.cardList = new ArrayList<Card>();
        this.cardList.addAll(list);
        this.cacheDB = new CacheDatabase(context);
    }

    private class ViewHolder {
        TextView title;
        TextView description;
        TextView season;
        ImageView thumbnail;
    }

    public void add(Card card){
        this.cardList.add(card);
    }

    public void updateAdapter(ArrayList<Card> newlist) {
        this.cardList.clear();
        this.cardList.addAll(newlist);

        //and call notifyDataSetChanged
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.list_item_view, null);

            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.card_list_title);
            holder.description = (TextView) convertView.findViewById(R.id.card_list_description);
            holder.season = (TextView) convertView.findViewById(R.id.card_list_season);
            holder.thumbnail = (ImageView) convertView.findViewById(R.id.card_list_thumbnail);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Card card = this.cardList.get(position);
        holder.title.setText(card.getTitle());
        holder.description.setText(card.getDescription());
        holder.season.setText(card.getSeason());

        Bitmap thumbnailImage = cacheDB.get(card.getCardId().toString());
        if(thumbnailImage != null) {
            holder.thumbnail.setImageBitmap(thumbnailImage);
        }
        else {
            holder.thumbnail.setImageResource(R.mipmap.ic_launcher);
            new BitmapDownloaderTask(holder.thumbnail).execute(card.getCardId().toString(), WEBAPPURL + card.getThumbnailUrl());
        }

        return convertView;
    }

    private class BitmapDownloaderTask extends AsyncTask<String, Void, Bitmap> {

        private String id, url;
        Bitmap image;
        private final ImageView imageViewReference;

        public BitmapDownloaderTask(ImageView imageView) {
            imageViewReference = imageView;
        }

        @Override
        protected Bitmap doInBackground (String... args) {
            id = args[0];
            url = args[1];
            try{
                image = BitmapFactory.decodeStream(new URL(url).openConnection().getInputStream());
                return image;
            }
            catch(Exception e){
                Log.e("Error", e.getMessage()); e.printStackTrace();}
            return null;
        }


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap != null) {
                cacheDB.insert(id, bitmap);
                imageViewReference.setImageBitmap(bitmap);
            }
        }
    }
}