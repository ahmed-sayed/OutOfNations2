package com.example.android.outofnations;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
 * {@link NumbersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NumbersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NumbersFragment extends Fragment {


    ///////////////

    /*
Constant value that represents languages provided for this word
*/
    private static final String ARABIC = "AR";
    private static final String ENGLISH = "EN";
    private static final String FRENCH = "FR";

    //////////////////////////////

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


    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        /////////////////////////////////////////

        mAudioManger = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);


//////////////////////////////////////////////////
        Bundle bundle = getArguments();
        if (bundle != null) {
            mSourceLang = bundle.getString("selected_source");
            mDestinationLang = bundle.getString("selected_des");
           // Log.v("source" , "111111111111111111111111");
            Toast.makeText(getContext(), "selected lang is " + mSourceLang + " ***  des is  :  " + mDestinationLang, Toast.LENGTH_SHORT).show();

        }



        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word(" ( صفر ) ٠ ", " 0 ( zero )  ", " 0 ( zéro ) "
                , R.raw.ring, R.raw.numbers_en_num0, R.raw.ring, 0));

        words.add(new Word(" ( واحد ) ١ ", " 1 ( One ) ", "1 ( un )"
                , R.raw.ring, R.raw.numbers_en_num1, R.raw.ring, R.drawable.number_one));

        words.add(new Word("( اثنان ) ٢ ", " 2 ( two ) ", " 2 ( deux )"
                , R.raw.ring, R.raw.numbers_en_num2, R.raw.ring, R.drawable.number_two));

        words.add(new Word(" ( ثلاثه ) ٣ ", " 3 ( three ) ", " 3 ( trois )"
                , R.raw.ring, R.raw.numbers_en_num3, R.raw.ring, R.drawable.number_three));

        words.add(new Word(" ( اربعه ) ٤ ", " 4 ( four ) ", " 4 ( quatre )"
                , R.raw.ring, R.raw.numbers_en_num4, R.raw.ring, R.drawable.number_four));

        words.add(new Word("  ( خمسه ) ٥ ", " 5 ( five )", " 5 ( cinq ) "
                , R.raw.ring, R.raw.numbers_en_num5, R.raw.ring, R.drawable.number_five));

        words.add(new Word(" ( سته ) ٦ ", " 6 ( six )", " 6 ( siz )"
                , R.raw.ring, R.raw.numbers_en_num6, R.raw.ring, R.drawable.number_six));

        words.add(new Word(" ( سبعه ) ٧ ", " 7 ( seven ) ", " 7 ( sept )"
                , R.raw.ring, R.raw.numbers_en_num7, R.raw.ring, R.drawable.number_seven));

        words.add(new Word("  ( ثمانيه ) ٨ ", " 8 ( eight ) ", " 8 ( huit )"
                , R.raw.ring, R.raw.numbers_en_num8, R.raw.ring, R.drawable.number_eight));

        words.add(new Word(" ( تسعه ) ٩ ", " 9 ( nine )", " 9 ( neuf )"
                , R.raw.ring, R.raw.numbers_en_num9, R.raw.ring, R.drawable.number_nine));

        words.add(new Word(" ( عشره ) ١٠ ", " 10 ( ten ) ", " 10 ( dix )"
                , R.raw.ring, R.raw.numbers_en_num10, R.raw.ring, R.drawable.number_ten));

        words.add(new Word(" ( احد عشر ) ۱۱ ", " 11 ( eleven ) ", " 11 ( onze )"
                , R.raw.ring, R.raw.numbers_en_num11, R.raw.ring, -1));

        words.add(new Word(" ( اثنا عشر ) ۱۲ ", " 12 ( tweleve ) ", " 12 ( douze )"
                , R.raw.ring, R.raw.numbers_en_num12, R.raw.ring, -1));

        words.add(new Word(" ( ثلاثه عشر ) ۱۳ ", " 13 ( thirteen ) ", " 13 ( treize )"
                , R.raw.ring, R.raw.numbers_en_num13, R.raw.ring, -1));


        words.add(new Word("  ( اربعه عشر ) ۱٤ ", " 14 ( fourteen )", " 14 ( quatorze )"
                , R.raw.ring, R.raw.numbers_en_num14, R.raw.ring, -1));


        words.add(new Word(" ( خمسه عشر ) ۱۵ ", " 15 ( fifteen )", " 15 ( qunize )"
                , R.raw.ring, R.raw.numbers_en_num15, R.raw.ring, -1));


        words.add(new Word("  ( سته عشر ) ۱٦ ", " 16 ( sixteen )", " 16 ( seize )"
                , R.raw.ring, R.raw.numbers_en_num16, R.raw.ring, -1));


        words.add(new Word(" ( سبعه عشر ) ۱۷ ", " 17 ( seventeen )", " 17 ( dix-sept )"
                , R.raw.ring, R.raw.numbers_en_num17, R.raw.ring, -1));


        words.add(new Word("  ( ثمانيه عشر ) ۱۸ ", " 18 ( eighteen )", " 18 ( dix-huit )"
                , R.raw.ring, R.raw.numbers_en_num18, R.raw.ring, -1));


        words.add(new Word(" ( تسعه عشر ) ۱۹ ", " 19 ( nineteen )", " 19 ( dix-huit )"
                , R.raw.ring, R.raw.numbers_en_num19, R.raw.ring, -1));


        words.add(new Word(" ( عشرون ) ۲۰ ", " 20 ( twenty )", " 20 ( vingt )"
                , R.raw.ring, R.raw.numbers_en_num20, R.raw.ring, -1));


        words.add(new Word("  ( ثلاثون ) ۳۰ ", " 30 ( thirty )", " 30 ( trente )"
                , R.raw.ring, R.raw.numbers_en_num30, R.raw.ring, -1));


        words.add(new Word(" ( اربعون ) ٤۰ ", " 40 ( forty )", " 40 ( quarante )"
                , R.raw.ring, R.raw.numbers_en_num40, R.raw.ring, -1));


        words.add(new Word("  ( خمسون ) ۵۰ ", " 50 ( fifty )", " 50 ( cinquante )"
                , R.raw.ring, R.raw.numbers_en_num50, R.raw.ring, -1));


        words.add(new Word("  ( ستون ) ٦۰ ", " 60 ( sixty ) ", " 60 ( soixante )"
                , R.raw.ring, R.raw.numbers_en_num60, R.raw.ring, -1));


        words.add(new Word(" ( سبعون ) ۷۰ ", " 70 ( seventy )", " 70 ( soixante-dix )"
                , R.raw.ring, R.raw.numbers_en_num70, R.raw.ring, -1));


        words.add(new Word(" ( ثمانون ) ۸۰ ", " 80 ( eighty )", " 80 ( quatre-vingts )"
                , R.raw.ring, R.raw.numbers_en_num80, R.raw.ring, -1));


        words.add(new Word(" ( تسعون ) ۹۰ ", " 90 ( ninety )", " 90 ( quatre-vingt-dix )"
                , R.raw.ring, R.raw.numbers_en_num90, R.raw.ring, -1));


        words.add(new Word(" ( مائه ) ۱۰۰ ", " 100 ( one hundred ) ", " 100 ( cent )"
                , R.raw.ring, R.raw.numbers_en_num100, R.raw.ring, -1));


        words.add(new Word("( ألف ) ۱۰۰۰ ", " 1,000 ( one thousand )", " 1,000 ( mille ) "
                , R.raw.ring, R.raw.numbers_en_num1000, R.raw.ring, -1));


        words.add(new Word("  ( مليون ) ۱۰۰۰,۰۰۰ ", " 1,000,000 ( million )", " 1,000,000 ( un-million )"
                , R.raw.ring, R.raw.numbers_en_num1000000, R.raw.ring, -1));


        words.add(new Word("  ( مليار ) ۱۰۰۰,۰۰۰,۰۰۰ ", "  1,000,000,000 (  billion ) ", "1,000,000,000 ( un-milliard )"
                , R.raw.ring, R.raw.numbers_en_num1000000000, R.raw.ring, -1));


        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        final WordAdapter adapter = new WordAdapter(getActivity(), words, mSourceLang, mDestinationLang, R.color.category_numbers);

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
                    String toast_message = "playing audio file for Number : " + CurrentClickedWord.getWordTranslation(mSourceLang);
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
