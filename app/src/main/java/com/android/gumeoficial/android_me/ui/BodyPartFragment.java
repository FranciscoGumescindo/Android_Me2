package com.android.gumeoficial.android_me.ui;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.gumeoficial.android_me.R;
import com.android.gumeoficial.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends Fragment {

    //Final Strings to store state information about the list of images and list index
    public static final String IMAGE_ID_LIST = "image_ids";
    public static final String LIST_INDEX = "list_index";

    private static  final String TAG = "BodyPartFragment";
    // TODO (1) Create a setter method and class variable to set and store of a list of image resources

    // TODO (2) Create another setter method and variable to track and set the index of the list item to display
    // ex. index = 0 is the first image id in the given list , index 1 is the second, and so on

    //Variables to store a list of image resources and the index of the image that this fragment display
    private List<Integer> mImageIds;
    private int mListIndex;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment
     *
     */
    public BodyPartFragment() {
    }

    /**
     * Inflates the fragment layout file and sets the correct resource for the image to display
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Load the saved state (the list of images and list index) if there is one
        if (savedInstanceState != null){
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex = savedInstanceState.getInt(LIST_INDEX);
        }

        // Inflate the Android-Me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        // Get a reference to the ImageView in the fragment layout
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        // Set the image to the first in our list of head images
        //imageView.setImageResource(AndroidImageAssets.getHeads().get(0));

        // TODO (3) If a list of image ids exists, set the image resource to the correct item in that list
        // Otherwise, create a Log statement that indicates that the list was not found
        if (mImageIds != null){
            //set the image resource to the list item at tjhe stored index
            imageView.setImageResource(mImageIds.get(mListIndex));

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListIndex < mImageIds.size()-1){
                        mListIndex++;
                    }else{
                        //The end of list has been reached, so return to begginign index
                        mListIndex = 0;
                    }
                    //set the image resource to the new list item
                    imageView.setImageResource(mImageIds.get(mListIndex));

                }

            });
        }

        else {
            Log.v(TAG,"This fragment has a null list of image id's");
        }

        // Return the rootView
        return rootView;
    }

    public void setImageIds(List<Integer> imageIds){
        mImageIds = imageIds;
    }

    public void setListIndex(int index){
        mListIndex = index;
    }

    //Salvar el estado de los fragmentos cuando salgas de ella

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putIntegerArrayList(IMAGE_ID_LIST,(ArrayList<Integer>)mImageIds);
        currentState.putInt(LIST_INDEX,mListIndex);

    }
}
