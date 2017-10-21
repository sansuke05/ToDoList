package study.todolist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.os.Handler;

import java.text.SimpleDateFormat;
import java.util.List;

public class ToDoListAdapter extends ArrayAdapter<ToDoItem> {

    private LayoutInflater layoutInflater;
    private ToDoAdapter todo;

    public ToDoListAdapter(Context context, int textViewResourceId, List<ToDoItem> objects) {
        super(context, textViewResourceId, objects);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        todo = new ToDoAdapter(context);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ToDoItem detail = getItem(position);

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.row, null);
        }

        final CheckBox checkBox = convertView.findViewById(R.id.title);
        checkBox.setText(detail.getTitle());

        SimpleDateFormat format = new SimpleDateFormat(getContext().getResources().getString(R.string.dateFormat));
        TextView deadLine = convertView.findViewById(R.id.deadLineView);
        deadLine.setText(format.format(detail.getDeadLine()));

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox c = (CheckBox) view;
                if (c.isChecked()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (checkBox.isChecked()) {
                                todo.remove(detail.getId());
                                ((MainActivity) getContext()).listInit();
                            }
                        }
                    }, 2000);

                    ((ViewGroup) c.getParent()).setBackgroundColor(R.color.colorSub);
                } else {
                    ((ViewGroup) c.getParent()).setBackgroundColor(Color.WHITE);
                }
            }
        });

        return convertView;
    }

}
