package fr.android.moi.sportplus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Térence on 12/04/2017.
 */

public class historiqueFragment extends Fragment {

    public historiqueFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_historique, container, false);
        return view;
    }

    public static historiqueFragment newInstance(String contest, String contestFragment) {
        historiqueFragment fragment = new historiqueFragment();
        Bundle args = new Bundle();
        args.putString("historique", contest);
        args.putString("histo", contestFragment);
        fragment.setArguments(args);
        return fragment;
    }

    public interface OnFragmentInteractionListener {
        void Show(historiqueFragment fragment);
    }
}
