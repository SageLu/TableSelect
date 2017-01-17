package zz.hanhan.testtablegreendao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import zz.hanhan.testtablegreendao.R;
import zz.hanhan.testtablegreendao.entity.TableInfo;

/**
 * Created by lenovo on 2017/1/16.
 */

public class TableRecyclerViewAdapter extends RecyclerView.Adapter<TableRecyclerViewAdapter.MinViewHolder> {

    private final List<TableInfo> infoList;
    private final Context context;

    public TableRecyclerViewAdapter(Context context, List<TableInfo> infoList) {
        this.context = context;
        this.infoList = infoList;
    }

    public void add(int position, TableInfo info) {
        infoList.add(position, info);
        notifyItemInserted(position);
    }

    public void delete(int position) {
        infoList.remove(position);
        notifyItemRemoved(position);
    }

    public List<TableInfo> getData() {
        return infoList;
    }

    @Override
    public MinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false);
        return new MinViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MinViewHolder holder, final int position) {
        holder.item_tv.setText(infoList.get(position).getNewsChannelName());

        holder.item_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(holder.getLayoutPosition(), v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return infoList == null ? 0 : infoList.size();
    }

    class MinViewHolder extends RecyclerView.ViewHolder {
        private TextView item_tv;

        public MinViewHolder(View itemView) {
            super(itemView);
            item_tv = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int positon, View view);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
