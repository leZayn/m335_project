package m335.penz;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContentProviderCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

public class CreateActivity extends AppCompatActivity {
    private TextInputEditText textInputEditTextCalendar;
    AutoCompleteTextView autoCompleteTextView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        textInputEditTextCalendar = (TextInputEditText) findViewById(R.id.tE_datePicker);
        textInputEditTextCalendar.setInputType(InputType.TYPE_NULL);
        textInputEditTextCalendar.setOnClickListener(view -> {
            System.out.println("--------------- CLICK ---------------");
            MaterialDatePicker datePicker =
                    MaterialDatePicker.Builder.datePicker()
                            .setTitleText("Select date")
                            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                            .build();
            datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                @Override
                public void onPositiveButtonClick(Object selection) {
                    textInputEditTextCalendar.setText(datePicker.getHeaderText());
                }
            });
            datePicker.show(getSupportFragmentManager(), "tag");
            System.out.println("--------------- CLICK2 ---------------");
        });
        autoCompleteTextView = findViewById(R.id.tV_prio_menu);
        String[] option = {"Niedrig", "Normal", "Hoch"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.priority_item, option);
        autoCompleteTextView.setText(arrayAdapter.getItem(0).toString(), false);
        autoCompleteTextView.setAdapter(arrayAdapter);
    }
}
