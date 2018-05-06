package com.vibhor.moviereviewer.adapter;

import android.content.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.support.v4.content.*;
import android.support.v7.graphics.*;
import android.support.v7.widget.*;
import android.text.*;
import android.view.*;
import android.widget.*;

import com.squareup.picasso.*;
import com.vibhor.moviereviewer.R;
import com.vibhor.moviereviewer.model.Movie;
import com.vibhor.moviereviewer.shared.*;

import java.util.*;

import butterknife.*;

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_MOVIE = 1;
    private static final int VIEW_TYPE_FOOTER = 2;

    private List<Movie> data;
    private boolean isFooterVisible = true;

    public MoviesAdapter(List<Movie> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_FOOTER) {
            return new FooterViewHolder(inflater.inflate(R.layout.footer_search_result, parent, false));
        } else {
            return new MovieViewHolder(inflater.inflate(R.layout.item_movie, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MovieViewHolder movieViewHolder;
        try {
            movieViewHolder = (MovieViewHolder) holder;
        } catch (ClassCastException e) {
            return;
        }

        Movie movie = data.get(position);
        Context context = holder.itemView.getContext();

        if (!TextUtils.isEmpty(movie.getTitle())) {
            movieViewHolder.title.setText(movie.getTitle());
        } else {
            movieViewHolder.title.setText(null);
        }

        if (!TextUtils.isEmpty(movie.getByline())) {
            movieViewHolder.reviewer.setText(context.getString(R.string.reviewer, movie.getByline()));
        } else {
            movieViewHolder.reviewer.setText(null);
        }

        if (!TextUtils.isEmpty(movie.getRatings())) {
            movieViewHolder.rating.setText(context.getString(R.string.rating, movie.getRatings()));
        } else {
            movieViewHolder.rating.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(movie.getPubDate())) {
            movieViewHolder.pubDate.setText(movie.getPubDate());
        } else {
            movieViewHolder.pubDate.setText(null);
        }

        if (!TextUtils.isEmpty(movie.getHeadline())) {
            movieViewHolder.headline.setText(movie.getHeadline());
        } else {
            movieViewHolder.headline.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(movie.getSummary())) {
            movieViewHolder.movieReview.setText(movie.getSummary());
        } else {
            movieViewHolder.movieReview.setText(null);
        }

        movieViewHolder.thumbnail.setScaleType(ImageView.ScaleType.CENTER);

        if (movie.getMultimedia() != null) {
            movieViewHolder.thumbnail.setVisibility(View.VISIBLE);

            Picasso.with(context)
                    .load(movie.getMultimedia().getSrc())
                    .fit()
                    .centerCrop()
                    .transform(PaletteTransformation.instance())
                    .into(movieViewHolder.thumbnail,
                            new Callback.EmptyCallback() {
                                @Override
                                public void onSuccess() {
                                    Bitmap bitmap = ((BitmapDrawable) movieViewHolder.thumbnail.getDrawable()).getBitmap();
                                    Palette palette = PaletteTransformation.getPalette(bitmap);
                                    Palette.Swatch swatch = palette.getVibrantSwatch();

                                    if (swatch != null) {
                                        movieViewHolder.title.setBackgroundColor(swatch.getRgb());
                                    }
                                }
                            });
        } else {
            movieViewHolder.title.setBackgroundColor(ContextCompat.getColor(context,
                    R.color.colorPrimary));
            movieViewHolder.thumbnail.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if (isFooterVisible) {
            return data.size() + 1;
        } else {
            return data.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (this.isFooterVisible &&
                position == this.data.size()) {
            return VIEW_TYPE_FOOTER;
        } else {
            return VIEW_TYPE_MOVIE;
        }
    }

    public void setData(List<Movie> data) {
        this.data = data;
    }

    public void isFooterVisible(boolean isVisible) {
        isFooterVisible = isVisible;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_image)
        ImageView thumbnail;

        @BindView(R.id.movie_title)
        TextView title;

        @BindView(R.id.byline)
        TextView reviewer;

        @BindView(R.id.rating)
        TextView rating;

        @BindView(R.id.pubDate)
        TextView pubDate;

        @BindView(R.id.headline)
        TextView headline;

        @BindView(R.id.review)
        TextView movieReview;

        MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {

        FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
