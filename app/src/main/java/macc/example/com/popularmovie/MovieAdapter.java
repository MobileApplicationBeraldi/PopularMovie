package macc.example.com.popularmovie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder>{


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater  =
                (LayoutInflater)
                        parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.movie,parent,false);
        MovieViewHolder vh = new MovieViewHolder(v,parent.getContext());
        Log.i("INFO2:","call of onCreateViewHolder");
        return vh;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = MovieDataSource.getItem(position);
        holder.titleTextView.setText(movie.getTitle());
        holder.imageViewView.setImageBitmap(movie.getBitMap());
        holder.overviewTextView.setText(movie.getShortOverview());
        holder.release_dateTextView.setText(movie.getRelease_date());
        holder.popularityTextView.setText(movie.getPopularity().toString().substring(0,8));

        Log.i("INFO2: ","call of onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return MovieDataSource.size();

    }
}