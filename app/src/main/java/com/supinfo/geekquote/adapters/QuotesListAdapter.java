package com.supinfo.geekquote.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.supinfo.geekquote.R;
import com.supinfo.geekquote.models.Quote;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexis on 02/12/14.
 */
public class QuotesListAdapter extends RecyclerView.Adapter<QuotesListAdapter.QuoteViewHolder> {

    public static class QuoteViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;

        public QuoteViewHolder(LinearLayout layout) {
            super(layout);
            this.layout = layout;
        }

        public LinearLayout getLayout() {
            return layout;
        }
    }

    private LinkedList<Quote> quotes;
    private Activity context;

    public QuotesListAdapter(LinkedList<Quote> quotes, Activity context) {
        this.quotes = quotes;
        this.context = context;
    }

    @Override
    public QuoteViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        LinearLayout ll = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(
            R.layout.list_quote_view,
            viewGroup,
            false
        );

        return new QuoteViewHolder(ll);
    }

    @Override
    public void onBindViewHolder(QuoteViewHolder qvh, final int i) {
        TextView textView = (TextView) qvh.getLayout().findViewById(R.id.quote_text);

        textView.setClickable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("geekquote", "toto");
                Toast.makeText(context, quotes.get(i).getStrQuote(), Toast.LENGTH_LONG);
            }
        });

        textView.setText(quotes.get(i).getStrQuote());
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    public LinkedList<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(LinkedList<Quote> quotes) {
        this.quotes = quotes;
    }
}
