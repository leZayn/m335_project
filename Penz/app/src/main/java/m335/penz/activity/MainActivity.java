package m335.penz.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.List;

import m335.penz.pendency.PendencyAdapter;
import m335.penz.R;
import m335.penz.model.Pendency;
import m335.penz.persistence.AppDatabase;
import m335.penz.persistence.PendencyDao;

/**
 * @author Severin Baur
 * @author Phearum Svay
 *
 * This class, MainActivity, is responsible for the functionality of {@link R.layout.activity_create}
 */
public class MainActivity extends AppCompatActivity {

    private PendencyDao pendencyDao;

    private FloatingActionButton createButton;
    private ImageView smiley;
    private TextView noPendency;

    private RecyclerView pendencyView;

    /**
     * onCreate creates the view with the functionalities
     * 1. Initialize elements
     * 2. Initialize "repository"
     * 3. Prints all pendencies, if there are pendencies
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findItems();
        pendencyDao = AppDatabase.getAppDb(getApplicationContext()).getPendencyDao();
        printPendencies();
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateActivity();
            }
        });
    }

    /**
     * printPendencies prints all pendencies if there are some available
     * 1. Check if there are pendencies
     * (2. Set visibility of image and text of "no pendencies" to GONE
     * 3. Print all certain pendencies)
     */
    private void printPendencies(){
        List<Pendency> pendencies = pendencyDao.getAll();
        Collections.sort(pendencies);
        if (pendencies.size() > 0){
            smiley.setVisibility(View.GONE);
            noPendency.setVisibility(View.GONE);
            GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
            pendencyView.setLayoutManager(layoutManager);
            PendencyAdapter pendencyAdapter = new PendencyAdapter(pendencies);
            pendencyView.setAdapter(pendencyAdapter);
        }else{
            return;
        }
    }

    /**
     * onResume
     */
    @Override
    protected void onResume() {
        super.onResume();
        printPendencies();
    }

    /**
     * findItems initializes all elements
     */
    private void findItems(){
        createButton =  findViewById(R.id.createButton);
        smiley = findViewById(R.id.img_smiley);
        noPendency = findViewById(R.id.noPendency);
        pendencyView = findViewById(R.id.pendencyView);
    }

    /**
     * openCreateActivity creates a new activity {@link R.layout.activity_create}
     */
    private void openCreateActivity(){
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

}