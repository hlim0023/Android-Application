package com.example.Goalden;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;






    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ArrayList<PieEntry> entries = new ArrayList<>();
        int acceptanceCount= 0;
        int cognitiveCount= 0;
        int presentCount= 0;
        int contextCount= 0;
        int valuesCount= 0;
        int actionCount= 0;
        float total = 0;
        for(Activity activity: UserInfo.loggedUser.getActivities()){
            if(activity.getType() == ActivityType.Acceptance && activity.isComplete())
                acceptanceCount += 1;
            else if (activity.getType() == ActivityType.CognitiveDefusion && activity.isComplete())
                cognitiveCount += 1;
            else if (activity.getType() == ActivityType.BeingPresent && activity.isComplete())
                presentCount += 1;
            else if (activity.getType() == ActivityType.SelfAsContext && activity.isComplete())
                contextCount += 1;
            else if (activity.getType() == ActivityType.Values && activity.isComplete())
                valuesCount += 1;
            else if (activity.getType() == ActivityType.CommittedAction && activity.isComplete())
                actionCount += 1;
            total += 1;
        }
        ProgressBar profileProgressBar1 = view.findViewById(R.id.progress_bar_1);
        profileProgressBar1.setProgress(acceptanceCount);
        ProgressBar profileProgressBar2 = view.findViewById(R.id.progress_bar_2);
        profileProgressBar2.setProgress(cognitiveCount);
        ProgressBar profileProgressBar3 = view.findViewById(R.id.progress_bar_3);
        profileProgressBar3.setProgress(presentCount);
        ProgressBar profileProgressBar4 = view.findViewById(R.id.progress_bar_4);
        profileProgressBar4.setProgress(contextCount);
        ProgressBar profileProgressBar5 = view.findViewById(R.id.progress_bar_5);
        profileProgressBar5.setProgress(valuesCount);
        ProgressBar profileProgressBar6 = view.findViewById(R.id.progress_bar_6);
        profileProgressBar6.setProgress(actionCount);

//        Button settingsButton = view.findViewById(R.id.settings_button);
//        settingsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Navigate to the SettingsFragment
//                NavController navController = Navigation.findNavController(v);
//                navController.navigate(R.id.action_profileFragment_to_settingsFragment);
//            }
//        });
        return view;
    }

}