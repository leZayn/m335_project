package m335.penz;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import m335.penz.persistence.PendencyDao;

public class CreateActivity extends AppCompatActivity {

    private PendencyDao pendencyDao;

    private DatePickerDialog datePickerDialog;
    private TextInputEditText textInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        textInputEditText = findViewById(R.id.outlinedTextInputLayout_date_wrap);
        textInputEditText.setInputType(InputType.TYPE_NULL);
        textInputEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("--------------- CLICK ---------------");
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(CreateActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        textInputEditText.setText(day + "." + (month + 1) + "." + year);
                    }
                }, year, month, day);

                datePickerDialog.show();

                System.out.println("--------------- CLICK2 ---------------");
            }
        });
    }
}
