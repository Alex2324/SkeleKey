package anomecon.skelekey.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import anomecon.skelekey.Content;
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
        //initObjects();
    }

    private void initViews(){
        textName = findViewById(R.id.textViewName);
        recyclerViewContent = findViewById(R.id.recyclerViewContent);
    }

    private void initObjects(){
        listContent = new ArrayList<>();
        contentRecyclerAdapter = new ContentRecyclerAdapter(listContent);
    }
}
