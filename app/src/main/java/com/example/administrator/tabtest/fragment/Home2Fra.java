package com.example.administrator.tabtest.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.example.administrator.tabtest.R;


/**
 * 空白页面
 */
public class Home2Fra extends BaseFragment implements OnClickListener {

    public static String FRAGMENT_TAG = Home2Fra.class.getSimpleName();

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.index_2, null);

		return v;
	}

	@Override
	public void onClick(View v) {
	}

}
