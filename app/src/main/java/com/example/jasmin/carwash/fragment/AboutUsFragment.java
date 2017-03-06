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
public class AboutUsFragment extends Fragment {

    private static WebView wvAboutUs;

    public AboutUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_about_us, container, false);

        wvAboutUs = (WebView) v.findViewById(R.id.wv_about_us);
        wvAboutUs.setWebViewClient(new WebViewClient());
        wvAboutUs.getSettings().setBuiltInZoomControls(true);
        wvAboutUs.getSettings().setDisplayZoomControls(false);
        wvAboutUs.loadUrl("http://www.lipsum.com/feed/html");

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("About Us");
    }
}
