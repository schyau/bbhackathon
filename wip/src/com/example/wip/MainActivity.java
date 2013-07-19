package com.example.wip;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import com.example.wip.yelpstuff.Business;
import com.example.wip.yelpstuff.Location;
import com.example.wip.yelpstuff.YelpSearchResult;
import com.example.wip.yelpstuff.YelpService;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity implements ResultsActivity 
{
  public MainActivity mThis;
  GoogleMap mMap;
  @Override
  protected void onCreate (Bundle b)
  {
    super.onCreate( b );
    setContentView( R.layout.main );
    setUpMapIfNeeded();
    mThis = this;
  }
  public boolean onCreateOptionsMenu(Menu menu)
  {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.activity_main, menu);
    SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
    searchView.setOnQueryTextListener( new OnQueryTextListener(){

      @Override
      public boolean onQueryTextChange( String newText )
      {
        return false;
      }

      @Override
      public boolean onQueryTextSubmit( String query )
      {
        MyAsyncTask mat = new MyAsyncTask();
        mat._listener = mThis;
        mat._query = query;
        return true;
      }
    });
    return super.onCreateOptionsMenu(menu);
  }
  
  private class MyAsyncTask extends AsyncTask<Void,Void,String> {
    public YelpSearchResult _results;
    public ResultsActivity _listener;
    public String _query;
    @Override
    protected String doInBackground( Void... params )
    {
      _results = YelpService.search( 37.796949, -122.402485, _query );
      return _query;
    }
    @Override
    protected void onPostExecute(String result){
      _listener.onResultsSucceeded( _results );
    }
    
  }
  
  public void onResume () 
  {
    super.onResume();
    mMap.addMarker(new MarkerOptions().title( "Blackboard!" ).position( new LatLng(37.796949, -122.402485 ) ));
  }
  
  @Override
  public void onResultsSucceeded( YelpSearchResult results )
  {
    setUpMapIfNeeded();
    List<Business> listOfBusinesses = results.getBusinesses();
    for ( Business bidness : listOfBusinesses ){
      Location loc = bidness.getLocation();
      String name = bidness.getName();
      String locCity = loc.getCity();
      String locAdd = loc.getAddress().get( 0 );
      String locState = loc.getStateCode();
      String locZip = loc.getPostalCode();
      String fullAddress = locAdd + ", " + locCity + ", " + locState + ", " + locZip;
      
      //mMap.addMarker(new MarkerOptions().position( new LatLng() ));
    }
  }
  private void setUpMapIfNeeded() {
    // Do a null check to confirm that we have not already instantiated the map.
    if (mMap == null) {
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                            .getMap();
        // Check if we were successful in obtaining the map.
        if (mMap != null) {
            // The Map is verified. It is now safe to manipulate the map.

        }
    }
}
}