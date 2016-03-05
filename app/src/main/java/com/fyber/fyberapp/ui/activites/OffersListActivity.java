package com.fyber.fyberapp.ui.activites;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.fyber.fyberapp.ui.fragments.OffersListFragment;

public class OffersListActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Nullable
    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return OffersListFragment.class;
    }
}
