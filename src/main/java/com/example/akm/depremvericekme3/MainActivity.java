package com.example.akm.depremvericekme3;

import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity {

    List<String> depremList=new ArrayList<String>();
    List<String> denemeList=new ArrayList<String>();
    ImageView icon;
    ListView liste ;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        liste=(ListView)findViewById(R.id.liste);
        icon=(ImageView) findViewById(R.id.imageView);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); //bağlantı izinleri
        StrictMode.setThreadPolicy(policy);
        runOnUiThread(new Runnable() { //run time da ekstra tred ile bağlantı kurulur androidte
            @Override
            public void run() {
                try  {
                    Connection.Response response = Jsoup.connect("http://koeri.boun.edu.tr/scripts/lst8.asp").timeout(30000).execute();
                    Document doc = response.parse();
                    Elements pres = doc.select("pre");

                    String veriParcala [];
                    for (Element pre : pres) {
                        String veriler=pres.text();
                        veriParcala=veriler.split("\\r?\\n");

                        for (int i = 6; i < veriParcala.length; i++) {  //6. satıra kadar tanım vs var...
                            depremList.add(veriParcala[i]);
                            String tmp=veriParcala[i].substring(59);
                            denemeList.add(tmp);
                        }
                    }

                    Toast.makeText(getApplicationContext(),"veriler geldi",Toast.LENGTH_SHORT).show();
                    icon.setVisibility(View.INVISIBLE);
                }  catch (Exception e){
                    Log.d("Hata", e.toString());
                    Toast.makeText(getApplicationContext(),"hata: "+e.getMessage(),Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),"Veri Bağlantınızı Kontrol Edin!",Toast.LENGTH_LONG).show();
                }


            }
        });


        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                Toast.makeText(getApplicationContext(),depremList.get(position),Toast.LENGTH_LONG).show();
                        String enlem=depremList.get(position).substring(21,27);
                        String boylam=depremList.get(position).substring(31,37);
                String haritaAdres="http://maps.google.com/maps?q=loc:"+enlem+","+boylam+"";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(haritaAdres));
               // startActivity(i);
                Uri gmmIntentUri = Uri.parse("loc:"+enlem+","+boylam+"?label=" + Uri.encode("Deprem Alanı"));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
                else{startActivity(i);}
            }

        });



        try {



            liste.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, denemeList));
        }catch (Exception a){}


    }
}
