package com.supinfo.geekquote.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.supinfo.geekquote.R;
import com.supinfo.geekquote.models.Quote;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexis on 01/12/14.
 */
public class QuoteListFragment extends Fragment {

    private final List<Quote> quotes = new ArrayList<Quote>();
    private LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quote_list, container, false);
        this.linearLayout = (LinearLayout) view.findViewById(R.id.base_list_layout);

        for (String q : getResources().getStringArray(R.array.quotes)) {
            addQuote(q);
        }

        return view;
    }

    private void addQuote(final String q) {
        quotes.add(new Quote(q));

        TextView textView = new TextView(getActivity());
        textView.setText(q);

        int padding = getResources().getDimensionPixelSize(R.dimen.quote_text_padding);

        textView.setPadding(padding, padding, padding, padding);

        if(quotes.size() % 2 == 0) {
            textView.setBackgroundColor(
                getResources().getColor(R.color.material_light_blue_50)
            );
        }

        this.linearLayout.addView(textView);
    }
}
