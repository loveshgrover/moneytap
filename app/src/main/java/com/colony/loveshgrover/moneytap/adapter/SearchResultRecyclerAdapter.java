package com.colony.loveshgrover.moneytap.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.colony.loveshgrover.moneytap.DetailsActivity;
import com.colony.loveshgrover.moneytap.R;
import com.colony.loveshgrover.moneytap.model.Page;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by loveshgrover on 12/03/18.
 */

public class SearchResultRecyclerAdapter extends RecyclerView.Adapter<SearchResultRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Page> values;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title;
        public TextView description;
        public ImageView searchResultImageTextView;
        public RelativeLayout detailsRelativeLayout;

        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            title = v.findViewById(R.id.title);
            searchResultImageTextView = v.findViewById(R.id.image_view_searchResult);
            detailsRelativeLayout = v.findViewById(R.id.details);


        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public SearchResultRecyclerAdapter(List<Page> pages, Context applicationContext) {
        values = pages;
        context = applicationContext;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SearchResultRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.search_result, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Page page = values.get(position);
        holder.title.setText(page.getTitle());
        if (page.getThumbnail() != null && page.getThumbnail().getSource() != null) {
            Glide.with(context).load(page.getThumbnail().getSource())
                    .apply(bitmapTransform(new CropCircleTransformation()))
                    .into(holder.searchResultImageTextView);

        }
        holder.detailsRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent details = new Intent(context, DetailsActivity.class);
                details.putExtra("page", page);
                context.startActivity(details);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (values == null)
            return 0;
        return values.size();
    }
}
