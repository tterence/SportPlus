package fr.android.moi.sportplus;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView tV1, tV2, tV3;
    private EditText Et1, Et2, Et3;
    private int checked1,checked2;
    private static final int TAKE_PICTURE = 1;
    Uri imageUri;

    private String filemanagerstring;
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
        tV1 = (TextView) view.findViewById(R.id.textView3);
        Et1 = (EditText) view.findViewById(R.id.Nom); //nom du match
        Et2 = (EditText) view.findViewById(R.id.editText); // date du match
        Et3 = (EditText) view.findViewById(R.id.editText2); //lieu du match
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // in onCreate or any event where your want the user to
                // select a file
                // Checking camera availability
                if (!isDeviceSupportCamera()) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Sorry! Your device doesn't support camera",
                            Toast.LENGTH_LONG).show();
                    // will close the app if the device doesn't have camera
                } else {
                    String fileName = "new-photo-name.jpg";
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, fileName);
                    values.put(MediaStore.Images.Media.DESCRIPTION,"Image capture by camera");
                    //imageUri is the current activity attribute, define and save it for later usage (also in onSaveInstanceState)
                    imageUri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                    startActivityForResult(intent, TAKE_PICTURE);
                }
            }

        });
        Button Bstart = (Button) view.findViewById(R.id.contest);
        Bstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO begin thread and store data
                ContestBDD contestBDD = new ContestBDD(getContext());
                Contest contest = new Contest(rg1.getTransitionName(), Et1.getText().toString(),
                        rg2.getTransitionName(), Et2.getText().toString(),
                        Et3.getText().toString(), null, null, filemanagerstring);
                contestBDD.open();
                contestBDD.insertContest(contest);
                Contest contestFromBDD = contestBDD.getContest(contest.getNom());
                if(contestFromBDD != null){
                    Toast.makeText(getContext(),contestFromBDD.toString(), Toast.LENGTH_LONG).show();
                }


            }
        });
        return view;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

            if (requestCode == TAKE_PICTURE) {
                Uri selectedImageUri = null;
                selectedImageUri = imageUri;
                filemanagerstring = selectedImageUri.getPath();
            }

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
    /**
     * Checking device has camera hardware or not
     * */
    private boolean isDeviceSupportCamera() {
        // this device has a camera
// no camera on this device
        return getActivity().getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA);
    }

}
