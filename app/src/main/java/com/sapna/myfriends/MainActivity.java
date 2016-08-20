package com.sapna.myfriends;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(android.R.id.content ) == null) {
            FriendsListFragment friendsListFragment = new FriendsListFragment();
            fragmentManager.beginTransaction().add( android.R.id.content, friendsListFragment ).commit();
        }
        //setContentView( R.layout.activity_main );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.menu_main, menu );
        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.addRecord:
                Intent intent = new Intent( MainActivity.this, AddActivity.class );
                startActivity( intent );
                break;
            case R.id.deleteDatabase:
                FriendsDialog friendsDialog = new FriendsDialog();
                Bundle args = new Bundle();
                args.putString( FriendsDialog.DIALOG_TYPE, FriendsDialog.DELETE_DATABASE );
                friendsDialog.setArguments( args );
                friendsDialog.show( getSupportFragmentManager(), "delete-database" );
                break;
            case R.id.searchRecord:
                Intent searchIntent = new Intent( MainActivity.this, SearchActivity.class );
                startActivity( searchIntent );

        }

        return super.onOptionsItemSelected( item );
    }
}
