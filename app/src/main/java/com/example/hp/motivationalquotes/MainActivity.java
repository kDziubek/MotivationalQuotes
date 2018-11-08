package com.example.hp.motivationalquotes;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hp.motivationalquotes.data.QuoteData;
import com.example.hp.motivationalquotes.data.QuoteViewPagerAdapter;
import com.example.hp.motivationalquotes.model.Quote;

import java.util.ArrayList;
import java.util.List;

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
        quoteViewPagerAdapter = new QuoteViewPagerAdapter(getSupportFragmentManager(),getFragment());

        viewPager = findViewById(R.id.viepager);
        viewPager.setAdapter(quoteViewPagerAdapter);


    }

    private List<Fragment>getFragment(){
        final List<Fragment> fragmentList = new ArrayList<>();
        new QuoteData().getQuotes(new QuoteListAsycnRespone() {
            @Override
            public void processFinished(ArrayList<Quote> quotes) {
                for (int i = 0 ; i<quotes.size(); i++) {
                    QuoteFragment quoteFragment = QuoteFragment.newInstance(quotes.get(i).getFrom(), quotes.get(i).getQuote());
                    fragmentList.add(quoteFragment);
                }
                //very important.
                quoteViewPagerAdapter.notifyDataSetChanged();
            }
        });
        return fragmentList;
    }
}

