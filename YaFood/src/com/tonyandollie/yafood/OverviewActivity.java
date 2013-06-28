package com.tonyandollie.yafood;

import java.util.ArrayList;
import java.util.Locale;

import org.json.crockford.JSONException;
import org.json.crockford.JSONObject;
import org.json.crockford.XML;
import org.json.crockford.JSONArray;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class OverviewActivity extends FragmentActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overview);

		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.overview, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
		
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new CafeMenuFragment();
			Bundle args = new Bundle();
			args.putInt(CafeMenuFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
			
		}

		@Override
		public int getCount() {
			// Show 5 total pages.
			return 5;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			case 3:
				return getString(R.string.title_section4).toUpperCase(l);
			case 4:
				return getString(R.string.title_section5).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class CafeMenuFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public CafeMenuFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			View rootView = inflater.inflate(R.layout.fragment_cafe,
					container, false);
			ListView listView = (ListView) rootView
					.findViewById(R.id.lvMenuList);
			loadMenu(listView,
					getArguments().getInt(ARG_SECTION_NUMBER));
		
			return rootView;
		}
		
		public void loadMenu (final View view, int menuNumber) {
			// asyn load the data from Google API
			AsyncHttpClient client = new AsyncHttpClient();
			
			// starts with 0, but menuNumber starts with 1
		    int[] menuUrls = new int[] {
		    		R.string.daily_menu_url1,
		    		R.string.daily_menu_url2,
		    		R.string.daily_menu_url3,		    		
		    		R.string.daily_menu_url4,		    		
		    		R.string.daily_menu_url5,		    		
		    };

		    final JSONObject stations = new JSONObject();
		    final String xmlResult = new String();
			final String url = getResources().getString(menuUrls[menuNumber - 1]);
			
			Log.d("DEBUG", "URL " +   url);
			client.get(
					url,
					new AsyncHttpResponseHandler() {
						@Override
						public void onStart() {
							// Initiated the request
							Log.d("DEBUG", "URL START " +   url);

						}

						@Override
						public void onSuccess(String response) {
							// Successfully got a response
							ArrayList<Station> stationResults = new ArrayList<Station>();

							try {

								JSONObject json = XML.toJSONObject(response);
								JSONArray jsonStations = json.getJSONObject("Document")
										.getJSONObject("tblMenu")
										.getJSONArray("tblDayPart");
								Log.d("DEBUG", "URL SUCCESS " +   url + json.toString());
								Log.d("DEBUG", "tblDayPart" + jsonStations.toString());

								ListView listView = (ListView ) view;
								StationListArrayAdapter stationAdapter = new StationListArrayAdapter(getActivity(),
										stationResults);
								stationAdapter.addAll(Station.fromJson(jsonStations));
								listView.setAdapter(stationAdapter);	
							} catch (JSONException e) {
								Log.d("DEBUG", e.toString());
							}

						}

						@Override
						public void onFailure(Throwable e, String response) {
							// Response failed :(
							Log.d("DEBUG", "URL FAIL " +   url + e.toString() + response);

						}

						@Override
						public void onFinish() {
							// Completed the request (either success or failure)
							Log.d("DEBUG", "URL FINISH" +   url);

						}
					}
					);
		}
	}

}
