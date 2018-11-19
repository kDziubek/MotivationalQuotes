package com.example.hp.motivationalquotes;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hp.motivationalquotes.data.QuoteData;
import com.example.hp.motivationalquotes.data.QuoteViewPagerAdapter;
import com.example.hp.motivationalquotes.model.Quote;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    private QuoteViewPagerAdapter quoteViewPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // quoteViewPagerAdapter connected to viewPager adapter.
        // getSupportFragmentManager method from fragment activity(default class)
        // get fragment created bellow
        // new object from QuoteFragment class created and newInstance method called

        quoteViewPagerAdapter = new QuoteViewPagerAdapter(getSupportFragmentManager(), getFragments());

        viewPager = findViewById(R.id.viepager);
        viewPager.setAdapter(quoteViewPagerAdapter);


    }


    private List<Fragment> getFragments() {
        final List<Fragment> fragmentList = new ArrayList<>();
        new QuoteData().getQuotes(new QuoteListAsycnRespone() {
            @Override
            public void processFinished(ArrayList<Quote> quotes) {
                for (int i = 0; i < quotes.size(); i++) {
                    QuoteFragment quoteFragment = QuoteFragment.newInstance(
                            quotes.get(i).getQuote(),
                            quotes.get(i).getAuthor()
                    );
                    fragmentList.add(quoteFragment);

                }
                quoteViewPagerAdapter.notifyDataSetChanged();/// very important!!

            }
        });

        return fragmentList;
    }




//        Random rand = new Random();
//            int randomIndex = rand.nextInt(list.size());
//            return randomIndex;




}

