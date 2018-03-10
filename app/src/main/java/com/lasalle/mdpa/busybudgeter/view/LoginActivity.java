package com.lasalle.mdpa.busybudgeter.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lasalle.mdpa.busybudgeter.R;
import com.lasalle.mdpa.busybudgeter.view.model.UserLoginViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Binding;
import toothpick.config.Module;

public class LoginActivity extends AppCompatActivity {

    private Scope scope;

    @Inject UserLoginViewModel userLoginViewModel;

    @BindView(R.id.username) EditText username;
    @BindView(R.id.password) EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        scope = Toothpick.openScopes(getApplication(), this);
        scope.installModules(new Module() {{
            bind(UserLoginViewModel.class);
        }});
        super.onCreate(savedInstanceState);
        Toothpick.inject(this, scope);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        Toothpick.closeScope(this);
        super.onDestroy();
    }

    @OnClick(R.id.login_button)
    public void onLoginButtonClicked() {
        try {
            userLoginViewModel.OnLoginUser(username.getText().toString(), password.getText().toString());
        }
        catch (IllegalArgumentException e) {
            Toast.makeText(this, R.string.login_empty, Toast.LENGTH_SHORT).show();
        }
    }
}
