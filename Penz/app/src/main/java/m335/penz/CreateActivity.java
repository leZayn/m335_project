package m335.penz;

import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import m335.penz.model.Pendency;
import m335.penz.persistence.AppDatabase;
import m335.penz.persistence.PendencyDao;


public class CreateActivity extends AppCompatActivity {

    private TextInputEditText textInputEditTextCalendar;
    private AutoCompleteTextView autoCompleteTextView;

    private TextInputLayout textInputLayoutTitel;
    private FloatingActionButton saveButton;
    private TextInputEditText titel;
    private TextInputEditText description;
    private PendencyDao pendencyDao;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        findItems();
        pendencyDao = AppDatabase.getAppDb(getApplicationContext()).getPendencyDao();
        setupDatePick();
        setupPriorityPick();
        setupSaveButton();
    }

    private void setupPriorityPick() {
        String[] option = {getString(R.string.low), getString(R.string.standard), getString(R.string.high)};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.priority_item, option);
        autoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> autoCompleteTextView.setText(arrayAdapter.getItem((int) l).toString(), false));
        autoCompleteTextView.setAdapter(arrayAdapter);
    }

    private void setupDatePick() {
        textInputEditTextCalendar.setInputType(InputType.TYPE_NULL);
        textInputEditTextCalendar.setOnClickListener(view -> {
            MaterialDatePicker datePicker =
                    MaterialDatePicker.Builder.datePicker()
                            .setTitleText("Select date")
                            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                            .build();
            datePicker.addOnPositiveButtonClickListener(selection -> textInputEditTextCalendar.setText(formatHeaderTextToDate(datePicker.getHeaderText())));
            datePicker.show(getSupportFragmentManager(), "tag");
        });
    }


    private String formatHeaderTextToDate(String headerText) {
        if (headerText.length() == 0) {
            return null;
        }
        DateFormat formatter1 = new SimpleDateFormat("MMM dd, yyyy");
        DateFormat finalFormatter = new SimpleDateFormat("dd.MM.yyyy");
        String selectedDate = null;
        try {
            System.out.println("HEADER TEXT: " + headerText);
            System.out.println("DATE1: " + finalFormatter.format(formatter1.parse(headerText)));
            selectedDate = finalFormatter.format(formatter1.parse(headerText));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(selectedDate);
        return selectedDate;
    }

    private void setupSaveButton() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePendency();
            }
        });
    }

    private void findItems() {
        textInputEditTextCalendar = (TextInputEditText) findViewById(R.id.tE_datePicker);
        autoCompleteTextView = findViewById(R.id.tV_prio_menu);
        saveButton = findViewById(R.id.btn_submit);
        titel = findViewById(R.id.tE_titel);
        description = findViewById(R.id.tE_beschreibung);
    }

    private void savePendency() {
        if(validateTitle()){
            Pendency pendency = new Pendency();
            pendency.setTitle(titel.getText().toString());
            if (textInputEditTextCalendar.getText().toString().length() > 0)
                pendency.setCompletionDate(LocalDate.parse(textInputEditTextCalendar.getText().toString()));
            pendency.setDescription(description.getText().toString());
            pendency.setPriority(getPriorityAsInt(autoCompleteTextView));
            pendencyDao.insert(pendency);
            finish();
        }
    }

    private int getPriorityAsInt(AutoCompleteTextView autoCompleteTextView) {
        if (autoCompleteTextView.getText().toString().equals(getString(R.string.low))) {
            return 1;
        } else if (autoCompleteTextView.getText().toString().equals(getString(R.string.standard))) {
            return 2;
        } else {
            return 3;
        }

    }

    private boolean validateTitle() {
        textInputLayoutTitel = (TextInputLayout) findViewById(R.id.tF_titel);
        System.out.println("TITEL TEXT: " + titel.getText());
        if(titel.getText().length() == 0){
            textInputLayoutTitel.setError("Gib einen Titel an");
            return false;
        }
        return true;
    }
}