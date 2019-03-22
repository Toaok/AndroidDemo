package indi.toaok.androiddemo.base.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.IntRange;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Toaok
 * @version 1.0  2019/3/13.
 */
public abstract class BaseRecyclerAdapter<D, V extends BaseViewHolder> extends RecyclerView.Adapter<V> {


    protected Context mContext;

    protected List<D> mData;

    protected boolean mIsUserAnimation;

    protected RecyclerView.LayoutManager mLayoutManager;

    public LayoutInflater mLayoutInflater;

    public BaseRecyclerAdapter(List<D> data) {
        this(data, true);
    }

    public BaseRecyclerAdapter(List<D> data, boolean isUserAnimation) {
        this(data, isUserAnimation, null);
    }

    public BaseRecyclerAdapter(List<D> data, boolean isUserAnimation, RecyclerView.LayoutManager layoutManager) {
        mData = data;
        mIsUserAnimation = isUserAnimation;
        mLayoutManager = layoutManager;
    }


    @Override
    public void onViewDetachedFromWindow(@NonNull V holder) {
        super.onViewDetachedFromWindow(holder);
        if (mIsUserAnimation
                && holder.itemView.getAnimation() != null
                && holder.itemView.getAnimation().hasStarted()) {
            holder.itemView.clearAnimation();
        }
    }

    @NonNull
    @Override
    public V onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        this.mLayoutInflater = LayoutInflater.from(mContext);
        BaseViewHolder baseViewHolder = new BaseViewHolder(mLayoutInflater.inflate(getItemLayoutId(), parent, false));
        return (V) baseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull V holder, int position) {
        if (mData != null && position < mData.size()) {
            bindData(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (mData != null) {
            count = mData.size();
        }
        return count;
    }

    public void append(D item) {
        mData.add(item);
        notifyItemInserted(mData.size() - 1);
    }

    public void insert(@IntRange(from = 0) int position, D item) {
        mData.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(@IntRange(from = 0) int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public void remove(D item) {
        int position = mData.indexOf(item);
        if (position > 0) {
            mData.remove(position);
            notifyItemInserted(position);
        } else {
            throw new IndexOutOfBoundsException("the index=" + position + "in" + mData.getClass().getSimpleName());
        }
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    protected void setAnimation(View viewToAnimate, int postion) {
    }


    public abstract @LayoutRes
    int getItemLayoutId();

    public abstract void bindData(BaseViewHolder holder, int position);

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickLiistener {
        void onItemLongClick(View view, int position);
    }

}
