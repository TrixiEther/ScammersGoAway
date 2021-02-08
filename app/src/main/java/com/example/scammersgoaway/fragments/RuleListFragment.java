package com.example.scammersgoaway.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.scammersgoaway.R;
import com.example.scammersgoaway.adapters.RuleCursorAdapter;
import com.example.scammersgoaway.database.DB;

import java.util.concurrent.TimeUnit;

public class RuleListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    //final ArrayList<RuleItem> ruleList = new ArrayList<RuleItem>();
    //ArrayAdapter<String> ruleAdapter;

    View thisView;

    ListView lvRules;
    DB db;
    RuleCursorAdapter ruleAdapter;

    String[] rulesFrom = {DB.RULES_COLUMN_NAME,
            DB.RULES_COLUMN_TYPE,
            DB.RULES_COLUMN_VALUE,
            DB.RULES_COLUMN_ACTIVE
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
        thisView = view;
        db = new DB(view.getContext());
        db.open();
        ruleAdapter = new RuleCursorAdapter(view.getContext(), R.layout.rule_item, db.getAllData(), rulesFrom, rulesTo, 0);
        lvRules = (ListView)view.findViewById(R.id.ruleList);
        lvRules.setAdapter(ruleAdapter);

        registerForContextMenu(lvRules);

        view.findViewById(R.id.addNewRule).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(RuleListFragment.this).navigate(R.id.action_ruleListFragment_to_addRuleFragment);
            }

        });
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new MyCursorLoader(thisView.getContext(), db);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        ruleAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    static class MyCursorLoader extends CursorLoader {

        DB db;

        public MyCursorLoader(Context context, DB db) {
            super(context);
            this.db = db;
        }

        @Override
        public Cursor loadInBackground() {
            Cursor cursor = db.getAllData();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return cursor;
        }

    }
}