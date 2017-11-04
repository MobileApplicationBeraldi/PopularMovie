package macc.example.com.popularmovie;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;

public class MovieDetails extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moviedetails);
        Movie movie = MovieDataSource.getItem(MovieDataSource.detailPosition);
        ((ImageView)findViewById(R.id.imageView2)).setImageBitmap(movie.getBitMap());
        ((TextView)findViewById(R.id.titleDetail)).setText(movie.getTitle());
        ((TextView)findViewById(R.id.overviewDetail)).setText(movie.getOverview());
        ((TextView)findViewById(R.id.release_dateDetail)).setText(movie.getRelease_date());
    }
}
