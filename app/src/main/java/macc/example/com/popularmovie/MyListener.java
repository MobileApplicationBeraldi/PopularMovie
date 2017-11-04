package macc.example.com.popularmovie;

import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.Response;

import static java.lang.Math.min;

public class MyListener implements Response.Listener<Bitmap> {

    private int id;
    private MovieAdapter movieAdapter;


    MyListener(int id, MovieAdapter movieAdapter) {
        this.id = id;
        this.movieAdapter = movieAdapter;
    }

    @Override
    public void onResponse(Bitmap response) {
        MovieDataSource.getItem(id).setBitMap(response);
        movieAdapter.notifyDataSetChanged();
        Log.i("INFO5:", "" + id);
    }
}