package com.ug.sp1.fragments;

import com.ug.sp1.R;
import com.ug.sp1.data.OfertasPagerAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OfertasFragment extends Fragment {
ViewPager viewPager;
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		
		OfertasPagerAdapter adapter = new OfertasPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_ofertas, container, false);
		viewPager = (ViewPager) view.findViewById(R.id.pager);
		return view;
	}
}


