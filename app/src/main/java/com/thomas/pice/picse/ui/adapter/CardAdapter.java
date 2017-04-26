package com.thomas.pice.picse.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.thomas.pice.picse.R;
import com.thomas.pice.picse.remote.object.Media;
import java.util.ArrayList;

/**
 * Created by Thomas on 14/04/2017.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.myViewHolder> {
    private ArrayList<Media> mDataset;
    private LayoutInflater inflater;
    private Context ctx;

    private int lastPosition = -1;


    public CardAdapter(Context ctx, ArrayList myDataset) {
        this.ctx = ctx;
        inflater = LayoutInflater.from(ctx);
        mDataset = myDataset;
    }

    @Override
    public CardAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_adapter, parent, false);
        CardAdapter.myViewHolder holder = new CardAdapter.myViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CardAdapter.myViewHolder viewHolder, int i) {
        try {
            viewHolder.person_name.setText("@"+mDataset.get(i).getPerson_name());
        } catch (Exception e) { }
        try {
            viewHolder.tweet_text.setText(mDataset.get(i).getTweet_text());
        } catch (Exception e) { }
        try {
            // Download image
            Picasso.with(ctx).load(mDataset.get(i).getMedia_url()).resize(450, 600).centerCrop().into(viewHolder.image_media);
        } catch (Exception e) { }
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView myRecyclerView) {
        super.onAttachedToRecyclerView(myRecyclerView);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        TextView person_name;
        TextView tweet_text;
        ImageView image_media;
        public myViewHolder(View itemView) {
            super(itemView);
            person_name = (TextView) itemView.findViewById(R.id.txv_person_name);
            tweet_text = (TextView) itemView.findViewById(R.id.txv_tweet_text);
            image_media = (ImageView) itemView.findViewById(R.id.img_media);

        }
    }

    public void setCard(ArrayList<Media> clmedias) {
        mDataset= clmedias;
        notifyDataSetChanged();
    }
}
