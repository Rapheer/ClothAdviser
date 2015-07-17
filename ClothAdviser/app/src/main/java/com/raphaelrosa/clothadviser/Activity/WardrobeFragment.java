package com.raphaelrosa.clothadviser.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raphaelrosa.clothadviser.R;

/**
 * Created by Raphael on 17/07/2015.
 */
public class WardrobeFragment extends Fragment{

    public WardrobeFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_wardrobe,container,false);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
    }

    @Override
    public void onDetach(){
        super.onDetach();
    }
}
