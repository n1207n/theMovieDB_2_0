package silin.theMovieDB_2_0.screens.main;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.inject.Inject;

import autodagger.AutoInjector;
import butterknife.BindView;
import butterknife.ButterKnife;
import silin.theMovieDB_2_0.BaseApplication;
import silin.theMovieDB_2_0.R;
import silin.theMovieDB_2_0.models.Movie;

/**
 * Created on 7/9/16: theMovieDB_2_0 by @n1207n
 */
@AutoInjector(BaseApplication.class)
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    @Inject
    Picasso mPicasso;

    @Inject
    Context mContext;

    private ArrayList<Movie> mMovieList;

    MovieAdapter(ArrayList<Movie> movieList) {
        mMovieList = movieList;
        BaseApplication.sharedApplication().getComponentApplication().inject(this);
    }

    /**
     * Called when RecyclerView needs a new {@link MovieViewHolder} of the given type to represent
     * an item.
     * <p/>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p/>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(MovieViewHolder, int)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(MovieViewHolder, int)
     */
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_movie_list_item, parent, false);

        return new MovieViewHolder(view);
    }


    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link MovieViewHolder#itemView} to reflect the item at the given
     * position.
     * <p/>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link MovieViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p/>
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        mPicasso.load(mMovieList.get(position).posterPathWidth342)
                .fit()
                .noPlaceholder()
                .into(holder.mPosterImageView);
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    void updateData(ArrayList<Movie> newList) {
        mMovieList.clear();
        mMovieList.addAll(newList);
        notifyDataSetChanged();
    }

    ArrayList<Movie> getMovieList() {
        return mMovieList;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.image_view_movie_poster)
        ImageView mPosterImageView;

        MovieViewHolder(CardView itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mPosterImageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
//            String sharedElementName = view.getTransitionName() + getAdapterPosition();
//
//            intent.putExtra(MOVIE_DETAILS_TRANSITION, sharedElementName);
//            intent.putExtra(MOVIE_DETAILS, mMovieList.get(getAdapterPosition()));
//
//            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(
//                    MainActivity.this
//                    // ,view, sharedElementName
//            ).toBundle();
//
//            startActivity(intent, bundle);
        }
    }
}
