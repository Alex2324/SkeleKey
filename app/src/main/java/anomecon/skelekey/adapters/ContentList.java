package anomecon.skelekey.adapters;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import anomecon.skelekey.Content;
import anomecon.skelekey.User;
import anomecon.skelekey.DatabaseHelper;
import anomecon.skelekey.R;

public class ContentList extends Activity{

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
}