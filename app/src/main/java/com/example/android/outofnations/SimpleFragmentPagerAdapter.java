package com.example.android.outofnations;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Ahmed Sayed on 3/14/2018.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {


    private Context mContext;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_Home);
        } else if (position == 1) {
            return mContext.getString(R.string.category_numbers);
        } else if (position == 2)

        {
            return mContext.getString(R.string.category_colors);
        } else if (position == 3)

        {
            return mContext.getString(R.string.category_family);
        } else if (position == 4)

        {
            return mContext.getString(R.string.category_food);
        } else if (position == 5)

        {
            return mContext.getString(R.string.category_transport);
        } else if (position == 6)

        {
            return mContext.getString(R.string.category_places);
        } else if (position == 7)

        {
            return mContext.getString(R.string.category_weeksAndMonthes);
        } else if (position == 8)

        {
            return mContext.getString(R.string.category_Clothes);
        } else


            return mContext.getString(R.string.category_phrases);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {


        /*
        * 0 - Home
        * 1 - Numbers
        * 2 - Colors
        * 3 - Family
        * 4 - Food & Drinks
        * 5- Transport
        * 6 - Places
        * 7 - Weekdays & Months
        * 8 - Clothes
        * 9- Phrases */
        if (position == 0) {
            return new HomeFragment();
        }
        if (position == 1) {
            return new NumbersFragment();
        } else if (position == 2) {
            return new ColorsFragment();
        } else if (position == 3) {
            return new FamilyFragment();
        } else if (position == 4) {
            return new FoodAndDrinksFragment();
        } else if (position == 5) {
            return new TansportFragment();
        } else if (position == 6) {
            return new PlacesFragment();
        } else if (position == 7) {
            return new WeekdaysAndMonthsFragment();
        } else if (position == 8) {
            return new ClothesFragment();
        } else
            return new PhrasesFragment();
    }
}
