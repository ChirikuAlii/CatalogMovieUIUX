package id.chirikualii.catalogmovieuiux.presentasion.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.chirikualii.catalogmovieuiux.R;
import id.chirikualii.catalogmovieuiux.presentasion.model.Movie;

public class RecyclerViewMovieAdapter extends RecyclerView.Adapter<RecyclerViewMovieAdapter.Holder> {
    OnItemClick<Movie> onItemClick;
    public RecyclerViewMovieAdapter(List<Movie> movieList, String key, OnItemClick<Movie> onItemClick) {
        this.movieList = movieList;
        this.key = key;
        this.onItemClick = onItemClick;
    }
    private String key;
    private List<Movie> movieList;

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder viewHolder, int position) {
        viewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }



    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_poster) ImageView imgPoster;
        @BindView(R.id.txt_title_movie) TextView txtTitle;
        @BindView(R.id.txt_overview) TextView txtOverview;
        @BindView(R.id.txt_release_date) TextView txtReleaseDate;
        @BindView(R.id.btn_detail_movie)Button btnDetail;
        @BindView(R.id.btn_share_movie)Button btnShare;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(int position) {
            txtTitle.setText(movieList.get(position).getOriginalTitle());
            txtOverview.setText(movieList.get(position).getOverview());
            txtReleaseDate.setText(movieList.get(position).getReleaseDate());

            Glide
                    .with(itemView)
                    .load("http://image.tmdb.org/t/p/w185"+ movieList.get(position).getUrlImagePoster())
                    .into(imgPoster);


            btnDetail.setOnClickListener(view -> {
                onItemClick.onItemClick(movieList.get(position));
            });

        }
    }
}
