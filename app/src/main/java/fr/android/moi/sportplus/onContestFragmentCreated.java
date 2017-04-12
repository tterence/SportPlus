package fr.android.moi.sportplus;


import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by Térence on 11/04/2017.
 */

public class onContestFragmentCreated extends Fragment{
    private String mParam1;
    private int mParam2;
    private int mParam3;
    private View view;
    private TextView score, info_detail;
    private RelativeLayout layout;
    private TextView chronometer;
    private Contest contest;
    private CountDownTimer downTimer = null;
    private ImageView imageView;
    public onContestFragmentCreated(){

    }
    public static onContestFragmentCreated newInstance(int id, String nom, int time){
        onContestFragmentCreated fragment = new onContestFragmentCreated();
        Bundle args = new Bundle();
        args.putString("nom", nom);
        args.putInt("id", id);
        args.putInt("time",time);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString("nom");
            mParam2 = getArguments().getInt("id");
            mParam3 = getArguments().getInt("time");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_oncontest, container, false);

        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                chronometer = (TextView) view.findViewById(R.id.chronometer);
                score = (TextView) view.findViewById(R.id.score);
                info_detail = (TextView) view.findViewById(R.id.match_detail);
                ContestBDD contestBDD = new ContestBDD(getContext());
                contestBDD.open();
                contest = contestBDD.getContest(mParam1);
                imageView = (ImageView) view.findViewById(R.id.photo);
                if (contest.getImage()==null)
                    imageView.setVisibility(View.INVISIBLE);
                contestBDD.close();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        startTimer();
                        imageView.setImageURI(Uri.parse(contest.getImage()));
                        info_detail.setText(toString(contest));
                        score.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                layout = (RelativeLayout) view.findViewById(R.id.detail);
                                layout.setVisibility(View.VISIBLE);
                            }
                        });
                    }

                    private String toString(Contest contest) {
                        String text = getResources().getString(R.string.info, contest.getId(), contest.getNom(), contest.getEdom(), contest.getEext(),
                                contest.getDate(), contest.getLieu(), contest.getComposition_dom(), contest.getComposition_ext());
                        return text;
                    }
                });
            }
        }).start();
        return view;
    }
    public void startTimer(){
        downTimer = new CountDownTimer(mParam3*1000,0){

            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished /1000);
                int minutes = seconds % 60;
                String text = getResources().getString(R.string.timelapse,minutes,seconds);
                chronometer.setText(text);
            }

            @Override
            public void onFinish() {
                String text = getResources().getString(R.string.timeelapsed, contest.getEdom(), contest.getEext());
                chronometer.setText(text);
            }
        };
        downTimer.start();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Object ref);
        void Show(onContestFragmentCreated fragment, Contest contest, int time);
    }
}
