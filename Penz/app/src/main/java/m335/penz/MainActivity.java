package m335.penz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FloatingActionButton createButton;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createButton =  findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateActivity();
            }
        });

    }
    private void openCreateActivity(){
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

}