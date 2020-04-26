package com.example.instagramclone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {
    private EditText edtProfileName, edtProfileBio, edtProfileProfession, edtProfileHobbies, edtProfileFavoriteSport;
    private Button btnProfileUpdateInfo;


    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profile_tab, container, false);
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);
        // note findViewByID doesn't live inside this method so we get access by pointing 'view' to a method that does..is how i get this
        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtProfileBio = view.findViewById(R.id.edtProfileBio);
        edtProfileProfession = view.findViewById(R.id.edtProfileProfession);
        edtProfileFavoriteSport = view.findViewById(R.id.edtProfileFavoriteSport);
        edtProfileHobbies = view.findViewById(R.id.edtProfileHobbies);
        btnProfileUpdateInfo = view.findViewById(R.id.btnProfileUpdateInfo);
        final ParseUser parseUser = ParseUser.getCurrentUser();
        // The below if statements protect us from calling toString on a null value  and throwing an exception!
        if (parseUser.get("profileName") == null ) {
            edtProfileName.setText("");

        } else {
            edtProfileName.setText(parseUser.get("profileName").toString());

        }
        if (parseUser.get("profileBio") == null) {
            edtProfileBio.setText("");

        } else {
            edtProfileBio.setText(parseUser.get("profileBio").toString());

        }
        if (parseUser.get("profileProfession") == null) {
            edtProfileProfession.setText("");

        } else {
            edtProfileProfession.setText(parseUser.get("profileProfession").toString());
        }
        if (parseUser.get("profileFavoriteSport") == null) {
            edtProfileFavoriteSport.setText("");

        } else {
            edtProfileFavoriteSport.setText(parseUser.get("profileFavoriteSport").toString());
        }
        if (parseUser.get("profileHobbies") == null) {
            edtProfileHobbies.setText("");
        } else {
            edtProfileHobbies.setText(parseUser.get("profileHobbies").toString());
        }

        btnProfileUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseUser.put("profileName", edtProfileName.getText().toString());
                parseUser.put("profileBio", edtProfileBio.getText().toString());
                parseUser.put("profileProfession", edtProfileProfession.getText().toString());
                parseUser.put("profileFavoriteSport", edtProfileFavoriteSport.getText().toString());
                parseUser.put("profileHobbies", edtProfileHobbies.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            // we can't use ProfileTab.this cause it's not an activity it's a fragment!
                            FancyToast.makeText(getContext(), "Info updated  Successfully!!", FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
                        } else {
                            FancyToast.makeText(getContext(), "Info not updated !!", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });

            }

            });
        return view;

    };
}
