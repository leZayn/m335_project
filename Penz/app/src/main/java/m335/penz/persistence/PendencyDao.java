package m335.penz.persistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import m335.penz.model.Pendency;

@Dao
public interface PendencyDao {
    @Query("SELECT * FROM pendency")
    List<Pendency> getAll();

    @Insert
    void insert(Pendency pendency);
}
