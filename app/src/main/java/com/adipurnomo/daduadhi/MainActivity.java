package com.adipurnomo.daduadhi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int[] diceArray = {
            R.drawable.dadu1,
            R.drawable.dadu2,
            R.drawable.dadu3,
            R.drawable.dadu4,
            R.drawable.dadu5,
            R.drawable.dadu6
    };
    ImageView leftDice,rightDice;

    //iklan
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private boolean loadingIklan=true;
    private Integer hitung=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //iklan
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest1);

        Button rollButton;
        rollButton = findViewById(R.id.rollButton);
        rollButton.setOnClickListener(this);
        leftDice = findViewById(R.id.image_leftDice);
        rightDice = findViewById(R.id.image_rightDice);

    }
    public void loadInterstitial() {
        hitung++;
        if (loadingIklan){
            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId("ca-app-pub-5389072248308971/9375633626");
            AdRequest adRequest = new AdRequest.Builder()
                    .setRequestAgent("android_studio:ad_template").build();
            mInterstitialAd.loadAd(adRequest);
            loadingIklan=false;
        }
        if (hitung%5==0){
            if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
                loadingIklan=true;
            }
        }
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Yeyeye", Toast.LENGTH_SHORT).show();
        Random randomGenerate = new Random();
        int leftDiceRand = randomGenerate.nextInt(6);
        leftDice.setImageResource(diceArray[leftDiceRand]);
        int rightDiceRand = randomGenerate.nextInt(6);
        rightDice.setImageResource(diceArray[rightDiceRand]);
        loadInterstitial();
    }
}
