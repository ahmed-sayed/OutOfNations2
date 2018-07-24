/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.outofnations;

/**
 * {@link Word} represents a vocabulary word that the user wants to learn.
 * It contains a default translation, a Miwok translation, and an image for that word.
 */
public class Word {

    /**
     * Word Translation For each language
     */
    private String mArabicTranslation;
    private String mEnglishTranslation;
    private String mFrenchTranslation;


    // Word Audio Resource id for each language
    private int mArabic_Audio_Res_Id;
    private int mEnglish_Audio_Res_Id;
    private int mFrench_Audio_Res_Id;


////////////////////////////////////////
    /**
     * this was made to fix the problem of hide the images Like the phrases activity
     * Constant value that represents no image was provided for this word
     * why value of -1 ? cause it's distinct and no CurrentImageResourceId will be -1
     */

    private static final int NO_IMAGE_PROVIDED = -1;
    /**
     * Image resource ID for the word
     */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

/////////////////////////////////////////////////////

    /*
    Constant value that represents languages provided for this word
 */
    private static final String ARABIC = "AR";
    private static final String ENGLISH = "EN";
    private static final String FRENCH = "FR";

    /**
     * Create a new Word object.
     *
     * @param ArabicTranslation
     * @param EnglishTranslation
     * @param FrenchTranslation
     * @param Arabic_Audio_Res_Id
     * @param English_Audio_Res_Id
     * @param French_Audio_Res_Id
     */

    public Word(String ArabicTranslation, String EnglishTranslation, String FrenchTranslation,
                int Arabic_Audio_Res_Id, int English_Audio_Res_Id, int French_Audio_Res_Id) {
        mArabicTranslation = ArabicTranslation;
        mEnglishTranslation = EnglishTranslation;
        mFrenchTranslation = FrenchTranslation;
        mArabic_Audio_Res_Id = Arabic_Audio_Res_Id;
        mEnglish_Audio_Res_Id = English_Audio_Res_Id;
        mFrench_Audio_Res_Id = French_Audio_Res_Id;
    }


    /**
     * Create a new Word object.
     *
     * @param ArabicTranslation
     * @param EnglishTranslation
     * @param FrenchTranslation
     * @param Arabic_Audio_Res_Id
     * @param English_Audio_Res_Id
     * @param French_Audio_Res_Id
     * @param imageResourceId
     */

    public Word(String ArabicTranslation, String EnglishTranslation, String FrenchTranslation,
                int Arabic_Audio_Res_Id, int English_Audio_Res_Id, int French_Audio_Res_Id, int imageResourceId) {
        mArabicTranslation = ArabicTranslation;
        mEnglishTranslation = EnglishTranslation;
        mFrenchTranslation = FrenchTranslation;
        mArabic_Audio_Res_Id = Arabic_Audio_Res_Id;
        mEnglish_Audio_Res_Id = English_Audio_Res_Id;
        mFrench_Audio_Res_Id = French_Audio_Res_Id;
        mImageResourceId = imageResourceId;
    }

    /**
     * Get the translation of the word according to selected language .
     */
    public String getWordTranslation(String selected_language) {
        if (selected_language == ARABIC) {
            return mArabicTranslation;
        } else if (selected_language == ENGLISH) {
            return mEnglishTranslation;
        } else
            return mFrenchTranslation;

    }


    /**
     * Get the Audio Res id  of the word according to selected language .
     */
    public int getAudioResourceId(String selected_language) {
        if (selected_language == ARABIC) {
            return mArabic_Audio_Res_Id;
        } else if (selected_language == ENGLISH) {
            return mEnglish_Audio_Res_Id;
        } else
            return mFrench_Audio_Res_Id;
    }

    /**
     * Return the image resource ID of the word.
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }


    /**
     * Returns whether or not there is an image for this word.
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}