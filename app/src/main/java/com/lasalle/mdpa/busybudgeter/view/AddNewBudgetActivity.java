package com.lasalle.mdpa.busybudgeter.view;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.lasalle.mdpa.busybudgeter.R;
import com.lasalle.mdpa.busybudgeter.view.model.BudgetViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewBudgetActivity extends AppCompatActivity {

    @BindView(R.id.add_budget_name) EditText budgetNameEditText;

    private BudgetViewModel budgetViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_budget);

        budgetViewModel = ViewModelProviders.of(this).get(BudgetViewModel.class);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.add_budget_submit)
    public void onAddBudget() {
        budgetViewModel.insertNewBudget(budgetNameEditText.getText().toString());

        setResult(RESULT_OK, null);
        finish();
    }
}
