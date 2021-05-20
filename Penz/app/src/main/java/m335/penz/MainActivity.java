package m335.penz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button createButton;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
   /* private void openActivityCreatePendenz(){
        Intent intent = new Intent(this, )
    }*/
}