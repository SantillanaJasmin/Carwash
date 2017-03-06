package com.example.jasmin.carwash.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.jasmin.carwash.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUsFragment extends Fragment {

    private static WebView wvContactUs;

    public ContactUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_contact_us, container, false);

        wvContactUs = (WebView) v.findViewById(R.id.wv_contact_us);
        wvContactUs.setWebViewClient(new WebViewClient());
        wvContactUs.getSettings().setBuiltInZoomControls(true);
        wvContactUs.getSettings().setDisplayZoomControls(false);
        wvContactUs.loadUrl("http://www.lipsum.com/feed/html");

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Contact Us");
    }
}
