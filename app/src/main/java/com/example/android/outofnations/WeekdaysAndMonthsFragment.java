package com.example.android.outofnations;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WeekdaysAndMonthsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WeekdaysAndMonthsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeekdaysAndMonthsFragment extends Fragment {

///////////////////////

    /*
Constant value that represents languages provided for this word
*/
    private static final String ARABIC = "AR";
    private static final String ENGLISH = "EN";
    private static final String FRENCH = "FR";


    ///////////////////////

    MediaPlayer player;
    private AudioManager mAudioManger;


    String mSourceLang = ARABIC;
    String mDestinationLang = ENGLISH;


    AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int audiofocus) {

            if (audiofocus == AudioManager.AUDIOFOCUS_LOSS) {
                // we have lose the AUDIOFOCUS permenantly . so we gonna stop the mediaplayer and release it .
                player.stop();
                releaseMediaPlayer();
            } else if (audiofocus == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                // we have lose the AUDIOFOCUS temporary
                player.pause();
                player.seekTo(0); // this make the sound to restart from the begining again since our words are pretty short .
            } else if (audiofocus == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                player.setVolume(0.5f, 0.5f);
            } else if (audiofocus == AudioManager.AUDIOFOCUS_GAIN) {
                // resume playing the mediaplayer
                player.start();
            }
        }
    };


    /**
     * Clean up the media player by releasing its resources. after complete playing audio file
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (player != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            //  Toast.makeText(NumbersActivity.this, "playing Audio File Done", Toast.LENGTH_SHORT).show();
            player.release();
            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            player = null;

            mAudioManger.abandonAudioFocus(mAudioFocusChangeListener);
        }
    }

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    /////////////////////////////

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public WeekdaysAndMonthsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeekdaysAndMonthsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeekdaysAndMonthsFragment newInstance(String param1, String param2) {
        WeekdaysAndMonthsFragment fragment = new WeekdaysAndMonthsFragment();
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
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        /////////////////////////////////////////

        mAudioManger = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);


//////////////////////////////////////////////////
        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word(" لحظه ", " Moment ", "#1_French"
                , R.raw.ring, R.raw.times_en_moment, R.raw.ring, -1));

        words.add(new Word(" ثانيه ", " Second ", "#1_French"
                , R.raw.ring, R.raw.times_en_second, R.raw.ring, -1));

        words.add(new Word(" دقيقه ", " Minute ", "#1_French"
                , R.raw.ring, R.raw.times_en_minute, R.raw.ring, -1));

        words.add(new Word(" ساعه ", " Hour ", "#1_French"
                , R.raw.ring, R.raw.times_en_hour, R.raw.ring, -1));

        words.add(new Word(" فجر ", " Dawn ", "#1_French"
                , R.raw.ring, R.raw.times_en_dawn, R.raw.ring, -1));

        words.add(new Word(" صباح ", " Morning ", "#1_French"
                , R.raw.ring, R.raw.times_en_morning, R.raw.ring, -1));

        words.add(new Word(" نهار ", " Day ", "#1_French"
                , R.raw.ring, R.raw.times_en_day, R.raw.ring, -1));

        words.add(new Word(" مساء ", " Evening ", "#1_French"
                , R.raw.ring, R.raw.times_en_evening, R.raw.ring, -1));

        words.add(new Word(" اسبوع ", " Week ", "#1_French"
                , R.raw.ring, R.raw.times_en_week, R.raw.ring, -1));

        words.add(new Word(" شهر ", " Month ", "#1_French"
                , R.raw.ring, R.raw.times_en_month, R.raw.ring, -1));

        words.add(new Word(" سنه ", " Year ", "#1_French"
                , R.raw.ring, R.raw.times_en_year, R.raw.ring, -1));

        words.add(new Word(" قرن ", " Century ", "#1_French"
                , R.raw.ring, R.raw.times_en_century, R.raw.ring, -1));


        words.add(new Word(" السبت ", " Saturday ", "#1_French"
                , R.raw.ring, R.raw.times_en_saturday, R.raw.ring, -1));

        words.add(new Word(" الأحد ", " Sunday ", "#1_French"
                , R.raw.ring, R.raw.times_en_sunday, R.raw.ring, -1));

        words.add(new Word(" الاثنين ", " Monday ", "#1_French"
                , R.raw.ring, R.raw.times_en_monday, R.raw.ring, -1));

        words.add(new Word(" الثلاثاء ", " Tuesday ", "#1_French"
                , R.raw.ring, R.raw.times_en_tuesday, R.raw.ring, -1));

        words.add(new Word(" الأربعاء ", " Wednesday ", "#1_French"
                , R.raw.ring, R.raw.times_en_wednesday, R.raw.ring, -1));

        words.add(new Word(" الخميس ", " Thursday ", "#1_French"
                , R.raw.ring, R.raw.times_en_thursday, R.raw.ring, -1));

        words.add(new Word(" الجمعه ", " Friday ", "#1_French"
                , R.raw.ring, R.raw.times_en_friday, R.raw.ring, -1));

        words.add(new Word(" أمس ", " Yesterday ", "#1_French"
                , R.raw.ring, R.raw.times_en_yesterday, R.raw.ring, -1));

        words.add(new Word(" اليوم ", " Today ", "#1_French"
                , R.raw.ring, R.raw.times_en_today, R.raw.ring, -1));


        words.add(new Word(" غدا ", " Tommorrow ", "#1_French"
                , R.raw.ring, R.raw.times_en_tommorrow, R.raw.ring, -1));


        words.add(new Word(" يناير ", " January ", "#1_French"
                , R.raw.ring, R.raw.times_en_january, R.raw.ring, -1));

        words.add(new Word(" فبراير ", " February ", "#1_French"
                , R.raw.ring, R.raw.times_en_february, R.raw.ring, -1));

        words.add(new Word(" مارس ", " March ", "#1_French"
                , R.raw.ring, R.raw.times_en_march, R.raw.ring, -1));

        words.add(new Word(" أبريل ", " April ", "#1_French"
                , R.raw.ring, R.raw.times_en_april, R.raw.ring, -1));

        words.add(new Word(" مايو ", " May ", "#1_French"
                , R.raw.ring, R.raw.times_en_may, R.raw.ring, -1));

        words.add(new Word(" يونيو ", " June ", "#1_French"
                , R.raw.ring, R.raw.times_en_june, R.raw.ring, -1));

        words.add(new Word(" يوليو ", " July ", "#1_French"
                , R.raw.ring, R.raw.times_en_july, R.raw.ring, -1));

        words.add(new Word(" أغسطس ", " August ", "#1_French"
                , R.raw.ring, R.raw.times_en_august, R.raw.ring, -1));

        words.add(new Word(" سبتمبر ", " September ", "#1_French"
                , R.raw.ring, R.raw.times_en_september, R.raw.ring, -1));

        words.add(new Word(" أكتوبر ", " October ", "#1_French"
                , R.raw.ring, R.raw.times_en_october, R.raw.ring, -1));

        words.add(new Word(" نوفمبر ", " November ", "#1_French"
                , R.raw.ring, R.raw.times_en_november, R.raw.ring, -1));

        words.add(new Word(" ديسمبر ", " December ", "#1_French"
                , R.raw.ring, R.raw.times_en_december, R.raw.ring, -1));


        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        final WordAdapter adapter = new WordAdapter(getActivity(), words, mSourceLang, mDestinationLang, R.color.category_week);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        final ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Release the media player if it currently exists because we are about to play a different sound file
                releaseMediaPlayer();


                // Get the  Word object at the given position the user clicked on
                Word CurrentClickedWord = adapter.getItem(position);

                // Request audio focus for a playback
                int mRequestResult = mAudioManger.requestAudioFocus(mAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

                if (mRequestResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    // we have AUDIOFOCUS now

                    //all the comming lines are inside the if statement Cause if we don't recieve the AUDIOFOCUS
                    // then -->> we don't need to setup the MediaPlayer
                    String toast_message = "playing audio file for: " + CurrentClickedWord.getWordTranslation(mSourceLang);
                    Toast.makeText(getActivity(), toast_message, Toast.LENGTH_SHORT).show();

                    player = MediaPlayer.create(getActivity(), CurrentClickedWord.getAudioResourceId(mDestinationLang));
                    player.start();

                    /**
                     * Clean up the media player by releasing its resources.
                     * after complete playing audio file to free up Memory Resources for other operation & other device apps
                     */
                    player.setOnCompletionListener(mCompletionListener);
                }
            }
        });


        ////////////////////////////////
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
