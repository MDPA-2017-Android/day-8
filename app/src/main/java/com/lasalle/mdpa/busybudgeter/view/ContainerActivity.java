package com.lasalle.mdpa.busybudgeter.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lasalle.mdpa.busybudgeter.R;
import com.lasalle.mdpa.busybudgeter.view.model.LoggedUserViewModel;
import com.lasalle.mdpa.busybudgeter.view.model.UserLoginViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

public class ContainerActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.navigation) BottomNavigationView bottomNavigationView;

    @Inject LoggedUserViewModel loggedUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Scope scope = Toothpick.openScopes(getApplication(), this);
        scope.installModules(new Module() {{
            bind(LoggedUserViewModel.class);
        }});
        super.onCreate(savedInstanceState);
        Toothpick.inject(this, scope);

        setContentView(R.layout.activity_container);
        ButterKnife.bind(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        initFragmentContainer();
    }

    @Override
    protected void onDestroy() {
        Toothpick.closeScope(this);
        super.onDestroy();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_dashboard:

            case R.id.navigation_settings:
                fragment = new SettingsFragment();
                break;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

        item.setChecked(true);

        return true;
    }

    private void initFragmentContainer() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
    }
}
