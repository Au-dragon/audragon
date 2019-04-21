package com.example.geek.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.geek.R;

public class GoldDateFragment extends Fragment {
    private View view;
    private TextView mT1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.golddate_item, null);
        initView(view);
        return view;
    }


    private void initView(View view) {
        mT1 = (TextView) view.findViewById(R.id.t1);
        Bundle arguments = getArguments();
        String title = (String) arguments.get("title");
        mT1.setText(title);
    }
}
