package com.patrickshim.univtodolist.tasks;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by patrickshim on 27/04/2017.
 */

@DatabaseTable(tableName = Task.TABLE_NAME_TASKS)
public class Task {

    public static final String TABLE_NAME_TASKS = "tasks";

    public static final String FIELD_NAME_ID     = "id";
    public static final String FIELD_NAME_NAME   = "name";
    public static final String FIELD_NAME_DATE   = "createdAt";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int mId;

    @DatabaseField(columnName = FIELD_NAME_NAME)
    private String text;

    @DatabaseField(columnName = FIELD_NAME_DATE)
    private Date createdAt;

    public Task() {
    }

    public Task(String text, Date createdAt) {
        this.text = text;
        this.createdAt = createdAt;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
