package com.example.fragmentexample;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DynamicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DynamicFragment extends Fragment {
    //this provides the full name or identifier of your class, in case you want to identify
    //using tags
    public static final String DYNAMIC_FRAGMENT_TAG = DynamicFragment.class.getSimpleName();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button mOpenHelloFragmentButton;
    private TextView mDynamicFragmentTextView;
    private EditText mInfoBackToActivityEt;
    private Button mInfoBackToActivityButton;
    private FragmentInteractionListener mListener;

    public DynamicFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DynamicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DynamicFragment newInstance(String param1, String param2) {
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_dynamic, container, false);
        mDynamicFragmentTextView = view.findViewById(R.id.dynamic_fragment_textview);
        String text = mParam1 + " " + mParam2;
        mDynamicFragmentTextView.setText(text);

        mOpenHelloFragmentButton = view.findViewById(R.id.open_fragment_button);
        mOpenHelloFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here we open up a new fragment, BUT we need a fragment manger
                //how do we get that?
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                //(let's create our fragmetn instance first)
                HelloFragment myHelloFragment = new HelloFragment();

                ft.replace(
                        R.id.dynamic_fragment_container_view,
                        myHelloFragment,
                        HelloFragment.HELLO_FRAGMENT_TAG)
                        .commit();
            }
        });

        mInfoBackToActivityEt = view.findViewById(R.id.info_back_to_activity_et);
        mInfoBackToActivityButton = view.findViewById(R.id.send_to_activity_btn);
        mInfoBackToActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here we want to pass whatever is in our edit text to this mehtod
                //so that the activity can use it
                String message = mInfoBackToActivityEt.getText().toString();
                mListener.onFragmentButtonClicked(message);
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInteractionListener) {
            mListener = (FragmentInteractionListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement FragmentInteractionListener");
        }
    }
}