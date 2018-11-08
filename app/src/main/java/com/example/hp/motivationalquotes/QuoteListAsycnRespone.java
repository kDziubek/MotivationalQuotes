package com.example.hp.motivationalquotes;

import com.example.hp.motivationalquotes.model.Quote;

import java.util.ArrayList;

public interface QuoteListAsycnRespone {
    void processFinished(ArrayList<Quote> quotes);
}
