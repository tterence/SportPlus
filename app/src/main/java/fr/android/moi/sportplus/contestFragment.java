package fr.android.moi.sportplus;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Admin on 05/04/2017.
 */
public class contestFragment extends Fragment implements onContestFragmentCreated.OnFragmentInteractionListener{
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private String mParam1;
    private String mParam2;
    private static final String ARG_FRAG_TYPE = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RadioGroup rg1,rg2,rg3;
    private TextView tV1;
    private EditText Et1, Et2, Et3;
    private int checked1,checked2, checked3,time;
    private static final int TAKE_PICTURE = 1;
    Uri imageUri;
    private Contest contest;
    private String filemanagerstring;
    private onContestFragmentCreated fragmentCreated;
    private OnFragmentInteractionListener mListener;
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
        final RadioButton rb1 = (RadioButton) view.findViewById(checked1);
        rg2 = (RadioGroup) view.findViewById(R.id.RG2);
        rg3 = (RadioGroup) view.findViewById(R.id.RG3);
        checked2 = rg2.getCheckedRadioButtonId();
        checked3 = rg3.getCheckedRadioButtonId();
        RadioButton rb = (RadioButton) view.findViewById(R.id.RB5);
        RadioButton rb3 = (RadioButton) view.findViewById(checked3);
        if (rb3.getId() == rb.getId())
            time = 60;
        else
            time = 120;
        final RadioButton rb2 = (RadioButton) view.findViewById(checked2);
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

                //user permission
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.

                }
                takePhoto();

            }

        });
        final Button Bstart = (Button) view.findViewById(R.id.contest);
        Bstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO begin thread and store data
                ContestBDD contestBDD = new ContestBDD(getContext());
                contest = new Contest(rb1.getText().toString(), Et1.getText().toString(),
                rb2.getText().toString(), Et2.getText().toString(),
                Et3.getText().toString(), "4-4-2", "4-4-2", filemanagerstring);
                contestBDD.open();
                contestBDD.insertContest(contest);
                if (mListener != null)
                    mListener.onFragmentInteraction(null);
                //Show(new onContestFragmentCreated(),contest,time);
                contestBDD.close();
            }
        });
        return view;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

            if (requestCode == TAKE_PICTURE) {
                Uri selectedImageUri;
                selectedImageUri = imageUri;
                filemanagerstring = selectedImageUri.getPath();
            }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.return;
                    // Checking camera availability
                    if (!isDeviceSupportCamera()) {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Sorry! Your device doesn't support camera",
                                Toast.LENGTH_LONG).show();
                    } else {
                        takePhoto();
                    }
                    return;
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this.getContext(), "External storage permission is denied", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
            // other 'case' lines to check for other
            // permissions this app might request
            default:super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        }
    }

    public void takePhoto(){
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        filemanagerstring = imageUri.getPath();
        getActivity().startActivityForResult(intent, 100);
    }

    @Override
    public void onFragmentInteraction(Object ref) {
        System.out.println((String) ref);
        Toast.makeText(getContext(), (String) ref, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Show(onContestFragmentCreated fragment, Contest contest, int time) {
        fragment = onContestFragmentCreated.newInstance(contest.getId(),contest.getNom(), time);
        FragmentTransaction fgt = getActivity().getSupportFragmentManager().beginTransaction();
        fgt.replace(R.id.fragment_contest, fragment).commit();
        fgt.addToBackStack("new fragment");
    }
    public Contest getContest(){
        return contest;
    }
    public int getTime(){
        return time;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Object ref);
        void Show(contestFragment fragment);
    }
}
