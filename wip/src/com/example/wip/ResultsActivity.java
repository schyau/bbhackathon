package com.example.wip;

import com.example.wip.yelpstuff.YelpSearchResult;


/**
 * Activites that wish to be notified about results
 * in onPostExecute of an AsyncTask must implement
 * this interface.
 *
 * This is the basic Observer pattern.
 */
public interface ResultsActivity {
    public void onResultsSucceeded(YelpSearchResult result);
}