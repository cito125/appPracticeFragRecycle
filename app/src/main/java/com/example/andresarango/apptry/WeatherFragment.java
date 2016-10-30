package com.example.andresarango.apptry;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import static android.content.ContentValues.TAG;

/**
 * Created by andresarango on 10/29/16.
 */

public class WeatherFragment extends Fragment {
    private static final String SWAG ="YEEEEEEEEEEEERRR" ;
    private String url_pic;
    private ImageView image;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_fragment,container,false);
        image = (ImageView) getActivity().findViewById(R.id.weather_pic_frag);
        view.setOnClickListener(onClick(this));
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            url_pic = bundle.getString(WeatherViewHolder.URL_FOR_PICTURE_KEY);
        }
        Log.d(TAG, url_pic);
//        Picasso.with(getActivity())
//                .load(url_pic)
//                .placeholder(R.drawable.ic_action_weather)
//                .resizeDimen(R.dimen.pic_dimens, R.dimen.pic_dimens)
//                .centerInside()
//                .into(image);
        return view;
    }

    private View.OnClickListener onClick(final Fragment frag) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "destroy ");
                getActivity().getFragmentManager().popBackStack(); //beginTransaction().remove(frag).commit();
            }
        };
    }

}
