package romz.luannguyen.getfirebase.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import romz.luannguyen.getfirebase.R;
import romz.luannguyen.getfirebase.activity.ThongTinActivity;
import romz.luannguyen.getfirebase.model.DuLieu;

/**
 * Created by Admin on 10/28/2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.DuLieuViewHolder> {
    private static final String TAG = "DulieuAdapter";
    private List<DuLieu> mDulieus;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public CustomAdapter(Context context, List<DuLieu> mDulieus) {
        this.mContext = context;
        this.mDulieus = mDulieus;
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public CustomAdapter.DuLieuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_list, parent, false);
        return new DuLieuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.DuLieuViewHolder holder, final int position) {
        Log.d(TAG,"============>");
        final DuLieu dulieu = mDulieus.get(position);
        Glide.with(mContext).load(dulieu.getUrl())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mImageView);


        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(mContext, ThongTinActivity.class);in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                in.putExtra("content",mDulieus.get(position).getContent());

                in.putExtra("title",mDulieus.get(position).getTitle());
                in.putExtra("url",mDulieus.get(position).getUrl());
                mContext.startActivity(in);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDulieus.size();
    }




    public static class DuLieuViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener{

        private TextView mContentz,mTitlez;
        private ImageView mImageView;
        private ItemClickListener mListener;

        public DuLieuViewHolder(View itemView) {
            super(itemView);

            mImageView = (ImageView)itemView.findViewById(R.id.imvHinh);
            mContentz =(TextView) itemView.findViewById(R.id.txtContent);
            mTitlez = (TextView) itemView.findViewById(R.id.txtTitle);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View view) {
            mListener.onClickItem(getLayoutPosition());

        }

        @Override
        public boolean onLongClick(View view) {
            mListener.onLongClickItem(getLayoutPosition());
            return true;
        }
    }
    public interface ItemClickListener {
        void onClickItem(int pos);

        void onLongClickItem(int pos);
    }
}
