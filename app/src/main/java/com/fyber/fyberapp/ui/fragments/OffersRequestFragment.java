package com.fyber.fyberapp.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.fyber.fyberapp.R;
import com.fyber.fyberapp.model.OffersRequest;
import com.fyber.fyberapp.ui.activites.OffersListActivity;

import static com.google.common.base.Preconditions.checkNotNull;


public class OffersRequestFragment extends Fragment {

    public static final String EXTRA_OFFER_REQUEST = "extraOfferRequest";
    private EditText editTextUid;
    private EditText editTextApiKey;
    private EditText editTextAppId;
    private EditText editTextPub0;
    private Button buttonFindOffers;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_offers_request, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        editTextUid = (EditText) view.findViewById(R.id.editText_uid);
        editTextApiKey = (EditText) view.findViewById(R.id.editText_api_key);
        editTextAppId = (EditText) view.findViewById(R.id.editText_app_id);
        editTextPub0 = (EditText) view.findViewById(R.id.editText_pub0);

        buttonFindOffers = (Button) view.findViewById(R.id.button_find_offers);
        buttonFindOffers.setOnClickListener(new ButtonFindOffersClickListener());

        super.onViewCreated(view, savedInstanceState);
    }

    private boolean validateInputValues() {
        boolean allInputsValid = true;
        if(editTextApiKey.getText().toString().isEmpty()) {
            allInputsValid = false;
            editTextApiKey.setError(getString(R.string.error_empty_api_key));
        }

        if(editTextUid.getText().toString().isEmpty()) {
            allInputsValid = false;
            editTextUid.setError(getString(R.string.error_empty_uid));
        }

        if(editTextAppId.getText().toString().isEmpty()) {
            allInputsValid = false;
            editTextAppId.setError(getString(R.string.error_empty_app_id));
        }

        if(editTextPub0.getText().toString().isEmpty()) {
            allInputsValid = false;
            editTextPub0.setError(getString(R.string.error_empty_pub0));
        }

        return allInputsValid;
    }

    @NonNull
    private OffersRequest createOfferRequest() {
        return new OffersRequest.Builder()
                .setAppId(editTextAppId.getText().toString())
                .setIp(getString(R.string.test_ip))
                .setLocale(getString(R.string.test_locale))
                .setTimestamp(String.valueOf(System.currentTimeMillis() / 1000))
                .setUid(editTextUid.getText().toString())
                .setOfferTypes(getString(R.string.test_offer_types))
                .setPub0(editTextPub0.getText().toString())
                .setApikey(editTextApiKey.getText().toString())
                .build();
    }

    private class ButtonFindOffersClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if(validateInputValues()) {
                OffersRequest offersRequest = checkNotNull(createOfferRequest());
                Intent intent = new Intent(getActivity(), OffersListActivity.class);
                intent.putExtra(EXTRA_OFFER_REQUEST, offersRequest);
                startActivity(intent);
            }
        }
    }
}
