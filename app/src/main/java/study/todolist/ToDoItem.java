package study.todolist;

import java.util.Date;

public class ToDoItem {

    private int id;
    private String title;
    private Date deadLine;

    public void setId(int _id) {
        this.id = _id;
    }

    public int getId() {
        return this.id;
    }

    public void setTitle(String _title) {
        this.title = _title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setDeadLine(Date _deadLine) {
        this.deadLine = _deadLine;
    }

    public Date getDeadLine() {
        return this.deadLine;
    }

}
