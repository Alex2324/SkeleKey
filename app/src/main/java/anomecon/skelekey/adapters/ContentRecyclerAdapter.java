package anomecon.skelekey.adapters;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import anomecon.skelekey.Content;
import anomecon.skelekey.R;

import java.util.List;

public class ContentRecyclerAdapter
        extends RecyclerView.Adapter<ContentRecyclerAdapter.ContentViewHolder> {

    private List<Content> listContent;

    public ContentRecyclerAdapter(List<Content> listContent) {
        this.listContent = listContent;
    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_content_recycler, parent, false);

        return new ContentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContentViewHolder holder, int position) {
        holder.textViewName.setText(listContent.get(position).getContentName());
        holder.textViewUserName.setText(listContent.get(position).getContentUserName());
        holder.textViewPassword.setText(listContent.get(position).getContentPassword());
    }

    @Override
    public int getItemCount() {
        Log.v(ContentRecyclerAdapter.class.getSimpleName(),""+listContent.size());
        return listContent.size();
    }

    public class ContentViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public TextView textViewUserName;
        public TextView textViewPassword;

        public ContentViewHolder(View view) {
            super(view);
            textViewName = view.findViewById(R.id.textViewName);
            textViewUserName = view.findViewById(R.id.textViewUserName);
            textViewPassword = view.findViewById(R.id.textViewPassword);
        }
    }
}