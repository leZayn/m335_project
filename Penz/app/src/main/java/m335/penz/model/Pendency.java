package m335.penz.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import m335.penz.R;

/**
 * @author Severin Baur
 * @author Phearum Svay
 *
 * This class, Pendency, is responsible for representation of each pendency
 */
@Entity
public class Pendency implements Comparable{
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;

    @ColumnInfo(name="completion_date")
    private LocalDate completionDate;

    private int priority;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Object o) {
        Pendency other = (Pendency) o;
        if (this.priority > other.priority) {
            return -1;
        } else if (this.priority == other.priority) {
            if (this.completionDate == null) {
                return 1;
            } else if (other.completionDate == null) {
                return -1;
            } else if (this.completionDate.isBefore(other.completionDate)) {
                return -1;
            } else if(this.completionDate.isAfter(other.completionDate)){
                return 1;
            }else{
                return 0;
            }
        } else {
            return 1;
        }
    }
}
