package com.example.android.miwok;

public class Word {

    private int mAudioResourceId;

    private String mDefaultTranslation;

    private String mMiwokTranslation;

    private int mImageResourceId = NO_IMAGE_PROVIDED; // İlk Değerinde resim yok

    private static final int NO_IMAGE_PROVIDED = -1; // Resim olmadığının temsili


    /**
     * @param mDefaultTranslation : Kullanıcının aşina olduğu dilde olan bir sözcüktür.
     * @param mMiwokTranslation   : Kullanıcının Miwok sözcüğüdür.
     */
    // İkinci Constructer
    public Word(String mDefaultTranslation, String mMiwokTranslation, int mImageResourceId, int mAudioResourceId) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mImageResourceId = mImageResourceId;
        this.mAudioResourceId = mAudioResourceId;

    }

    // İlk Constructer
    public Word(String mDefaultTranslation, String mMiwokTranslation, int mAudioResourceId) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mAudioResourceId = mAudioResourceId;
    }

    // Kelimenin varsayılan çevirisini getirir.
    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    // Kelimenin Miwok Çevirisini getirir.
    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    // Resim var mı yok mu metodu
    public boolean hasImage() {
        if (mImageResourceId != NO_IMAGE_PROVIDED) {
            return true;

        } else {

            return false;

        }
    }

    public int getmAudioResourceId() {

        return mAudioResourceId;

    }


}
