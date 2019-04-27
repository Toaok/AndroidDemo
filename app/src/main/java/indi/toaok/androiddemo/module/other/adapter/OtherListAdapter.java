package indi.toaok.androiddemo.module.other.adapter;

import java.util.List;

import indi.toaok.androiddemo.R;
import indi.toaok.androiddemo.module.base.recyclerview.BaseRecyclerAdapter;
import indi.toaok.androiddemo.module.base.recyclerview.BaseViewHolder;
import indi.toaok.androiddemo.module.other.bean.OtherItemBean;

/**
 * @author Toaok
 * @version 1.0  2019/4/26.
 */
public class OtherListAdapter extends BaseRecyclerAdapter<OtherItemBean, BaseViewHolder> {

    public OtherListAdapter(List<OtherItemBean> data) {
        super(data);
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.view_item_other;
    }

    @Override
    public void bindData(BaseViewHolder holder, int position) {
        OtherItemBean itemBean = mData.get(position);
        holder.getButton(R.id.btn_other_item).setText(itemBean.getText());
    }
}
