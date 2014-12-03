package com.supinfo.geekquote.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.supinfo.geekquote.R;
import com.supinfo.geekquote.adapters.QuotesListAdapter;
import com.supinfo.geekquote.listeners.AddQuoteListener;
import com.supinfo.geekquote.models.Quote;
import com.supinfo.geekquote.tasks.ListQuotesTask;

import java.util.LinkedList;

/**
 * Created by alexis on 01/12/14.
 */
public class QuoteListFragment extends Fragment {

    private LinkedList<Quote> quotes = new LinkedList<Quote>();
    private LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quote_list, container, false);
        this.linearLayout = (LinearLayout) view.findViewById(R.id.base_list_layout);


        RecyclerView quotesRecycler = (RecyclerView) linearLayout.findViewById(R.id.quotes_recycler_view);
        quotesRecycler.setHasFixedSize(true);
        quotesRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        quotesRecycler.setItemAnimator(new DefaultItemAnimator());

        QuotesListAdapter quotesListAdapter = new QuotesListAdapter(this.quotes, getActivity());
        quotesRecycler.setAdapter(quotesListAdapter);

        new ListQuotesTask(quotesListAdapter)
                .execute("http://geekquote.cleverapps.io/rest/quotes");

        Button addQuoteButton = (Button) linearLayout.findViewById(R.id.add_quote_button);
        addQuoteButton.setOnClickListener(new AddQuoteListener(this, view, quotesListAdapter));

        return view;
    }

    private void addQuote(final String q) {
        quotes.add(new Quote(q));
    }

    public LinkedList<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(LinkedList<Quote> quotes) {
        this.quotes = quotes;
    }
}
