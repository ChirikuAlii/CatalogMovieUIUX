package id.chirikualii.favoritemovie;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecylerViewMovieAdapter extends RecyclerView.Adapter<RecylerViewMovieAdapter.Holder> {

    List<FavoriteMovie> favoriteMovieList ;

    public RecylerViewMovieAdapter(List<FavoriteMovie> favoriteMovieList) {
        this.favoriteMovieList = favoriteMovieList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_favorite_movie,viewGroup,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(favoriteMovieList.get(position));
    }

    @Override
    public int getItemCount() {
        return favoriteMovieList.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        @BindView(R.id.txt_title_movie)
        TextView txtTitleMovie;

        @BindView(R.id.txt_overview)
        TextView txtOverview;

        @BindView(R.id.txt_release_date)
        TextView txtReleaseDate;

        @BindView(R.id.img_poster)
        ImageView imgPoster;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(FavoriteMovie favoriteMovie) {
            //notifyDataSetChanged();
            txtTitleMovie.setText(favoriteMovie.getOriginalTitle());
            txtOverview.setText(favoriteMovie.getOverview());
            txtReleaseDate.setText(favoriteMovie.getReleaseDate());

            Glide
                    .with(itemView)
                    .load("http://image.tmdb.org/t/p/w185"+ favoriteMovie.getUrlImagePoster())
                    .into(imgPoster);
        }
    }
}
