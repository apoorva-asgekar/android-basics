package com.example.android.pets.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import static android.icu.text.UnicodeSet.CASE;

/**
 * Created by apoorva on 2/23/17.
 */

public final class PetContract {

    //Content Authority to access the Pets database.
    public static final String CONTENT_AUTHORITY = "com.example.android.pets";

    //Base URI for the content provider.
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_PETS = "pets";

    private PetContract() {
    }

    ;

    public static abstract class PetEntry implements BaseColumns {

        //Complete Content URI.
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of pets.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;

        public static final String TABLE_NAME = "pets";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PET_NAME = "name";
        public static final String COLUMN_PET_BREED = "breed";
        public static final String COLUMN_PET_GENDER = "gender";
        public static final String COLUMN_PET_WEIGHT = "weight";

        //Possible values for the gender.
        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;

        public static boolean isGenderValid(int gender) {
            boolean isGenderValid = false;
            if (gender == GENDER_MALE
                    || gender == GENDER_FEMALE
                    || gender == GENDER_UNKNOWN) {
                isGenderValid = true;
            }
            return isGenderValid;
        }
    }
}
