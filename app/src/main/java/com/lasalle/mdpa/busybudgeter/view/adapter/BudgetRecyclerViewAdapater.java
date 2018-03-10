package com.lasalle.mdpa.busybudgeter.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lasalle.mdpa.busybudgeter.R;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by albert.denova on 22/02/2018.
 */

public class BudgetRecyclerViewAdapater extends RecyclerView.Adapter<BudgetRecyclerViewAdapater.ViewHolder> {

    private List<String> budgetNameList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.budget_name) TextView nameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public BudgetRecyclerViewAdapater(List<String> budgetNameList) {
        this.budgetNameList = budgetNameList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.budget_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameTextView.setText(budgetNameList.get(position));
    }

    @Override
    public int getItemCount() {
        return budgetNameList.size();
    }

    public void setBudgetNameList(List<String> budgetNameList) {
        this.budgetNameList = budgetNameList;
    }
}
