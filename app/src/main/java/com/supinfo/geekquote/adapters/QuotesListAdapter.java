package com.supinfo.geekquote.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.supinfo.geekquote.R;
import com.supinfo.geekquote.models.Quote;

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

    private List<Quote> quotes;

    public QuotesListAdapter(List<Quote> quotes) {
        this.quotes = quotes;
    }

    @Override
    public QuoteViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new QuoteViewHolder(
            (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.list_quote_view,
                viewGroup,
                false

            )
        );
    }

    @Override
    public void onBindViewHolder(QuoteViewHolder qvh, int i) {
        TextView textView = (TextView) qvh.getLayout().findViewById(R.id.quote_text);

        textView.setText(quotes.get(i).getStrQuote());
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    public List<Quote> getQuotes() {
        return quotes;
    }
}
