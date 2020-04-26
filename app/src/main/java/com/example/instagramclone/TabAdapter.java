package com.example.instagramclone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {


    public TabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int tabPosition) {
        switch (tabPosition) {
            case 0:
                // note the profileTab is of type 'Fragment' and the return type for getItem is 'Fragment'
                ProfileTab profileTab = new ProfileTab();
                return profileTab; //recall ProfileTab extends Fragment so profileTab matches the return type of getItem!
            case 1:
                // a more succinct way to do the above
                return new UsersTab();
            case 2:
                return new SharePictureTab();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 3;  // we are returning the number of tabs!
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Profile";
            case 1:
                return "Users";
            case 2:
                return "Share Pictures";
            default:
                return null; // satisfies 'missing return statement' error. this method wants a default for switch
        }
    }
}
