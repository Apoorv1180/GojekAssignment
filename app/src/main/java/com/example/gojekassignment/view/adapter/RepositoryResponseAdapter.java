package com.example.gojekassignment.view.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gojekassignment.R;
import com.example.gojekassignment.service.model.RepositoryResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RepositoryResponseAdapter extends RecyclerView.Adapter<RepositoryResponseAdapter.MyViewHolder> implements View.OnClickListener {

    private List<RepositoryResponse> bookingsList;
    int status;
    Context context;

    public interface OnItemClickListener {
        void onItemClick(RepositoryResponse item);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView imProfilePicture;
        public TextView tvAuthorName;
        public TextView tvRepoName;
        public TextView tvRepoDescription;
        public TextView tvLanguage;
        public TextView tvFork;
        public TextView tvStars;
        public LinearLayout expandView;


        public MyViewHolder(View view) {
            super(view);
            imProfilePicture = itemView.findViewById(R.id.id_profile_picture);
            tvAuthorName = itemView.findViewById(R.id.id_author);
            tvRepoName = itemView.findViewById(R.id.id_repository_name);
            tvRepoDescription = itemView.findViewById(R.id.repository_description);
            tvLanguage = itemView.findViewById(R.id.id_language);
            tvFork = itemView.findViewById(R.id.id_fork_text);
            tvStars = itemView.findViewById(R.id.id_star_text);
            expandView = itemView.findViewById(R.id.id_expand_view);
        }
    }


    public RepositoryResponseAdapter(List<RepositoryResponse> bookingsList, Context context) {
        this.bookingsList = bookingsList;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_repository_item_view, parent, false);

        MyViewHolder holder = new MyViewHolder(itemView);

        // Sets the click adapter for the entire cell
        // to the one in this class.
        holder.itemView.setOnClickListener(RepositoryResponseAdapter.this);
        holder.itemView.setTag(holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RepositoryResponse item = bookingsList.get(position);
        Picasso.get().load(item.getAvatar()).into(holder.imProfilePicture);
        if (!TextUtils.isEmpty(item.getAuthor())) {
            holder.tvAuthorName.setText(item.getAuthor());
        } else holder.tvAuthorName.setText(context.getResources().getString(R.string.not_provided));
        if (!TextUtils.isEmpty(item.getName())) {
            holder.tvRepoName.setText(item.getName());
        } else holder.tvRepoName.setText(context.getResources().getString(R.string.not_provided));
        if (!TextUtils.isEmpty(item.getDescription())) {
            holder.tvRepoDescription.setText(item.getDescription());
        } else
            holder.tvRepoDescription.setText(context.getResources().getString(R.string.not_provided));
        if (!TextUtils.isEmpty(item.getLanguage())) {
            holder.tvLanguage.setText(item.getLanguage());
        } else holder.tvLanguage.setText(context.getResources().getString(R.string.not_provided));
        if (item.getForks() != 0) {
            holder.tvFork.setText(item.getForks().toString());
        } else holder.tvFork.setText(context.getResources().getString(R.string.not_provided));
        if (item.getStars() != 0) {
            holder.tvStars.setText(item.getForks().toString());
        } else holder.tvStars.setText(context.getResources().getString(R.string.not_provided));

        if (item.isExpanded() == true) {

            holder.expandView.setVisibility(View.VISIBLE);

        } else {

            holder.expandView.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return bookingsList.size();
    }

    @Override
    public void onClick(View view) {
        MyViewHolder holder = (MyViewHolder) view.getTag();

        if (bookingsList.get(holder.getPosition()).isExpanded()) {
            bookingsList.get(holder.getPosition()).setExpanded(false);
            notifyDataSetChanged();
        } else {
            // set previously expanded row to false
            for (int i = 0; i < bookingsList.size(); i++) {
                if (bookingsList.get(i).isExpanded()) {
                    bookingsList.get(i).setExpanded(false);
                }
            }
            //set current item expanded
            bookingsList.get(holder.getPosition()).setExpanded(true);
            notifyDataSetChanged();
        }
    }
}