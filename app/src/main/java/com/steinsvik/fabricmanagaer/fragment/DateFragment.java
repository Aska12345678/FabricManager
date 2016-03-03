package com.steinsvik.fabricmanagaer.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.steinsvik.fabricmanagaer.R;
import com.steinsvik.fabricmanagaer.adapter.ListItemDateAdapter;
import com.steinsvik.fabricmanagaer.data.Information;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DateFragment extends Fragment {


    public DateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_item_date);
        ListItemDateAdapter adapter = new ListItemDateAdapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private List<Information> getData() {
        List<Information> arr = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            Information information = new Information();
            information.setDetail(String.valueOf("Product number Product number Product number Product number Product number " + i));
            information.setPrice((int)(Math.random() * 10000000));
            arr.add(information);
        }
        return arr;
    }

}
