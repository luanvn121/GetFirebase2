package romz.luannguyen.getfirebase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import romz.luannguyen.getfirebase.R;
import romz.luannguyen.getfirebase.adapter.CustomAdapter;
import romz.luannguyen.getfirebase.fragment.Thang7Fragment;
import romz.luannguyen.getfirebase.fragment.Thang8Fragment;
import romz.luannguyen.getfirebase.model.DuLieu;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Firebase root;
    private ArrayList<DuLieu> mDulieu = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Firebase.setAndroidContext(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


        root = new Firebase("https://kitestquocte.firebaseio.com/2016/6");

//        if (isNetworkAvailable(this)) {
//            // code here
//            Toast.makeText(MainActivity.this, "Connect", Toast.LENGTH_SHORT).show();
//        } else {
//            // code
//            Toast.makeText(MainActivity.this, "NotConnect", Toast.LENGTH_SHORT).show();
//
//        }
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("Tag", "onChildAdded: " + dataSnapshot.getKey().toString());


                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.d("child", "onDataChange: " + child.getValue().toString());


                    DuLieu post = new DuLieu();
                    post.setContent(child.getValue(DuLieu.class).getContent());
                    post.setTitle(child.getValue(DuLieu.class).getTitle());
                    post.setUrl(child.getValue(DuLieu.class).getUrl());
                    mDulieu.add(post);
                }
                customAdapter = new CustomAdapter(getApplicationContext(), mDulieu);
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        addControl();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

//    private boolean isNetworkAvailable(final Context context) {
//        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
//        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
//    }

    private void addControl() {

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
        if (id == R.id.action_exit) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent homeIntent = new Intent(this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);

        } else if (id == R.id.nav_thang7) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fram, new Thang7Fragment()).commit();

        } else if (id == R.id.nav_thang8) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fram, new Thang8Fragment()).commit();

        } else if (id == R.id.nav_thang9) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fram, new Thang8Fragment()).commit();

        } else if (id == R.id.nav_thang10) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fram, new Thang8Fragment()).commit();
        }
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
}
