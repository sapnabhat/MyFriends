package com.sapna.myfriends;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Sapna on 8/5/2016.
 */
public class FriendsContract {
    public static final String CONTENT_AUTHORITY = "com.sapna.myfriends.provider";
    public static final Uri BASE_CONTENT_URI = Uri.parse( "content://" + CONTENT_AUTHORITY );
    private static final String PATH_FRIENDS = "friends";
    public static final Uri URI_TABLE = Uri.parse( BASE_CONTENT_URI.toString() + "/" + PATH_FRIENDS );
    public static final String[] TOP_LEVEL_PATHS = {
            PATH_FRIENDS
    };

    interface FriendsColumns {
        String FRIENDS_ID = "_id";
        String FRIENDS_NAME = "friends_name";
        String FRIENDS_EMAIL = "friends_email";
        String FRIENDS_PHONE = "friends_phone";
    }

    public static class MyFriends implements FriendsColumns, BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendEncodedPath( PATH_FRIENDS ).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + ".friends";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + ".friends";

        public static Uri buildFriendUri(String friendId) {
            return CONTENT_URI.buildUpon().appendEncodedPath( friendId ).build();
        }

        public static String getFriendId(Uri uri) {
            return uri.getPathSegments().get( 1 );
        }
    }

}