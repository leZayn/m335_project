package m335.penz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import m335.penz.model.Pendency;
import m335.penz.persistence.PendencyDao;

public class MainActivity extends AppCompatActivity {

    private PendencyDao pendencyDao;

    private FloatingActionButton createButton;
    private ImageView smiley;
    private TextView noPendency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        if (pendencies.size() > 0){
            smiley.setVisibility(View.GONE);
            noPendency.setVisibility(View.GONE);
        }else{
            return;
        }
    }

    private void findItems(){
        createButton =  findViewById(R.id.createButton);
        smiley = findViewById(R.id.img_smiley);
        noPendency = findViewById(R.id.noPendency);
    }
    private void openCreateActivity(){
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

}