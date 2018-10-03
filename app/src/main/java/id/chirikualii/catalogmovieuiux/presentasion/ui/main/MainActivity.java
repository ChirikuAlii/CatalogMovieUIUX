package id.chirikualii.catalogmovieuiux.presentasion.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.chirikualii.catalogmovieuiux.BaseApp;
import id.chirikualii.catalogmovieuiux.R;
import id.chirikualii.catalogmovieuiux.data.db.FavoriteHelper;
import id.chirikualii.catalogmovieuiux.di.component.ActivityComponent;
import id.chirikualii.catalogmovieuiux.di.scope.ActivityScope;
import id.chirikualii.catalogmovieuiux.presentasion.ui.favoritemovie.FavoriteMovieActivity;
import id.chirikualii.catalogmovieuiux.presentasion.ui.searchmovie.SearchMovieActivity;
import id.chirikualii.catalogmovieuiux.presentasion.ui.main.adapter.ViewPagerAdapter;
import id.chirikualii.catalogmovieuiux.presentasion.ui.settings.SettingsActivity;

@ActivityScope
public class MainActivity extends AppCompatActivity implements MainView {

    @Inject
    MainPresenter presenter;
    //tag name
    private String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.tab_layout) TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindModule();
        this.setupView();
    }

    private void bindModule() {
        ButterKnife.bind(this);
        this.injectActivity();
        presenter.bind(this);
    }

    private void injectActivity() {
        Context context=this.getApplicationContext();
        ActivityComponent injector =((BaseApp)context).getAppComponent().activityComponent().build();
        injector.inject(this);
    }

    private void setupView() {
        setSupportActionBar(toolbar);
        this.setupNavbar();
        this.setupViewPager();
        this.setupTab();

        //set on click nav item
        navigationView.setNavigationItemSelectedListener(menuItem -> {

            switch (menuItem.getItemId()){
                case R.id.nav_now_playing:
                    //state
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.nav_upcoming:
                    //state
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.nav_favorite:
                    //state
                    Intent intent = new Intent(this, FavoriteMovieActivity.class);
                    startActivity(intent);
                    break;
            }
            drawer.closeDrawer(GravityCompat.START);
            return true;
        });

        //set click on click tab
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                navigationView.getMenu().getItem(tab.getPosition()).setChecked(true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void setupTab() {
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.label_now_playing)));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.label_upcoming));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    private void setupViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),navigationView);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void setupNavbar() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //menu index 0 checked default
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    private void checkNavDrawerState() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        this.checkNavDrawerState();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        //setup search view
        SearchView searchView = (SearchView) menu.findItem(R.id.search_bar).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.performSearchMovie(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });
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
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void navigateToSearchActivity(String query) {
        Intent intent = new Intent(this, SearchMovieActivity.class);
        intent.putExtra(SearchMovieActivity.SEARCH_MOVIE_KEY,query);
        startActivity(intent);
    }

    @Override
    public void setOnErrorSearch(String message) {

    }
}
