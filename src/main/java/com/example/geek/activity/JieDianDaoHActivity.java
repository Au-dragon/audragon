package com.example.geek.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.example.geek.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

  
public class JieDianDaoHActivity extends AppCompatActivity {
    private static final String TAG = "JieDianDaoHActivity";
    private String Url = "https://www.v2ex.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jie_dian_dao_h);
        initData();
    }

    private void initData() {
       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   Document doc = Jsoup.connect(Url).get();
                   Elements elements = doc.select("div.cell");
                   for (Element element:elements){
                       Elements titles = element.select("table tbody tr td span.fade");
                       if(titles!=null){

                           String title = titles.text();
                           Log.e(TAG, "title: "+ title);
                       }
                       Elements texts = element.select("table tbody tr td > a");
                       if (texts!=null) {
                           String text = texts.text();
                           Log.e(TAG, "text: "+text );
                       }
                   }
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }).start();

    }
}
