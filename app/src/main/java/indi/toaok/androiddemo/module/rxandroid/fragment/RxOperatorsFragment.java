package indi.toaok.androiddemo.module.rxandroid.fragment;

import indi.toaok.androiddemo.base.presenter.fragment.BaseFragment;
import indi.toaok.androiddemo.module.rxandroid.view.RxOperatorsDelegate;

/**
 * @author Toaok
 * @version 1.0  2019/3/21.
 */
public class RxOperatorsFragment extends BaseFragment<RxOperatorsDelegate> {

    public static RxOperatorsFragment newInstance() {
        RxOperatorsFragment fragment = new RxOperatorsFragment();
        return fragment;
    }


    @Override
    protected Class<RxOperatorsDelegate> getDelegateClass() {
        return RxOperatorsDelegate.class;
    }
}
