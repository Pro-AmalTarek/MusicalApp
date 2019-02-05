package com.amaltarek.musicalapp.Adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.amaltarek.musicalapp.R;

public class CategoryAdapter extends FragmentPagerAdapter {

    // Context of the app
    private Context mContext;

    public CategoryAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }


    /**
     * Get the Fragment that should be displayed
     * @param position
     * @return the Fragment that should be displayed
     */
    @Override
    public Fragment getItem(int position) {
        return null;
    }

    /**
     * Return Total Number of Pages
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }

    /**
     * Get Fragment Tab Title to display with Fragment
     * @param position
     * @return
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return mContext.getString(R.string.songs);
        }else{
            return mContext.getString(R.string.albums);
        }
    }
}
