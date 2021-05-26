package m335.penz.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Pendency {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;

    @ColumnInfo(name="completion_date")
    private Date completionDate;

    private String priority;

    public int getId() {
        return id;
    }

    public Pendency setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Pendency setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Pendency setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public Pendency setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
        return this;
    }

    public String getPriority() {
        return priority;
    }

    public Pendency setPriority(String priority) {
        this.priority = priority;
        return this;
    }
}
