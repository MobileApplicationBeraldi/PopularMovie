package macc.example.com.popularmovie;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.Math.min;

public class MovieAsyncLoader extends AsyncTaskLoader<String>{


    public MovieAsyncLoader(Context context){
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad(); // Starts the loadInBackground method
    }
    @Override
    public String loadInBackground() {
        Log.i("INFO:","Starting loading from the net");
        // Set up variables for the try block that need to be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String moviesJSONString = null;
        try {
            Uri builtURI = Uri.parse("https://api.themoviedb.org/3/discover/movie?").buildUpon()
                    .appendQueryParameter("api_key", Config.API_KEY)
                    .appendQueryParameter("language", "it")
                    .appendQueryParameter("sort_by", "popularity.desc")
                    .appendQueryParameter("include_adult", "false")
                    .appendQueryParameter("include_video", "false")
                    .appendQueryParameter("page", "1")
                    .build();

            URL requestURL = new URL(builtURI.toString());

            // Open the network connection.
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Get the InputStream.
            InputStream inputStream = urlConnection.getInputStream();

            // Read the response string into a StringBuilder.
            StringBuilder builder = new StringBuilder();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // but it does make debugging a *lot* easier if you print out the completed buffer for debugging.
                builder.append(line + "\n");
            }

            if (builder.length() == 0) {
                // Stream was empty.  No point in parsing.
                // return null;
                return null;
            }
            moviesJSONString = builder.toString();

            // Catch errors.
        } catch (IOException e) {
            e.printStackTrace();

            // Close the connections.
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        // Return the raw response.
        return moviesJSONString;
    }
}