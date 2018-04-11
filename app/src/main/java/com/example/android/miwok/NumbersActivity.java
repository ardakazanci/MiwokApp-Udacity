package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    // MediaPlayer tamamlandığında bu dinleyici tetiklenir ve sonlandırılır.
    private AudioManager audioManager;

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {

            releaseMediaPlayer();

        }
    };

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };


    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };
    /*

        Etkinlik durdurulduğunda, medya oynatıcı kaynaklarını serbest bırakın çünkü
         daha fazla ses çalıyor.

     */

    @Override
    protected void onStop() {
        super.onStop();

        releaseMediaPlayer();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // 1. Versiyon ArrayList<String> words = new ArrayList<String>();

        final ArrayList<Word> words = new ArrayList<Word>();


        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));

        /*
        words.add("one");
        words.add("two");
        words.add("three");
        words.add("four");
        words.add("five");
        words.add("six");
        words.add("seven");
        words.add("eight");
        words.add("nine");
        words.add("ten"); */


        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Eğer dolu ise serbest bırakıp tekrar başlatacağız.

                releaseMediaPlayer();
                Word word = words.get(i);

                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                int result = audioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getmAudioResourceId());

                    // Start the audio file
                    mediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });





        /* GridView gridView = (GridView) findViewById(R.id.gridList);
        gridView.setAdapter(itemsAdapter); */




        /* LinearLayout ly = (LinearLayout) findViewById(R.id.rootView);
         TextView textView = new TextView(this); -> Hata basar. Döngü içerisinde her adımda tek tek oluşturulmalı
        int wordsSize = words.size();
        int i = 0;

        while (i <= wordsSize - 1) {
            TextView textView = new TextView(this);
            textView.setText(words.get(i));
            ly.addView(textView);
            i++;

        }


        for(i=0;i < wordsSize;i++){

            Log.v("NumbersActivity ", "Words -> " + words.get(i));

        } */


        /*
        TextView wordView1 = new TextView(this);
        wordView1.setText(words.get(0));
        ly.addView(wordView1);

        TextView wordView2 = new TextView(this);
        wordView2.setText(words.get(1));
        ly.addView(wordView2);

        TextView wordView3 = new TextView(this);
        wordView3.setText(words.get(2));
        ly.addView(wordView3);

        TextView wordView4 = new TextView(this);
        wordView4.setText(words.get(3));
        ly.addView(wordView4);

        TextView wordView5 = new TextView(this);
        wordView5.setText(words.get(4));
        ly.addView(wordView5);

        TextView wordView6 = new TextView(this);
        wordView6.setText(words.get(5));
        ly.addView(wordView6);

        TextView wordView7 = new TextView(this);
        wordView7.setText(words.get(6));
        ly.addView(wordView7);

        TextView wordView8 = new TextView(this);
        wordView8.setText(words.get(7));
        ly.addView(wordView8);

        TextView wordView9 = new TextView(this);
        wordView9.setText(words.get(8));
        ly.addView(wordView9);

        TextView wordView10 = new TextView(this);
        wordView10.setText(words.get(9));
        ly.addView(wordView10);
        */

    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }

}
