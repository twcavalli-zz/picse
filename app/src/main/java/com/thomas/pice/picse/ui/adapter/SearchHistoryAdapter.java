package com.thomas.pice.picse.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.thomas.pice.picse.R;
import com.thomas.pice.picse.data.model.*;
import com.thomas.pice.picse.ui.activity.HistoryActivity;
import io.realm.RealmResults;

/**
 * Created by Thomas on 13/04/2017.
 */
public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.myViewHolder> {
    private RealmResults<Search> mDataset;
    private LayoutInflater inflater;
    private Context ctx;

    public SearchHistoryAdapter(Context ctx, RealmResults myDataset) {
        this.ctx = ctx;
        inflater = LayoutInflater.from(ctx);
        mDataset = myDataset;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_row, parent, false);
        myViewHolder holder = new myViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder viewHolder, int i) {
        viewHolder.word.setText(mDataset.get(i).getWord());

        // When item clicked, return the word back to Search activity
        final Search item = mDataset.get(i);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("search_word",item.getWord());
                ((HistoryActivity)ctx).setResult(Activity.RESULT_OK,returnIntent);
                ((HistoryActivity)ctx).finish();
            }
        });
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
        TextView word;
        public myViewHolder(View itemView) {
            super(itemView);
            word = (TextView) itemView.findViewById(R.id.history_search_row);
        }
    }
}
