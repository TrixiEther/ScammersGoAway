package com.example.scammersgoaway.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.example.scammersgoaway.R;
import com.example.scammersgoaway.items.RuleItem;

import java.util.ArrayList;

public class RuleArrayAdapter extends ArrayAdapter {

    private final Context context;
    private final int resource;
    private final ArrayList<RuleItem> rules;

    public RuleArrayAdapter(Context context, int resource, ArrayList<RuleItem> values) {
        super(context, resource, values);
        this.context = context;
        this.resource = resource;
        this.rules = values;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View ruleView = inflater.inflate(resource, null, false);

        TextView ruleName = (TextView)ruleView.findViewById(R.id.ruleName);
        TextView ruleType = (TextView)ruleView.findViewById(R.id.ruleType);
        TextView ruleValue = (TextView)ruleView.findViewById(R.id.ruleValue);
        Switch ruleActive = (Switch)ruleView.findViewById(R.id.ruleActive);

        RuleItem currentRule = rules.get(position);

        ruleName.setText(currentRule.GetName());
        ruleType.setText(currentRule.GetType());
        ruleValue.setText(currentRule.GetValue());
        ruleActive.setChecked(currentRule.IsActive());

        return ruleView;

    }

}
