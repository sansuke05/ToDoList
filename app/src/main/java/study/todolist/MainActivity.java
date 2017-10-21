package study.todolist;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ToDoAdapter todo;
    private List<ToDoItem> list = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todo = new ToDoAdapter(this);
        listView = (ListView) findViewById(R.id.listView);
        listInit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.mainToolBar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("ToDoList");

        FloatingActionButton btn = (FloatingActionButton) findViewById(R.id.addButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), AddActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        listInit();
    }

    void listInit() {
        list.clear();
        list = todo.getList();
        ToDoListAdapter rowAdapter = new ToDoListAdapter(this, 0, list);
        listView.setAdapter(rowAdapter);
    }
}
