package com.android.gumeoficial.android_me.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.gumeoficial.android_me.R;
import com.android.gumeoficial.android_me.data.AndroidImageAssets;

public class MasterListFragment extends Fragment {

    //Todo (1) Define a new interface OnImageClickListener that triggers a callback in the host activity
    //The callback is a method named onImageSelected(int position) that contains information about
    //which position on the grid of images a user has clicked
    OnImageClickListener mCallback;

    public interface OnImageClickListener{
        void onImageSelected(int position);
    }

    //TODO (2) Override onAttach to make sure that the container activity has implemented the callback

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //This makes sure that the host activity has implemented the callback interface
        //if not, it throws an exception.
        try{
            mCallback = (OnImageClickListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString()
            +"must implement OnImageClickListener");
        }
    }

    public MasterListFragment() {
    }

    // Inflates the GridView of all AndroidMe images
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        // Get a reference to the GridView in the fragment_master_list xml layout file
        GridView gridView = (GridView) rootView.findViewById(R.id.images_grid_view);

        // Create the adapter
        // This adapter takes in the context and an ArrayList of ALL the image resources to display
        MasterListAdapter mAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());

        // Set the adapter on the GridView
        gridView.setAdapter(mAdapter);

        //TODO (3) Seta click listener on the gridview and trigger the callback onImageSelected when an item is selected
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallback.onImageSelected(position);
            }
        });

        // Return the root view
        return rootView;
    }
}
