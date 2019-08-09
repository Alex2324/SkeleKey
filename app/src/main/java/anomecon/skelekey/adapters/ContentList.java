package anomecon.skelekey.adapters;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import anomecon.skelekey.AddRecord;
import anomecon.skelekey.Content;
import anomecon.skelekey.DatabaseHelper;
import anomecon.skelekey.DeleteRecord;
import anomecon.skelekey.R;

public class ContentList extends AppCompatActivity {

    private Activity activity = ContentList.this;
    private TextView textName;
    private RecyclerView recyclerViewContent;
    private List<Content> listContent;
    private ContentRecyclerAdapter contentRecyclerAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        initViews();
        initObjects();
    }

    private void initViews(){
        textName = findViewById(R.id.textViewName);
        recyclerViewContent = findViewById(R.id.recyclerViewContent);
    }

    private void initObjects(){
        listContent = new ArrayList<>();
        contentRecyclerAdapter = new ContentRecyclerAdapter(listContent);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewContent.setLayoutManager(layoutManager);
        recyclerViewContent.setItemAnimator(new DefaultItemAnimator());
        recyclerViewContent.setHasFixedSize(true);
        recyclerViewContent.setAdapter(contentRecyclerAdapter);
        databaseHelper = new DatabaseHelper(activity);

        //Intent username = new Intent(this, UsersList.class);
        //String name = username.getStringExtra("NAME");
        //textName.setText(name);

        getContentData();
    }
    private void getContentData() {
        // AsyncTask, for multitasking
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listContent.clear();
                listContent.addAll(databaseHelper.getAllContent());

                return null;
            }

            @Override
            protected void onPostExecute(Void _void) {
                super.onPostExecute(_void);
                contentRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;
            case R.id.action_add:
                Intent addActivity = new Intent(getApplicationContext(), AddRecord.class);
                startActivity(addActivity);
                return true;
            case R.id.action_delete:
                Intent deleteActivity = new Intent(getApplicationContext(), DeleteRecord.class);
                startActivity(deleteActivity);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}