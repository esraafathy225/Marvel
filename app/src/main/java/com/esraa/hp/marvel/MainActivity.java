package com.esraa.hp.marvel;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
ArrayList<MarvelCharacter> characters;
RecyclerView recyclerView;
URL url;
HttpURLConnection urlConnection;
InputStream inputStream;
String result;
StringBuffer buffer1;
String finalResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler);

        characters=new ArrayList<>();
       new JSONTask().execute();
    }
    public class JSONTask extends AsyncTask<String,String,ArrayList<MarvelCharacter>> {

        @Override
        protected ArrayList<MarvelCharacter> doInBackground(String... strings) {
            try {
                url=new URL("https://www.simplifiedcoding.net/demos/marvel/");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                urlConnection= (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                inputStream=urlConnection.getInputStream();
                int c=0;
                StringBuffer buffer=new StringBuffer();
                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    while ((c = inputStream.read()) != -1) {
                        buffer.append((char) c);
                    }
                }
                result=buffer.toString();
                buffer1=new StringBuffer();
                JSONArray array=new JSONArray(result);
                for(int i=0;i<array.length();i++) {
                    JSONObject object = array.getJSONObject(i);
                    String name = object.getString("name");
                    String team = object.getString("team");
                    String bio=object.getString("bio");
                    String imgUrl=object.getString("imageurl");
                    characters.add(new MarvelCharacter(imgUrl,name,team,bio.trim()));
                }
                finalResult=buffer1.toString();
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return characters;
        }

        @Override
        protected void onPostExecute(ArrayList<MarvelCharacter> characters) {
            super.onPostExecute(characters);
            CharacterAdapter adapter=new CharacterAdapter(characters,MainActivity.this);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            recyclerView.setAdapter(adapter);
        }
    }
}
