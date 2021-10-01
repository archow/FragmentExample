package com.example.fragmentexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

//This activity will display our Dynamic Fragment dynamically
public class DynamicActivity extends AppCompatActivity {
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);

        //To dynamically add a fragment, create an instance of your framgent
        DynamicFragment myFragment = DynamicFragment
                .newInstance("Abe", "Chow");

        //Then get an instance of your Fragment manager
        FragmentManager fragmentManager = getSupportFragmentManager();

        //THEN get an instance of a Fragment Transaction object
        mFragmentTransaction = fragmentManager.beginTransaction();

        //Finally, perform a series of actions on your fragments
        //examples: showing a fragment, adding a fragment, removing one, adding it
        //to back stack, replacing a fragment, etc
        mFragmentTransaction
                .add(R.id.dynamic_fragment_container_view,
                        myFragment,
                        DynamicFragment.DYNAMIC_FRAGMENT_TAG)
                //we want to add this to backstack
                .addToBackStack(DynamicFragment.DYNAMIC_FRAGMENT_TAG)
                //this denotes that it is the end of one fragment transaction, which
                //is a series of "actions" done on a fragment
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mFragmentTransaction != null) {
            HelloFragment fragment = (HelloFragment) getSupportFragmentManager()
                    .findFragmentByTag(HelloFragment.HELLO_FRAGMENT_TAG);
            if (fragment != null) {
                mFragmentTransaction.remove(fragment);
            }
        }

    }
}