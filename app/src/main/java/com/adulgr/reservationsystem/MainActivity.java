package com.adulgr.reservationsystem;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.adulgr.reservationsystem.fragments.CustomerInfoDialog;
import com.adulgr.reservationsystem.fragments.GalleryFragment;
import com.adulgr.reservationsystem.fragments.JumperFragment;
import com.adulgr.reservationsystem.fragments.ShareFragment;
import com.adulgr.reservationsystem.fragments.SuppliesFragment;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        DialogFragment fragment = new CustomerInfoDialog();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment.show(fragmentTransaction, "Book");
      }
    });

    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
    if (id == R.id.action_book) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private void displaySelectedScreen(int itemId) {
    // Create fragment object
    Fragment fragment = null;

    // Initialize the fragment object which is selected
    switch (itemId) {
      case R.id.action_book:
        fragment = new CustomerInfoDialog();
        break;
      case R.id.action_contact:

        break;
      case R.id.action_logout:

        break;
      case R.id.nav_jumpers:
        fragment = new JumperFragment();
        break;
      case R.id.nav_supplies:
        fragment = new SuppliesFragment();
        break;
      case R.id.nav_gallery:
        fragment = new GalleryFragment();
        break;
      case R.id.nav_book:
        fragment = new CustomerInfoDialog();
        break;
      case R.id.nav_share:
        fragment = new ShareFragment();
        break;
    }
    if (fragment != null) {
      FragmentTransaction fragmentTransaction =
          getSupportFragmentManager().beginTransaction();
      fragmentTransaction.replace(R.id.main_container, fragment);
      fragmentTransaction.commit();
    }
    DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
    drawerLayout.closeDrawer(GravityCompat.START);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    displaySelectedScreen(item.getItemId());

    return true;
  }
}
