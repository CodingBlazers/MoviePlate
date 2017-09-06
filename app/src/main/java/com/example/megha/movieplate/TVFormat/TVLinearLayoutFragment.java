package com.example.megha.movieplate.TVFormat;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.megha.movieplate.Constants;
import com.example.megha.movieplate.MovieFormat.SingleMovieCardViewFragment;
import com.example.megha.movieplate.R;
import com.example.megha.movieplate.WatchlistFormat.PostJsonInWatchList;
import com.example.megha.movieplate.utility.API.MovieDBApiClient;
import com.example.megha.movieplate.utility.SharedPreferencesUtils;
import com.example.megha.movieplate.utility.UI.UiUnitConverter;
import com.example.megha.movieplate.utility.UI.ViewIdGenerator;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by megha on 14/08/17.
 */

public class TVLinearLayoutFragment extends Fragment implements Constants{

    Context mContext;
    View view;

    ArrayList<TVDetails> mData;
    String key, userID, sessionID;

    SharedPreferencesUtils spUtils;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle b = getArguments();
        mData = (ArrayList<TVDetails>) b.getSerializable(ALL_TV_SHOW_DETAILS);
        mContext = getContext();

        spUtils = new SharedPreferencesUtils(getContext());
        key = spUtils.getAPIKey();
        userID = spUtils.getIDKey();
        sessionID = spUtils.getSessionIDKey();

        initViews();
        return view;
    }

    private void initViews() {

        HorizontalScrollView scrollView = new HorizontalScrollView(mContext);
        scrollView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        scrollView.addView(linearLayout);

        for(int i=0; i<10; i++){
            int id = ViewIdGenerator.generateViewId();
            FrameLayout frameLayout = new FrameLayout(mContext);
            frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            frameLayout.setId(id);
            linearLayout.addView(frameLayout);
            SingleTVShowCardView fragment = new SingleTVShowCardView();
            Bundle bundle = new Bundle();
            bundle.putSerializable(SINGLE_TV_SHOW_DETAILS, mData.get(i));
            fragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(id, fragment).commit();
        }
        view = scrollView;
    }

}
