package fr.android.moi.sportplus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

/**
 * Created by Admin on 05/04/2017.
 */
public class contestFragment extends Fragment {
    private String mParam1;
    private String mParam2;
    private static final String ARG_FRAG_TYPE = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RadioGroup rg1;
    private RadioGroup rg2;
    private int checked1,checked2;
    public contestFragment(){

    }
    public static contestFragment newInstance(String param1, String param2){
        contestFragment fragment = new contestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FRAG_TYPE, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_FRAG_TYPE);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_contest, container,
                false);
        rg1 = (RadioGroup) view.findViewById(R.id.RG1);
        checked1 = rg1.getCheckedRadioButtonId();
        rg2 = (RadioGroup) view.findViewById(R.id.RG2);
        checked2 = rg2.getCheckedRadioButtonId();
        Button Bstart = (Button) view.findViewById(R.id.contest);
        Bstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO begin thread and store data

            }
        });
        return view;
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Object ref);
    }
}
