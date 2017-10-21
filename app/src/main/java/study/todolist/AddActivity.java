package study.todolist;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends FragmentActivity implements DatePickerDialog.OnDateSetListener {

    EditText deadLineText;
    ToDoAdapter todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        todo = new ToDoAdapter(this);

        Date today = new Date();
        deadLineText = findViewById(R.id.deadLine);
        deadLineText.setText(new SimpleDateFormat(getResources().getString(R.string.dateFormat)).format(today));
        deadLineText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Myapp", "tap");
                showDatePickerDialog(view);
            }
        });

        Button addBtn = findViewById(R.id.addToDo);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText title = findViewById(R.id.titleInput);
                SimpleDateFormat format = new SimpleDateFormat(getResources().getString(R.string.dateFormat));
                Date deadLine = new Date();
                try {
                    deadLine = format.parse(deadLineText.getText().toString());
                } catch(ParseException e) {
                    Log.e("AddActivity", "Parse Error");
                }
                todo.insert(title.getText().toString(), deadLine);
                finish();
            }
        });

        Toolbar toolbar = findViewById(R.id.addToolBar);
        toolbar.setTitle("Add ToDo");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24px);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker picker, int year, int month, int day) {
        deadLineText.setText(String.valueOf(year) + "-" + String.valueOf(month + 1) + "-" + String.valueOf(day));
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePiker();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}
