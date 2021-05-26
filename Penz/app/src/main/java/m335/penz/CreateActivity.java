package m335.penz;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.List;

import m335.penz.persistence.PendencyDao;

public class CreateActivity extends AppCompatActivity {

    private PendencyDao pendencyDao;

    private DatePickerDialog datePickerDialog;
    private TextInputEditText textInputEditTextCalendar;
    private TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        textInputEditTextCalendar = (TextInputEditText)findViewById(R.id.tE_datePicker);
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

        List<String> items = Arrays.asList("Option 1", "Option 2", "Option 3", "Option 4");
        ArrayAdapter adapter = ArrayAdapter(requireContext(), R.layout.activity_create, items);
        (textInputLayout.getEditText() as? AutoCompleteTextView)?.setAdapter(adapter)
    }
}
