package com.example.farmguide.moviedb.ui.review;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.farmguide.moviedb.R;
import com.example.farmguide.moviedb.data.db.Review;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private List<Review> reviewList;
    private Context context;

    public void setReviewList(List<Review> reviewList) {
        onNewData(reviewList);
    }

    private void onNewData(List<Review> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ReviewDiffUtil(newList,reviewList));
        this.reviewList = newList;
        diffResult.dispatchUpdatesTo(this);
    }

    public ReviewAdapter(Context context){
       this.context = context;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.review_layout,null , false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.bind(reviewList.get(position));
    }

    @Override
    public int getItemCount() {
        if(reviewList!=null){
            return reviewList.size();
        }
        return 0;
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder{

        TextView authorTextView,reviewTextView;
        public ReviewViewHolder(View itemView) {
            super(itemView);
            authorTextView = itemView.findViewById(R.id.textView_author);
            reviewTextView = itemView.findViewById(R.id.textView_review);
        }

        public void bind(Review review) {
            authorTextView.setText(review.getAuthor());
            reviewTextView.setText(review.getContent());
        }
    }

    class ReviewDiffUtil extends DiffUtil.Callback{

        private List<Review> newList;
        private List<Review> oldList;

        ReviewDiffUtil(List<Review> newList , List<Review> oldList){
            this.newList = newList;
            this.oldList = oldList;
        }

        @Override
        public int getOldListSize() {
            return oldList==null?0:oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList==null?0:newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).getReviewId().equals(newList.get(newItemPosition).getReviewId());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return (newList.get(newItemPosition).getAuthor().equals(oldList.get(oldItemPosition).getAuthor())
                    && newList.get(newItemPosition).getContent()
                    .equals(oldList.get(oldItemPosition).getContent()));
        }
    }
}
