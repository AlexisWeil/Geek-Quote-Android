package com.supinfo.geekquote.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.supinfo.geekquote.adapters.QuotesListAdapter;
import com.supinfo.geekquote.fragments.QuoteListFragment;
import com.supinfo.geekquote.models.Quote;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexis on 03/12/14.
 */
public class ListQuotesTask extends AsyncTask<String, Void, LinkedList<Quote>> {

    private QuotesListAdapter quotesListAdapter;

    public ListQuotesTask(QuotesListAdapter quotesListAdapter) {
        this.quotesListAdapter = quotesListAdapter;
    }

    @Override
    protected LinkedList<Quote> doInBackground(String... strings) {
        Log.d("GeekQuote", "Begin of ListQuotesTask");

        LinkedList<Quote> result = new LinkedList<Quote>();

        final String url = strings[0];

        HttpClient client = new DefaultHttpClient();
        HttpGet req = new HttpGet(url);

        try {
            HttpResponse resp = client.execute(req);
            String jsonStr = EntityUtils.toString(resp.getEntity());

            Log.d("GeekQuote", "Quotes : " + jsonStr);

            JSONArray arr = new JSONArray(jsonStr);

            for(int i = 0 ; i < arr.length() ; i++) {
                JSONObject o = arr.getJSONObject(i);
                Quote q = new Quote();
                q.setStrQuote(o.getString("content"));

                result.addFirst(q);
            }

        } catch (Exception e) {
            Log.e("GeekQuote", "Error when trying to retrieve quotes from " + url);
        }

        return result;
    }

    @Override
    protected void onPostExecute(LinkedList<Quote> quotes) {
        Log.d("GeekQuote", "Size : " + quotes.size());
        quotesListAdapter.setQuotes(quotes);
        quotesListAdapter.notifyDataSetChanged();
    }
}
