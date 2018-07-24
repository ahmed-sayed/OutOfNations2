package com.example.android.outofnations;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    /*
Constant value that represents languages provided for this word
*/
    private static final String ARABIC = "AR";
    private static final String ENGLISH = "EN";
    private static final String FRENCH = "FR";


    ///////////////////////////////////////////////

    public static String mSource_lang = ARABIC;
    public static String mDestination_lang = ENGLISH;
    //////////////////////


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.fragment_home, container, false);



//Populate the spinner in the fragment
        Spinner spinner_source = (Spinner) rootview.findViewById(R.id.spinner_Source_lang);
        Spinner spinner_destination = (Spinner) rootview.findViewById(R.id.spinner_Destination_lang);


///////////////////////////////


// Creating adapter for spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(rootview.getContext(), R.array.Languages_array,
                android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(rootview.getContext(), R.array.Languages_array,
                android.R.layout.simple_spinner_item);


// Drop down layout style - list view with radio button
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        adapter2.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);

// attaching data adapter to spinner
        spinner_source.setAdapter(adapter);
        spinner_destination.setAdapter(adapter2);


        spinner_source.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selected = parentView.getItemAtPosition(position).toString();
                Context context = parentView.getContext();
                CharSequence toast_text = mSource_lang;
                int duration = Toast.LENGTH_SHORT;


                switch (position) {
                    case 0:
                        mSource_lang = ARABIC;
                        toast_text = "Arabic";
                        break;
                    case 1:
                        mSource_lang = ENGLISH;
                        toast_text = "English";
                        break;
                    case 2:
                        mSource_lang = FRENCH;
                        toast_text = "French";
                        break;
                    default:
                        break;
                }
                Toast toast = Toast.makeText(context, "Selected Source Language is : " + mSource_lang, duration);
                toast.show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });



        spinner_destination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selected = parentView.getItemAtPosition(position).toString();
                Context context = parentView.getContext();
                CharSequence toast_text = mDestination_lang;
                int duration = Toast.LENGTH_SHORT;


                switch (position) {
                    case 0:
                        mDestination_lang = ARABIC;
                        toast_text = "Arabic";
                        break;
                    case 1:
                        mDestination_lang = ENGLISH;
                        toast_text = "English";
                        break;
                    case 2:
                        mDestination_lang = FRENCH;
                        toast_text = "French";
                        break;
                    default:
                        break;
                }
                Toast toast = Toast.makeText(context, "Selected Destination Language is : " + mDestination_lang, duration);
                toast.show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        //////////////*********************
        NumbersFragment mNumbersFragment = new NumbersFragment();
        Bundle bundle = new Bundle();
        bundle.putString("selected_source", mSource_lang);
        bundle.putString("selected_des", mDestination_lang);
        mNumbersFragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction();
        ////////////////*********************


        // Inflate the layout for this fragment


        return rootview;
    }

}
