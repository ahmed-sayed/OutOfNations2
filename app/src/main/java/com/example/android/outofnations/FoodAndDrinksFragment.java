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
 * {@link FoodAndDrinksFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FoodAndDrinksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodAndDrinksFragment extends Fragment {
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

    public FoodAndDrinksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodAndDrinksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodAndDrinksFragment newInstance(String param1, String param2) {
        FoodAndDrinksFragment fragment = new FoodAndDrinksFragment();
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

        ///// FOOOOOODDD //////////////
        words.add(new Word(" مأكولات  ", " Eatables", "#1_French"
                , R.raw.ring, R.raw.food_en_eatables, R.raw.ring, -1));

        words.add(new Word(" وجبه  ", " Meal ", "#1_French"
                , R.raw.ring, R.raw.food_en_meal, R.raw.ring, -1));

        words.add(new Word(" الفطار  ", " Breakfast ", "#1_French"
                , R.raw.ring, R.raw.food_en_breakfast, R.raw.ring, -1));

        words.add(new Word(" الغداء  ", " Lunch ", "#1_French"
                , R.raw.ring, R.raw.food_en_lunch, R.raw.ring, -1));

        words.add(new Word(" العشاء  ", " Dinner ", "#1_French"
                , R.raw.ring, R.raw.food_en_dinner, R.raw.ring, -1));

        words.add(new Word(" لحم  ", " Meat ", "#1_French"
                , R.raw.ring, R.raw.meat, R.raw.ring, -1));

        words.add(new Word(" شوربه  ", " Soup ", "#1_French"
                , R.raw.ring, R.raw.food_en_soup, R.raw.ring, -1));

        words.add(new Word(" أرز  ", " Rice ", "#1_French"
                , R.raw.ring, R.raw.food_en_rice, R.raw.ring, -1));

        words.add(new Word(" مكرونه  ", " Macaroni ", "#1_French"
                , R.raw.ring, R.raw.macaroni, R.raw.ring, -1));

        words.add(new Word(" بسله ", " Peas ", "#1_French"
                , R.raw.ring, R.raw.food_en_peas, R.raw.ring, -1));

        words.add(new Word(" فاصوليا ", " Beans ", "#1_French"
                , R.raw.ring, R.raw.food_en_beans, R.raw.ring, -1));

        words.add(new Word(" بطاطس ", " Potatoes ", "#1_French"
                , R.raw.ring, R.raw.food_en_potatoes, R.raw.ring, -1));

        words.add(new Word(" طماطم ", " Tomatoes ", "#1_French"
                , R.raw.ring, R.raw.food_en_tomatoes, R.raw.ring, -1));

        words.add(new Word(" جزر ", " Carrot ", "#1_French"
                , R.raw.ring, R.raw.food_en_carrot, R.raw.ring, -1));

        words.add(new Word(" جمبرى  ", " Shrimp ", "#1_French"
                , R.raw.ring, R.raw.food_en_shrimp, R.raw.ring, -1));

        words.add(new Word(" سمك  ", " Fish ", "#1_French"
                , R.raw.ring, R.raw.food_en_fish, R.raw.ring, -1));

        words.add(new Word(" سلطه  ", " Salad ", "#1_French"
                , R.raw.ring, R.raw.food_en_salad, R.raw.ring, -1));

        words.add(new Word(" خبز  ", " Bread ", "#1_French"
                , R.raw.ring, R.raw.food_en_bread, R.raw.ring, -1));

        words.add(new Word(" عسل  ", " Honey ", "#1_French"
                , R.raw.ring, R.raw.food_en_honey, R.raw.ring, -1));

        words.add(new Word(" جبن  ", " Cheese ", "#1_French"
                , R.raw.ring, R.raw.food_en_cheese, R.raw.ring, -1));

        words.add(new Word(" زبد  ", " Butter ", "#1_French"
                , R.raw.ring, R.raw.food_en_butter, R.raw.ring, -1));


        words.add(new Word(" بصل ", " Onion ", "#1_French"
                , R.raw.ring, R.raw.food_en_onion, R.raw.ring, -1));

        words.add(new Word(" ملح  ", " Salt ", "#1_French"
                , R.raw.ring, R.raw.food_en_salt, R.raw.ring, -1));

        words.add(new Word(" فلفل  ", " Pepper ", "#1_French"
                , R.raw.ring, R.raw.food_en_pepper, R.raw.ring, -1));

        words.add(new Word(" عجين (عجه)  ", " Omelet ", "#1_French"
                , R.raw.ring, R.raw.food_en_omelet, R.raw.ring, -1));

        words.add(new Word(" مسلوقه جيدا  ", " Hard boiled ", "#1_French"
                , R.raw.ring, R.raw.food_en_hard_boiled, R.raw.ring, -1));

        words.add(new Word(" نصف مسلوقه  ", " Soft boiled ", "#1_French"
                , R.raw.ring, R.raw.food_en_soft_boiled, R.raw.ring, -1));

        words.add(new Word(" مشوى  ", " Roasted ", "#1_French"
                , R.raw.ring, R.raw.food_en_roasted, R.raw.ring, -1));

        words.add(new Word(" مخبوز  ", " Baked ", "#1_French"
                , R.raw.ring, R.raw.food_en_baked, R.raw.ring, -1));

        words.add(new Word(" نئ ", " Uncooked ", "#1_French"
                , R.raw.ring, R.raw.food_en_uncooked, R.raw.ring, -1));

        words.add(new Word(" مجمد ", " Frozen ", "#1_French"
                , R.raw.ring, R.raw.food_en_frozen, R.raw.ring, -1));

        //////// Drinks ////////

        words.add(new Word(" مشروبات  ", " Drinkables ", "#1_French"
                , R.raw.ring, R.raw.food_en_drinkables, R.raw.ring, -1));

        words.add(new Word(" بارد ", " Cold ", "#1_French"
                , R.raw.ring, R.raw.food_en_cold, R.raw.ring, -1));

        words.add(new Word(" ساخن ", " Hot ", "#1_French"
                , R.raw.ring, R.raw.food_en_hot, R.raw.ring, -1));

        words.add(new Word(" قهوه ", " Coffee ", "#1_French"
                , R.raw.ring, R.raw.food_en_coffee, R.raw.ring, -1));

        words.add(new Word(" شاى ", " Tea ", "#1_French"
                , R.raw.ring, R.raw.food_en_tea, R.raw.ring, -1));

        words.add(new Word(" كوكا ", " Coca ", "#1_French"
                , R.raw.ring, R.raw.food_en_coca, R.raw.ring, -1));

        words.add(new Word(" شوكولاته ", " Chocolate ", "#1_French"
                , R.raw.ring, R.raw.food_en_chocolate, R.raw.ring, -1));

        words.add(new Word(" حليب ", " Milk ", "#1_French"
                , R.raw.ring, R.raw.food_en_milk, R.raw.ring, -1));


        //// Fruits //////////

        words.add(new Word(" فاكهه ", " Fruit ", "#1_French"
                , R.raw.ring, R.raw.food_en_fruit, R.raw.ring, -1));

        words.add(new Word(" تفاح ", " Apple ", "#1_French"
                , R.raw.ring, R.raw.food_en_apple, R.raw.ring, -1));

        words.add(new Word(" كمثرى ", " Appear ", "#1_French"
                , R.raw.ring, R.raw.food_en_appear, R.raw.ring, -1));

        words.add(new Word(" خوخ ", " Peaches ", "#1_French"
                , R.raw.ring, R.raw.food_en_peaches, R.raw.ring, -1));

        words.add(new Word(" موز ", " Banana ", "#1_French"
                , R.raw.ring, R.raw.food_en_banana, R.raw.ring, -1));

        words.add(new Word(" عنب ", " Grapes ", "#1_French"
                , R.raw.ring, R.raw.food_en_grapes, R.raw.ring, -1));

        words.add(new Word(" بطيخ ", " Watermelon ", "#1_French"
                , R.raw.ring, R.raw.food_en_watermelon, R.raw.ring, -1));

        words.add(new Word(" شمام ", " Melon ", "#1_French"
                , R.raw.ring, R.raw.food_en_melon, R.raw.ring, -1));

        //// Juices /////

        words.add(new Word(" عصير ", " Juice ", "#1_French"
                , R.raw.ring, R.raw.food_en_juice, R.raw.ring, -1));

        words.add(new Word(" ليموناده ", " lemonade ", "#1_French"
                , R.raw.ring, R.raw.food_en_lemonade, R.raw.ring, -1));

        words.add(new Word(" برتقال ", " Orange ", "#1_French"
                , R.raw.ring, R.raw.food_en_orange, R.raw.ring, -1));

        words.add(new Word(" ليمون ", " Lemon ", "#1_French"
                , R.raw.ring, R.raw.food_en_lemon, R.raw.ring, -1));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        final WordAdapter adapter = new WordAdapter(getActivity(), words, mSourceLang, mDestinationLang, R.color.category_food);

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
                    String toast_message = "playing audio file for  : " + CurrentClickedWord.getWordTranslation(mSourceLang);
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
    }    // TODO: Rename method, update argument and hook method into UI event

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
