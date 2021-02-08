package com.example.scammersgoaway.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.example.scammersgoaway.R;
import com.example.scammersgoaway.database.DB;

public class RuleCursorAdapter extends SimpleCursorAdapter {

    private int layout;

    public RuleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flag) {
        super(context, layout, c, from, to, flag);
        this.layout = layout;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, parent, false);
        return super.newView(context, cursor, parent);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView ruleName = (TextView)view.findViewById(R.id.ruleName);
        TextView ruleType = (TextView)view.findViewById(R.id.ruleType);
        TextView ruleValue = (TextView)view.findViewById(R.id.ruleValue);
        Switch ruleActive = (Switch)view.findViewById(R.id.ruleActive);

        ruleName.setText(cursor.getString(cursor.getColumnIndexOrThrow(DB.RULES_COLUMN_NAME)));
        ruleType.setText(cursor.getString(cursor.getColumnIndexOrThrow(DB.RULES_COLUMN_TYPE)));
        ruleValue.setText(cursor.getString(cursor.getColumnIndexOrThrow(DB.RULES_COLUMN_VALUE)));
        ruleActive.setChecked((cursor.getInt(cursor.getColumnIndexOrThrow(DB.RULES_COLUMN_ACTIVE))) == 1);

    }

}
