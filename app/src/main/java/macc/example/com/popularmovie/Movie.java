package macc.example.com.popularmovie;

import android.graphics.Bitmap;
import static java.lang.Math.min;

public class Movie {
    String title;
    String overview;
    String release_date;
    Double popularity;
    String backdrop_path;
    Bitmap bitMap;

    Movie(String title, String overview, String release_date, Double popularity,String backdrop_path) {
        this.title = title;
        this.overview = overview;
        this.release_date = release_date;
        this.popularity=popularity;
        this.backdrop_path=backdrop_path;
    }

    String getTitle(){
        return title;
    }

    String getShortOverview(){
        return overview.substring(0,min(30,overview.length()));
    }

    String getOverview(){
        return overview;
    }

    String getBackdrop_path(){return backdrop_path;}

    String getRelease_date(){return release_date;}

    Double getPopularity(){return popularity;}

    Bitmap getBitMap (){
        return bitMap;
    }

    void setBitMap(Bitmap bitMap){
        this.bitMap=bitMap;
    }
}