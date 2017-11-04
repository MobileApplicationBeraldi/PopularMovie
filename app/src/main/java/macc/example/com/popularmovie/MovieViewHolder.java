package macc.example.com.popularmovie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView titleTextView;
    TextView overviewTextView;
    TextView release_dateTextView;
    TextView popularityTextView;
    ImageView imageViewView;
    Context context;


    public MovieViewHolder(View itemView,Context context) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.title);
        overviewTextView = itemView.findViewById(R.id.overview);
        release_dateTextView = itemView.findViewById(R.id.release_date);
        popularityTextView = itemView.findViewById(R.id.popularity);
        imageViewView = itemView.findViewById(R.id.imageView);
        this.context=context;
        itemView.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        MovieDataSource.detailPosition=getAdapterPosition();
        Intent intent = new Intent(context,MovieDetails.class);
        context.startActivity(intent);
        //Log.i("INFO6:",""+getAdapterPosition());
    }


}