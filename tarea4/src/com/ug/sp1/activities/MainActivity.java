package com.ug.sp1.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ug.sp1.R;
import com.ug.sp1.fragments.ComunidadFragment;
import com.ug.sp1.fragments.ContentFragment;
import com.ug.sp1.fragments.FotoFragment;
import com.ug.sp1.fragments.OfertasFragment;

public class MainActivity extends ActionBarActivity {
    private ListView drawerList;
    private String[] drawerOptions;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    
    Fragment[] fragments = new Fragment[]{new ContentFragment(),
    									  new OfertasFragment(),
                                          new ComunidadFragment(),
                                          new FotoFragment()
                                          };
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerOptions = getResources().getStringArray(R.array.drawer_options);
        drawerList.setAdapter(new ArrayAdapter<String>(this,
                                                       R.layout.drawer_list_item, 
                                                       drawerOptions));
        drawerList.setItemChecked(0, true);
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close
                ) {

            public void onDrawerClosed(View view) {
            	ActivityCompat.invalidateOptionsMenu(MainActivity.this);
            	//invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
            	ActivityCompat.invalidateOptionsMenu(MainActivity.this);
            	//invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);        
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
        	    .add(R.id.contentFrame, fragments[0])
        		.add(R.id.contentFrame, fragments[1])
        		.add(R.id.contentFrame, fragments[2])
        		.add(R.id.contentFrame, fragments[3])
        	    .commit();	 
        setContent(1);		
	}
	
	
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
          	 
            if (drawerLayout.isDrawerOpen(drawerList)) {
            	drawerLayout.closeDrawer(drawerList);
            } else {
            	drawerLayout.openDrawer(drawerList);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    
	public void setContent(int index) {
		Fragment toHide1 = null;
		Fragment toHide2 = null;
		Fragment toHide3 = null;
		Fragment toShow = null;
		final ActionBar actionBar = getSupportActionBar();
		switch (index) {
			case 0:
				toShow = fragments[0];
				toHide1 = fragments[1];				
				toHide2 = fragments[2];
				toHide3 = fragments[3];
				actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
				break;
			case 1:
				toHide1 = fragments[0];
				toShow = fragments[1];
				toHide2 = fragments[2];
				toHide3 = fragments[3];
				actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);				
				break;
			case 2:
				toHide1 = fragments[0];				
				toHide2 = fragments[1];
				toShow = fragments[2];
				toHide3 = fragments[3];
				actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);				
				break;
			case 3:
				toHide1 = fragments[0];				
				toHide2 = fragments[1];
				toHide3 = fragments[2];
				toShow = fragments[3];
				actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);				
				break;
		}
		
		FragmentManager manager = getSupportFragmentManager();
		
		manager.beginTransaction()
				.hide(toHide1)
				.hide(toHide2)
				.hide(toHide3)
				.show(toShow)
				.commit();		
        drawerList.setItemChecked(index, true);
        drawerLayout.closeDrawer(drawerList);	
	}
	
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
    	
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        	setContent(position);
        }
    }	
}
