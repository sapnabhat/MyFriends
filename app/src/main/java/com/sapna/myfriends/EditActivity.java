package com.sapna.myfriends;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Sapna on 8/6/2016.
 */
public class EditActivity extends FragmentActivity {
    private final String TAG = EditActivity.class.getSimpleName();
    private TextView mNameTextView, mEmailTextView, mPhoneTextView;
    private Button mButton;
    private ContentResolver contentResolver;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.add_edit );
        getActionBar().setDisplayHomeAsUpEnabled( true );

        mNameTextView = (TextView) findViewById( R.id.friendName );
        mEmailTextView = (TextView) findViewById( R.id.friendEmail );
        mPhoneTextView = (TextView) findViewById( R.id.friendPhone );

        contentResolver = EditActivity.this.getContentResolver();

        Intent intent = getIntent();
        final String _id = intent.getStringExtra( FriendsContract.FriendsColumns.FRIENDS_ID );
        String name = intent.getStringExtra( FriendsContract.FriendsColumns.FRIENDS_NAME );
        String email = intent.getStringExtra( FriendsContract.FriendsColumns.FRIENDS_EMAIL );
        String phone = intent.getStringExtra( FriendsContract.FriendsColumns.FRIENDS_PHONE );

        mNameTextView.setText( name );
        mEmailTextView.setText( email );
        mPhoneTextView.setText( phone );

        mButton = (Button) findViewById( R.id.saveButton );
        mButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues = new ContentValues();
                contentValues.put( FriendsContract.FriendsColumns.FRIENDS_NAME, mNameTextView.getText().toString() );
                contentValues.put( FriendsContract.FriendsColumns.FRIENDS_EMAIL, mEmailTextView.getText().toString() );
                contentValues.put( FriendsContract.FriendsColumns.FRIENDS_PHONE, mPhoneTextView.getText().toString() );
                Uri uri = FriendsContract.MyFriends.buildFriendUri( _id );
                int recordsUpdated = contentResolver.update( uri, contentValues, null, null );
                Log.d( TAG, "number of records updated is " + recordsUpdated );
                Intent intent = new Intent( EditActivity.this, MainActivity.class );
                startActivity( intent );
                finish();
            }
        } );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask( this );
                break;
        }
        return true;
    }
}
