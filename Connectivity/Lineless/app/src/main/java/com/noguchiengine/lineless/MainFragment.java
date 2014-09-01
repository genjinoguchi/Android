package com.noguchiengine.lineless;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;


public class MainFragment extends ListFragment {
    Firebase firebaseRoot, firebaseRestaurants;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        init();

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        /*
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, AndroidOS);
        setListAdapter(adapter);
        */
        return view;
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

    }

    public void init(){
        firebaseRoot = new Firebase("https://intense-fire-7167.firebaseio.com/");
        firebaseRestaurants = firebaseRoot.child("restaurants");

        firebaseRestaurants.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> restaurant = (Map<String, Object>) dataSnapshot.getValue();
                System.out.println("Something worked!");
                System.out.println(restaurant.keySet());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}