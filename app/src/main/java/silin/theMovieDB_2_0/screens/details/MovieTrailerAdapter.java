package silin.theMovieDB_2_0.screens.details;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import autodagger.AutoInjector;
import butterknife.BindView;
import butterknife.ButterKnife;
import silin.theMovieDB_2_0.BaseApplication;
import silin.theMovieDB_2_0.R;
import silin.theMovieDB_2_0.models.MovieTrailer;

/**
 * Created on 8/2/16: theMovieDB_2_0 by @n1207n
 */
@AutoInjector(BaseApplication.class)
public class MovieTrailerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;

    private ArrayList<MovieTrailer> mMovieTrailerList;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    MovieTrailerAdapter(Context context, ArrayList<MovieTrailer> movieTrailers) {
        mContext = context;
        mMovieTrailerList = movieTrailers;
        BaseApplication.sharedApplication().getComponentApplication().inject(this);
    }

    /**
     * Called when RecyclerView needs a new {@link MovieTrailerViewHolder} of the given type to represent
     * an item.
     * <p/>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p/>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder, int)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder, int)
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;

        if (viewType == TYPE_HEADER) {
            TextView view = (TextView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.movie_trailer_list_header, parent, false);

            viewHolder = new MovieTrailerHeaderViewHolder(view);
        } else if (viewType == TYPE_ITEM) {
            CardView view = (CardView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_view_movie_trailer_list_item, parent, false);

            viewHolder = new MovieTrailerViewHolder(view);
        }

        return viewHolder;
    }


    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link MovieTrailerViewHolder#itemView} to reflect the item at the given
     * position.
     * <p/>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link MovieTrailerViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p/>
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder.getItemViewType() == TYPE_ITEM) {
            final MovieTrailer movieTrailer = mMovieTrailerList.get(position);

            ((MovieTrailerViewHolder) holder).mTrailerTextView.setText(String.format("%s - %s - %sp", movieTrailer.name(), movieTrailer.type(), movieTrailer.size()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(movieTrailer.getTrailerUrl())));
                }
            });
        }
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mMovieTrailerList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEADER : TYPE_ITEM;
    }

    void updateData(ArrayList<MovieTrailer> newList) {
        mMovieTrailerList.clear();
        mMovieTrailerList.addAll(newList);
        notifyDataSetChanged();
    }

    ArrayList<MovieTrailer> getMovieTrailerList() {
        return mMovieTrailerList;
    }

    class MovieTrailerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_trailer_text)
        TextView mTrailerTextView;

        MovieTrailerViewHolder(CardView itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class MovieTrailerHeaderViewHolder extends RecyclerView.ViewHolder {

        MovieTrailerHeaderViewHolder(TextView itemView) {
            super(itemView);
        }
    }
}
