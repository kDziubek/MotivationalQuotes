package com.example.hp.motivationalquotes;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hp.motivationalquotes.data.QuoteData;
import com.example.hp.motivationalquotes.data.QuoteViewPagerAdapter;

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
        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0 ; i<5; i++){
            QuoteFragment quoteFragment = QuoteFragment.newInstance("Java","daus");
            fragmentList.add(quoteFragment);


        }
        return fragmentList;
    }
}
