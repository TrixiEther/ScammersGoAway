package com.example.scammersgoaway.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.scammersgoaway.R;
import com.example.scammersgoaway.adapters.RuleArrayAdapter;
import com.example.scammersgoaway.adapters.RuleCursorAdapter;
import com.example.scammersgoaway.database.DatabaseHelper;
import com.example.scammersgoaway.items.RuleItem;

import java.util.ArrayList;

public class RuleListFragment extends Fragment {

    //final ArrayList<RuleItem> ruleList = new ArrayList<RuleItem>();
    //ArrayAdapter<String> ruleAdapter;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor ruleCursor;
    RuleCursorAdapter ruleAdapter;

    String[] rulesFrom = {DatabaseHelper.RULES_COLUMN_NAME,
            DatabaseHelper.RULES_COLUMN_TYPE,
            DatabaseHelper.RULES_COLUMN_VALUE,
            DatabaseHelper.RULES_COLUMN_ACTIVE
    };
    int[] rulesTo = {
            R.id.ruleName,
            R.id.ruleType,
            R.id.ruleValue,
            R.id.ruleActive
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rule_list, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*
        ruleList.add(new RuleItem("Name","Type","79307167684",true));
        ruleList.add(new RuleItem("Name","Type","79307167684",false));
        ruleList.add(new RuleItem("Name","Type","79307167684",true));
        ruleList.add(new RuleItem("Name","Type","79307167684",false));
        ruleList.add(new RuleItem("Name","Type","79307167684",true));
        ruleList.add(new RuleItem("Name","Type","79307167684",false));
        ruleList.add(new RuleItem("Name","Type","79307167684",true));
        ruleList.add(new RuleItem("Name","Type","79307167684",false));

        ListView ruleListView = view.findViewById(R.id.ruleList);
        ruleAdapter = new RuleArrayAdapter(view.getContext(), R.layout.rule_item, ruleList);
        ruleListView.setAdapter(ruleAdapter);*/

        ListView ruleListView = view.findViewById(R.id.ruleList);

        databaseHelper = new DatabaseHelper(view.getContext());
        db = databaseHelper.getReadableDatabase();
        databaseHelper.onCreate(db);
        ruleCursor = databaseHelper.getRulesAllCursor();
        ruleAdapter = new RuleCursorAdapter(
                view.getContext(),
                R.layout.fragment_rule_list,
                ruleCursor,
                rulesFrom,
                rulesTo
        );
        ruleListView.setAdapter(ruleAdapter);

        view.findViewById(R.id.addNewRule).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(RuleListFragment.this).navigate(R.id.action_ruleListFragment_to_addRuleFragment);
            }

        });
    }
}