package fr.android.moi.sportplus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, myFragment.OnFragmentInteractionListener,
        contestFragment.OnFragmentInteractionListener, onContestFragmentCreated.OnFragmentInteractionListener,
        historiqueFragment.OnFragmentInteractionListener {
    private myFragment myFragment;
    private contestFragment contestFragment;
    private onContestFragmentCreated fragmentCreated;
    private historiqueFragment historiqueFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fgt = getSupportFragmentManager().beginTransaction();
                fgt.addToBackStack("new fragment");
                Show(contestFragment);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction fgt = getSupportFragmentManager().beginTransaction();
        fgt.addToBackStack("new fragment");
        if (id == R.id.nav_contest) {
            Show(contestFragment);

        } else if (id == R.id.nav_historique) {
            Show(historiqueFragment);
        } else if (id == R.id.nav_map) {
            myFragment = myFragment.newInstance("map", "mapFragment");
            fgt.replace(R.id.content_main, myFragment).commit();
            fgt.addToBackStack("new fragment");
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Object ref) {
        System.out.println((String) ref);
        Toast.makeText(this, (String) ref, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Show(onContestFragmentCreated fragment, Contest contest , int time) {
        fragment = onContestFragmentCreated.newInstance(contest.getId(),contest.getNom(), time);
        FragmentTransaction fgt = getSupportFragmentManager().beginTransaction();
        fgt.replace(R.id.fragment_contest, fragment).commit();
        fgt.addToBackStack("new fragment");
    }

    @Override
    public void Show(contestFragment fragment) {
        fragment = fr.android.moi.sportplus.contestFragment.newInstance("contest","contestFragment");
        FragmentTransaction fgt = getSupportFragmentManager().beginTransaction();
        fgt.replace(R.id.content_main, fragment).commit();
        fgt.addToBackStack("new fragment");
    }

    @Override
    public void Show(historiqueFragment fragment) {
        fragment = historiqueFragment.newInstance("contest","contestFragment");
        FragmentTransaction fgt = getSupportFragmentManager().beginTransaction();
        fgt.replace(R.id.content_main, fragment).commit();
        fgt.addToBackStack("new fragment");
    }
}
