package com.psychoapp.iliev.psychoapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
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
        sampleTextList.add("Sample text 4");

        ListAdapter adapter = new ArrayAdapter<String>(this.getActivity(), R.layout.list_view_question, sampleTextList);
        // adapter.getItem();

        ListView question = (ListView) view.findViewById(R.id.listview_question);
        question.setAdapter(adapter);

        return view;
    }

    public static class QustionsTestPagerAdapter extends FragmentStatePagerAdapter {

        public QustionsTestPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // set here the data to be bind in fragment_quiz for each question
        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new QuizActivityFragment();
            Bundle args = new Bundle();
            args.putInt(QuizActivityFragment.ARG_OBJECT, i + 1); // Our object is just an integer :-P
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            // For this contrived example, we have a 10-object collection.
            return 10;
        }

    }

}
