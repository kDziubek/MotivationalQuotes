package com.example.hp.motivationalquotes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.motivationalquotes.model.Quote;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends Fragment {


    public QuoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View quoteView = inflater.inflate(R.layout.fragment_quote,container,false);

        TextView quoteText = quoteView.findViewById(R.id.quoteText);
        TextView byAuthor = quoteView.findViewById(R.id.byAuthor);
        CardView cardView = quoteView.findViewById(R.id.cardview);
        String quote = getArguments().getString("quote");
        String author = getArguments().getString("author");

        quoteText.setText(quote);
        byAuthor.setText("-" + author);

        return quoteView;
    }

    //method to create fragment

   public static final QuoteFragment newInstance(String quote, String author){

        QuoteFragment fragment  = new QuoteFragment();
        Bundle bundle = new Bundle();
        bundle.putString("quote",quote);
        bundle.putString("author", author);
        fragment.setArguments(bundle);

        return fragment;
    }

}
