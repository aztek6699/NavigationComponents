package haq.app.navigationcomponents.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import haq.app.navigationcomponents.Constants;
import haq.app.navigationcomponents.MainActivity;
import haq.app.navigationcomponents.R;
import haq.app.navigationcomponents.baseclass.BaseFragment;


public class ListFragment extends BaseFragment {

    EditText editText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();

        if (getArguments().getString(Constants.TAG_TEXT) != null) {
            TextView tv = view.findViewById(R.id.textView);
            tv.setText(getArguments().getString(Constants.TAG_TEXT));
        }

        editText = view.findViewById(R.id.editText);
    }

    void initListeners() {
        getView().findViewById(R.id.btn_add_frag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                b.putInt(Constants.TAG_KEY, getFragmentTag());
                b.putString(Constants.TAG_TEXT, editText.getText().toString());
                ListFragment newFragment = new ListFragment();
                newFragment.setArguments(b);

                ((MainActivity) getActivity()).getFragmentNavigation().gotoNewFragment(getFragmentTag(), newFragment);
            }
        });
    }
}
