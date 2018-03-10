package com.lasalle.mdpa.busybudgeter.view;


import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lasalle.mdpa.busybudgeter.R;
import com.lasalle.mdpa.busybudgeter.view.model.LoggedUserViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import toothpick.Scope;
import toothpick.Toothpick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    @BindView(R.id.name) EditText nameText;
    @BindView(R.id.last_name) EditText lastNameText;
    @BindView(R.id.username) EditText usernameText;
    @BindView(R.id.password) EditText passwordText;

    @Inject LoggedUserViewModel loggedUserViewModel;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Activity owner = getActivity();
        Scope scope = Toothpick.openScopes(owner.getApplication(), owner, this);
        super.onCreate(savedInstanceState);
        Toothpick.inject(this, scope);

        loggedUserViewModel.getUser().observe(this, user -> {
            nameText.setText(user.name);
            lastNameText.setText(user.lastname);
            usernameText.setText(user.username);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);

        loggedUserViewModel.retrieveUserData();

        return view;
    }

    @OnClick(R.id.update_button)
    public void onClickUpdateButton() {

    }

}
