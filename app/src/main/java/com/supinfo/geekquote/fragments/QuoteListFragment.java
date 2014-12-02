package com.supinfo.geekquote.fragments;

import android.app.Fragment;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.supinfo.geekquote.R;
import com.supinfo.geekquote.adapters.QuotesListAdapter;
import com.supinfo.geekquote.listeners.AddQuoteListener;
import com.supinfo.geekquote.models.Quote;
import com.supinfo.geekquote.outlines.RoundButtonOutline;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexis on 01/12/14.
 */
public class QuoteListFragment extends Fragment {

    private final LinkedList<Quote> quotes = new LinkedList<Quote>();
    private LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quote_list, container, false);
        this.linearLayout = (LinearLayout) view.findViewById(R.id.base_list_layout);

        for (String q : getResources().getStringArray(R.array.quotes)) {
            addQuote(q);
        }

        RecyclerView quotesRecycler = (RecyclerView) linearLayout.findViewById(R.id.quotes_recycler_view);
        quotesRecycler.setHasFixedSize(true);
        quotesRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        quotesRecycler.setItemAnimator(new DefaultItemAnimator());

        QuotesListAdapter quotesListAdapter = new QuotesListAdapter(this.quotes);
        quotesRecycler.setAdapter(quotesListAdapter);

        Button addQuoteButton = (Button) linearLayout.findViewById(R.id.add_quote_button);
        addQuoteButton.setOnClickListener(new AddQuoteListener(this, view, quotesListAdapter));

        int fabSize = getResources().getDimensionPixelSize(R.dimen.fab_button_size);
        Button fabButton = (Button) view.findViewById(R.id.fab_button);
        fabButton.setOutlineProvider(new RoundButtonOutline(fabSize));
        fabButton.setClipToOutline(true);

        Log.d("geekquote", "Elevation : " + fabButton.getElevation());
        System.out.println("Elevation : " + fabButton.getElevation());

        return view;
    }

    private void addQuote(final String q) {
        quotes.add(new Quote(q));
    }
}
