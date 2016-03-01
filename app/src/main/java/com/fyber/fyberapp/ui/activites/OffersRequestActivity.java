package com.fyber.fyberapp.ui.activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.fyber.fyberapp.ui.fragments.OffersRequestFragment;

public class OffersRequestActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return OffersRequestFragment.class;
    }
}
