package m335.penz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import m335.penz.model.Pendency;
import m335.penz.persistence.AppDatabase;
import m335.penz.persistence.PendencyDao;

public class MainActivity extends AppCompatActivity {

    private PendencyDao pendencyDao;

    private FloatingActionButton createButton;
    private ImageView smiley;
    private TextView noPendency;

    private RecyclerView pendencyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pendencyDao = AppDatabase.getAppDb(getApplicationContext()).getPendencyDao();
        findItems();
        printPendencies();
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateActivity();
            }
        });
    }

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

    @Override
    protected void onResume() {
        super.onResume();
        printPendencies();
    }

    private void findItems(){
        createButton =  findViewById(R.id.createButton);
        smiley = findViewById(R.id.img_smiley);
        noPendency = findViewById(R.id.noPendency);
        pendencyView = findViewById(R.id.pendencyView);
    }
    private void openCreateActivity(){
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

}