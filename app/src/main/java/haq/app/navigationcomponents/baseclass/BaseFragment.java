package haq.app.navigationcomponents.baseclass;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import haq.app.navigationcomponents.Constants;
import haq.app.navigationcomponents.MainActivity;

public abstract class BaseFragment extends Fragment {

    private int tag = -1;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        tag = getArguments().getInt(Constants.TAG_KEY);
    }

    public int getFragmentTag() {
        return tag;
    }

    int getNavigationCount() {
        return ((MainActivity) getActivity()).getFragmentNavigation().getNavigationCount(tag);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        if(((MainActivity) getActivity()).getFragmentNavigation().getNavigationCount(tag) > 1) {
            ((MainActivity) getActivity()).showBackButton();
        } else {
            ((MainActivity) getActivity()).hideBackButton();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Log.d("homebutton", "onOptionsItemSelected: " + tag);


        if(item.getItemId() == android.R.id.home) {
            ((MainActivity) getActivity()).getFragmentNavigation().goBack(tag);
        }

        return super.onOptionsItemSelected(item);
    }

}
