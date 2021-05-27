package m335.penz.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

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

import m335.penz.R;
import m335.penz.model.Pendency;
import m335.penz.persistence.AppDatabase;
import m335.penz.persistence.PendencyDao;

/**
 * @author Severin Baur
 * @author Phearum Svay
 *
 * This class, CreateActivity, is responsible for the functionality of {@link R.layout.activity_create}
 */
public class CreateActivity extends AppCompatActivity {

    private TextInputEditText textInputEditTextCalendar;
    private AutoCompleteTextView autoCompleteTextView;
    private ImageButton closeButton;
    private TextInputLayout textInputLayoutTitel;
    private FloatingActionButton saveButton;
    private TextInputEditText titel;
    private TextInputEditText description;
    private PendencyDao pendencyDao;

    /**
     * onCreate creates the view with the functionalities
     * 1. Initialize elements
     * 2. Initialize "repository"
     * 3. Setup Date-Picker
     * 4. Setup Priority-Picker
     * 5. Setup Save-Button
     * 6. Save Pendenz
     * @param savedInstanceState
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        findItems();
        pendencyDao = AppDatabase.getAppDb(getApplicationContext()).getPendencyDao();
        setupCloseButton();
        setupDatePick();
        setupPriorityPick();
        setupSaveButton();
    }

    private void setupCloseButton(){
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * setupPriorityPick sets the prioritiy-picker up
     * 1. Initialize options
     * 2. Change the text when priority is chosen
     */
    private void setupPriorityPick() {
        String[] option = {getString(R.string.low), getString(R.string.standard), getString(R.string.high)};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.priority_item, option);
        autoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> autoCompleteTextView.setText(arrayAdapter.getItem((int) l).toString(), false));
        autoCompleteTextView.setAdapter(arrayAdapter);
    }

    /**
     * setupDatePick sets the date-picker up
     * 1. Create the calendar and show it
     * 2. Change the text when the date is picked
     */
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

    /**
     * formatHeaderTextToDote formats the header text of the date picker to "date"
     * 1. Parse the header text into "MMM dd, yyyy"-format
     * 2. Format the date into "dd.MM.yyyy"-format
     * 3. Return the date "selectedDate"
     * @param headerText
     * @return selectedDate
     */
    private String formatHeaderTextToDate(String headerText) {
        if (headerText.length() == 0) {
            return null;
        }
        DateFormat formatter1 = new SimpleDateFormat("MMM dd, yyyy");
        DateFormat finalFormatter = new SimpleDateFormat("dd.MM.yyyy");
        String selectedDate = null;
        try {
            selectedDate = finalFormatter.format(formatter1.parse(headerText));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return selectedDate;
    }

    /**
     * setUpSaveButton executes "savePendency()"
     */
    private void setupSaveButton() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePendency();
            }
        });
    }

    /**
     * findItems initializes all elements
     */
    private void findItems() {
        textInputEditTextCalendar = (TextInputEditText) findViewById(R.id.tE_datePicker);
        autoCompleteTextView = findViewById(R.id.tV_prio_menu);
        closeButton = findViewById(R.id.btn_close);
        saveButton = findViewById(R.id.btn_submit);
        titel = findViewById(R.id.tE_titel);
        description = findViewById(R.id.tE_beschreibung);
    }

    /**
     * savePendency puts all data into a pendency object
     * 1. Set & get title
     * 2. Set & get date
     * 3. Set & get description
     * 4. Set & get priority
     */
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

    /**
     * getPriorityAsInt converts the string priority to a certain int
     * @param autoCompleteTextView
     * @return
     */
    private int getPriorityAsInt(AutoCompleteTextView autoCompleteTextView) {
        if (autoCompleteTextView.getText().toString().equals(getString(R.string.low))) {
            return 1;
        } else if (autoCompleteTextView.getText().toString().equals(getString(R.string.standard))) {
            return 2;
        } else {
            return 3;
        }
    }

    /**
     * validateTitle validates if the pendenz has a title
     * @return boolean
     */
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