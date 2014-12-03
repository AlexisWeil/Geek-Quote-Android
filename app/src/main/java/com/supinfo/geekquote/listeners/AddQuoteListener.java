package com.supinfo.geekquote.listeners;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.supinfo.geekquote.R;
import com.supinfo.geekquote.adapters.QuotesListAdapter;
import com.supinfo.geekquote.fragments.QuoteListFragment;
import com.supinfo.geekquote.models.Quote;
import com.supinfo.geekquote.tasks.AddQuoteTask;

import java.util.LinkedList;

/**
 * Created by alexis on 02/12/14.
 */
public class AddQuoteListener implements Button.OnClickListener {

    private QuoteListFragment quoteListFragment;
    private EditText quoteEditText;
    private QuotesListAdapter quotesListAdapter;

    public AddQuoteListener(QuoteListFragment quoteListFragment, View root, QuotesListAdapter quotesListAdapter) {
        this.quoteListFragment = quoteListFragment;

        this.quoteEditText = (EditText) root.findViewById(R.id.add_quote_content);
        this.quotesListAdapter = quotesListAdapter;
    }

    @Override
    public void onClick(View view) {
        String strQuote = this.quoteEditText.getText().toString();

        Quote q = new Quote(strQuote);
        q.setRating(3);

        new AddQuoteTask(quotesListAdapter).execute(
            "http://geekquote.cleverapps.io/rest/quotes",
            q
        );
    }

}
