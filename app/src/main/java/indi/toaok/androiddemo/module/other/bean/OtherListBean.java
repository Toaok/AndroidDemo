package indi.toaok.androiddemo.module.other.bean;

import java.util.ArrayList;
import java.util.List;

import indi.toaok.androiddemo.base.model.IBaseModel;
import indi.toaok.androiddemo.module.base.recyclerview.BaseRecyclerAdapter;
import indi.toaok.androiddemo.module.base.recyclerview.BaseViewHolder;
import indi.toaok.androiddemo.module.other.adapter.OtherListAdapter;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Toaok
 * @version 1.0  2019/4/26.
 */
@Data
@Accessors(prefix = "m")
public class OtherListBean implements IBaseModel {
    private List<OtherItemBean> mOtherList;

    private BaseRecyclerAdapter<OtherItemBean, BaseViewHolder> mAdapter;

    private BaseRecyclerAdapter.OnItemClickListener mOnItemClickListener;

    private String mMessage;

    public OtherListBean(String[] items) {
        this(items, null);
    }

    public OtherListBean(String[] items, BaseRecyclerAdapter.OnItemClickListener onItemClickListener) {
        mOtherList = new ArrayList<>();
        for (String item : items) {
            OtherItemBean itemBean = new OtherItemBean();
            itemBean.setText(item);
            mOtherList.add(itemBean);
        }
        mAdapter = new OtherListAdapter(mOtherList);
        mOnItemClickListener = onItemClickListener;
        if (mOnItemClickListener != null) {
            mAdapter.setOnItemClickListener(mOnItemClickListener);
        }
    }

    public void setOnItemClickListener(BaseRecyclerAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        mAdapter.setOnItemClickListener(mOnItemClickListener);
    }
}
