package com.example.android.miwok;

/**
 * Created by apoorva on 1/20/17.
 * Class describing the word object to be display in the Miwok app.
 */

public class Word {
    //Variable storing the Miwok translation of the word.
    private String mMiwokTranslation;

    //Variable storing the English translation of the word which will be default for the app.
    private String mDefaultTranslation;

    //Variable storing the resource ID of t6he Image associated with a word.
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    //Variable storing the resource ID of the audio associated with a word.
    private int mAudioResourceId;

    private final static int NO_IMAGE_PROVIDED = -1;


    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId) {
        this.mMiwokTranslation = miwokTranslation;
        this.mDefaultTranslation = defaultTranslation;
        this.mAudioResourceId = audioResourceId;
    }

    public Word (String defaultTranslation, String miwokTranslation, int imageResourceId,
                 int audioResourceId) {
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = miwokTranslation;
        this.mImageResourceId = imageResourceId;
        this.mAudioResourceId = audioResourceId;
    }

    public String getMiwokTranslation() {
        return this.mMiwokTranslation;
    }

    public String getDefaultTranslation() {
        return this.mDefaultTranslation;
    }

    public int getImageResourceId() {
        return this.mImageResourceId;
    }

    public int getmAudioResourceId() {
        return this.mAudioResourceId;
    }

    public boolean hasImage() {
        return (mImageResourceId != NO_IMAGE_PROVIDED);
    }

}
