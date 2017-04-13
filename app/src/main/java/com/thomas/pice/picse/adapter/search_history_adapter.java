package com.thomas.pice.picse.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.thomas.pice.picse.R;
import com.thomas.pice.picse.model.*;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Thomas on 13/04/2017.
 */

public class search_history_adapter extends RecyclerView.Adapter<search_history_adapter.ViewHolder> {
    private RealmResults<search> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    public search_history_adapter(RealmResults myDataset) {
        mDataset = myDataset;
    }

    @Override
    public search_history_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position).getWord());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
