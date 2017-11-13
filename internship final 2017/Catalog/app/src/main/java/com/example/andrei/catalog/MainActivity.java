package com.example.andrei.catalog;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private final String SERVICEURL = "http://10.0.2.2:8080/catalog-service/cards/all";

    private ListView cardListView;

    ArrayList<Card> cardList;
    CustomAdapter myAdapter = null;
    int start = 0;
    int size = 6;
    boolean loadingMore = false;
    View loadingView;

    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_SMS, Manifest.permission.CAMERA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardListView = (ListView) findViewById(R.id.card_list_view);

        loadingView = ((LayoutInflater)this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.loading_view, null, false);
        cardListView.addFooterView(loadingView);

        //create an ArrayAdaptar from the String Array
        cardList = new ArrayList<Card>();

        //cardListView.setAdapter(myAdapter);

        cardListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Card selectedCard = cardList.get(position);

                Intent detailIntent = new Intent(getApplicationContext(), CardDetailActivity.class);
                detailIntent.putExtra("cardId", selectedCard.getCardId().toString());
                startActivity(detailIntent);
            }

        });

        cardListView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                int lastInScreen = firstVisibleItem + visibleItemCount;
                if((lastInScreen == totalItemCount-1) && !(loadingMore)){
                    String dataUrl = SERVICEURL +
                            "?" + "start=" + start + "&size=" + visibleItemCount;
                    Log.d(TAG, dataUrl);
                    new getData().execute(dataUrl);
                    start += visibleItemCount;
                }
            }
        });

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        String dataUrl = SERVICEURL + "?" + "start=" + start + "&size=" + size;
        start += size;
        new getData().execute(dataUrl);
    }

    public static boolean hasPermissions(Context context, String... permissions)
    {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null)
        {
            for (String permission : permissions)
            {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                {
                    return false;
                }
            }
        }
        return true;
    }
    private class getData extends AsyncTask<String, Void, String> {

        HttpURLConnection urlConnection = null;
        private boolean error = false;
        private ProgressDialog dialog =
                new ProgressDialog(MainActivity.this);

        protected void onPreExecute() {
            dialog.setMessage("Getting your data... Please wait...");
            dialog.show();
        }

        protected String doInBackground(String... args) {

            loadingMore = true;
            StringBuilder result = new StringBuilder();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                URL url = new URL(args[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                urlConnection.disconnect();
            }

            return result.toString();
        }

        protected void onCancelled() {
            dialog.dismiss();
            Toast toast = Toast.makeText(MainActivity.this,
                    "Error connecting to Server", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 25, 400);
            toast.show();
        }

        protected void onPostExecute(String content) {
            dialog.dismiss();
            Toast toast;
            if (error) {
                toast = Toast.makeText(MainActivity.this,
                        content, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 25, 400);
                toast.show();
            } else {
                populateCardList(content);
            }
        }


        private void populateCardList(String response) {

            Log.d(TAG, response);

            JSONObject responseObj = null;

            try {
                Gson gson = new Gson();
                JSONArray cardListArr = new JSONArray(response);

                if(cardListArr.length() == 0){
                    ListView listView = (ListView) findViewById(R.id.card_list_view);
                    listView.removeFooterView(loadingView);
                    Toast toast = Toast.makeText(MainActivity.this,
                            "end of list", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM, 10, 0);
                    toast.show();
                }
                else {
                    for (int i=0; i<cardListArr.length(); i++) {
                        //get the card information JSON object
                        String cardInfo = cardListArr.getJSONObject(i).toString();
                        //create java object from the JSON object
                        Card card = gson.fromJson(cardInfo, Card.class);
                        //add to country array list
                        cardList.add(card);
                    }

                    //myAdapter.notifyDataSetChanged();
                    if(myAdapter == null) {
                        myAdapter = new CustomAdapter(getApplicationContext(),
                                R.layout.list_item_view, cardList);
                        cardListView.setAdapter(myAdapter);
                        myAdapter.updateAdapter(cardList);
                    } else {
                        myAdapter.updateAdapter(cardList);
                    }
                    loadingMore = false;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}
