package com.supinfo.geekquote.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.supinfo.geekquote.adapters.QuotesListAdapter;
import com.supinfo.geekquote.models.Quote;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 * Created by alexis on 03/12/14.
 */
public class AddQuoteTask extends AsyncTask<Object, Void, Quote> {

    private QuotesListAdapter quotesListAdapter;

    public AddQuoteTask(QuotesListAdapter quotesListAdapter) {
        this.quotesListAdapter = quotesListAdapter;
    }

    @Override
    protected Quote doInBackground(Object... objects) {
        final String url = (String) objects[0];
        final Quote quote = (Quote) objects[1];

        HttpClient client = new DefaultHttpClient();
        HttpPost req = new HttpPost(url);
        req.setHeader("Content-Type", "application/json");

        try {
            JSONObject json = new JSONObject();
            json.put("content", quote.getStrQuote());
            json.put("rating", quote.getRating());

            req.setEntity(new StringEntity(json.toString()));

            HttpEntity entity = client.execute(req).getEntity();

            Log.d("GeekQuote", "Add Quote resp : " + EntityUtils.toString(entity));
        }
        catch(Exception e) {
            Log.e("GeekQuote", "Error when trying to send the quote");
        }

        return quote;
    }

    @Override
    protected void onPostExecute(Quote quote) {
        quotesListAdapter.getQuotes().addFirst(quote);
        quotesListAdapter.notifyItemInserted(0);
    }
}
