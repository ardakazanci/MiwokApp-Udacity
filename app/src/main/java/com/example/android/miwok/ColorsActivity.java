package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private AudioManager audioManager;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    /*
    * Ses odağı her değiştiğinde bu dinleyici tetiklenir.
    * Başka bir uygulama veya cihaz nedeniyle ses odağı etkilenebilir.
    */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // -----
                // The AUDIOFOCUS_LOSS_TRANSIENT şu anlama geliyor :
                // bu durum ses odağı kaybedildiği anlamına gelir.
                // -----
                // AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK durumu şu anlama geliyor:
                // uygulamamızın ses çalmaya devam etmesine izin verilir ancak daha düşük bir ses düzeyinde.
                // -----
                //Her iki durumda da aynı şekilde uygulanır çünkü uygulamamız kısa ses dosyaları çalıyordur.
                // -----


                // Oynatmayı duraklatın ve oynatıcıyı dosyanın başlangıcına sıfırlayın. Bu şekilde yapabiliriz
                // oynatmaya devam ettiğimizde baştan itibaren kelimeyi oynat.
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {

            // AUDIOFOCUS_GAIN durumu, tekrar odağı aldığımız ve oynatmaya devam edebileceğimiz anlamına gelir.
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {

                // AUDIOFOCUS_LOSS durumu, ses odağını kaybettiğimiz anlamına gelir ve
                // Oynatmayı durdur ve kaynakları temizle
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);


        // Ses odağu istemek için gerekli kurulum
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow,
                R.raw.color_mustard_yellow));
        words.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow,
                R.raw.color_dusty_yellow));
        words.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("black", "kululli", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white));


        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);


        // Her bir öğe için tıklama olayı.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Kullanıcının kullandığı konumda ki nesne alınıyor.
                releaseMediaPlayer();

                Word word = words.get(i); // İlgili Model'e eklenen verinin position bilgisi alınıyor.

                // Uygulamanın bir  kısa ses dosyası oynatması gerekiyor, bu yüzden kısa bir süre
                // ile AUDIOFOCUS GAIN TRANSIENT ile ses odağı isteyeceğiz.
                int result = audioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                   // Şu anda ses odaklarımız var

                    // Geçerli kelime ile ilişkili ses kaynağı için {@link Media Player} oluşturun ve kurun
                    mediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getmAudioResourceId());

                    // Start the audio file
                    mediaPlayer.start();


                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                }

            }
        });





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
