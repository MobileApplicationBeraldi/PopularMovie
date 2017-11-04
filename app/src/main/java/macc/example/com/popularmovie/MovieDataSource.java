package macc.example.com.popularmovie;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieDataSource {
        static private ArrayList<Movie> movies = new ArrayList<Movie>();
        static private RequestQueue queue;
        static public int detailPosition=0;


    static void initializeFromJSON(final JSONArray jsonArray, final MovieAdapter movieAdapter, final Activity activity)
    {
        queue  = Volley.newRequestQueue(activity);
        movies.clear();
        int size = jsonArray.length();
        String title;
        String overview;
        String release_date;
        Double popularity;
        String backdrop_path;
        Movie movie;
        try {
        for (int i = 0; i < size; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            title = jsonObject.getString("title");
            overview = jsonObject.getString("overview");
            release_date = jsonObject.getString("release_date");
            popularity = jsonObject.getDouble("popularity");
            backdrop_path = jsonObject.getString("backdrop_path");
            movie = new Movie(title, overview, release_date, popularity, backdrop_path);
            movies.add(movie);


            String url = new StringBuilder()
                    .append(" http://image.tmdb.org/t/p/w185/")
                    .append(movie.getBackdrop_path()).toString();


            Response.Listener<Bitmap> oklistener = new MyListener(i,movieAdapter);

            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            };
            ImageRequest imageRequest = new ImageRequest(
                    url, oklistener, 100,
                    100, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565, errorListener);

            queue.add(imageRequest);
        }
        }
        catch (JSONException e)
        {

        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                movieAdapter.notifyDataSetChanged();
            }
        });

    }


        static public int size(){
         return movies.size();
     }
        static public Movie getItem(int i){
        return movies.get(i);
    }


}
