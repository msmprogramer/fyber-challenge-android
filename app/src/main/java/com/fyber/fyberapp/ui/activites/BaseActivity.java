package com.fyber.fyberapp.ui.activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.fyber.fyberapp.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);

        Bundle args = getIntent().getExtras();
        if(args == null) {
            args = new Bundle();
        }

        loadFragment(args);
    }

    private void loadFragment(Bundle args) {

        Class<? extends Fragment> fragmentClass = getFragmentClass();

        FragmentManager manager = getSupportFragmentManager();

        if (fragmentClass != null) {

            String tag = this.getClass().getName();

            Fragment fragment = manager.findFragmentByTag(tag);
            if (fragment == null) {
                fragment = Fragment.instantiate(getApplicationContext(), fragmentClass.getName());

                fragment.setArguments(args);

                manager.beginTransaction()
                        .replace(R.id.content_frame, fragment, tag)
                        .commit();
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected abstract Class<? extends Fragment> getFragmentClass();
}
