package macc.example.com.popularmovie;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    private MovieAdapter movieAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLoaderManager().initLoader(0, null, this);
        movieAdapter = new MovieAdapter();
        LinearLayoutManager lm = new LinearLayoutManager(this);
        RecyclerView rv = findViewById(R.id.rv);
        rv.setAdapter(movieAdapter);
        rv.setLayoutManager(lm);
    }


    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {
        return new MovieAsyncLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String s) {
        //Log.i("INFO",s);
        //Log.i("INFO:","onLoadFinished"+jsonReply);

        try {
            JSONObject jsonObject= new JSONObject(s);
            JSONArray jsonMovies = jsonObject.getJSONArray("results");
            //Log.i("INFO:","JSON Results:"+jsonMovies);
            MovieDataSource.initializeFromJSON(jsonMovies,movieAdapter,this);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
