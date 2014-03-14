package com.ug.sp1.data;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ug.sp1.R;
import com.ug.sp1.fragments.FlagFragment;

public class OfertasPagerAdapter extends FragmentPagerAdapter {

	public OfertasPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int item) {
		int[] arrayFlags = new int[]{ 
				 R.drawable.macaroneoferta,
				 R.drawable.popsoferta,
				 R.drawable.macoferta,
				 R.drawable.burgeroferta
				 };
		
        Fragment fragment = new FlagFragment();
        Bundle args = new Bundle();
        args.putInt(FlagFragment.RESOURCE, arrayFlags[item]);
        fragment.setArguments(args);
        return fragment;		
		
	}

	@Override
	public int getCount() {
		return 4;
	}

}
