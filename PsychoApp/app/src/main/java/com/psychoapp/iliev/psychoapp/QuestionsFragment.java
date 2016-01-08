package com.psychoapp.iliev.psychoapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class QuestionsFragment extends Fragment {

    public QuestionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_questions, container, false);

        // set view adapters if needed here and then return the populated view
        List<String> sampleTextList = new ArrayList<>();
        sampleTextList.add("Sample text 1");
        sampleTextList.add("Sample text 2");
        sampleTextList.add("Sample text 3");

        ListAdapter adapter = new ArrayAdapter<String>(this.getActivity(), R.layout.list_view_question, sampleTextList);

        ListView question = (ListView) view.findViewById(R.id.listview_question);
        question.setAdapter(adapter);

        return view;
    }
}
