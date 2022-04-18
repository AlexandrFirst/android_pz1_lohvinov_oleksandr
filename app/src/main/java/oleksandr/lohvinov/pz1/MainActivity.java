package oleksandr.lohvinov.pz1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button addBtn;
    TextView textView;
    TableLayout tableLayout;
    CheckBox checkBox;

    boolean deletionModeOn;
    int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentIndex = 0;

        addBtn = findViewById(R.id.buttonId);
        textView = findViewById(R.id.textViewId);
        tableLayout = findViewById(R.id.tableLayoutId);
        checkBox = findViewById(R.id.checkBoxId);



        checkBox.setOnClickListener(view -> {
            deletionModeOn = ((CheckBox) view).isChecked();
            if (deletionModeOn) {
                addBtn.setText("Delete last row");
            } else {
                addBtn.setText("Add row");
            }
        });

        if(savedInstanceState!= null){
            checkBox.setChecked(savedInstanceState.getBoolean("checkBoxState"));
            int lastLineIndex = savedInstanceState.getInt("lastElementIndex");
            drawMultipleLines(lastLineIndex);
        }

        addBtn.setOnClickListener(view -> {
            if (deletionModeOn) {
                if (currentIndex > 0) {
                    tableLayout.removeViewAt(currentIndex);
                    currentIndex--;
                } else {
                    Toast.makeText(MainActivity.this,
                            "No more rows delete",
                            Toast.LENGTH_LONG)
                            .show();
                }
            } else {
                drawLine();
            }
        });
    }

    private void drawMultipleLines(int lastLineIndex)
    {
        for (int i = 0; i < lastLineIndex; i++)
        {
            drawLine();
        }
    }

    private void drawLine() {
        TableRow newTableRow = (TableRow) LayoutInflater.from(MainActivity.this).
                inflate(R.layout.table_line, null);

        TextView column1 = newTableRow.findViewById(R.id.columnFirstId);
        TextView column2 = newTableRow.findViewById(R.id.columnSecondId);
        TextView column3 = newTableRow.findViewById(R.id.columnThirdId);

        column1.setText("Some info " + currentIndex);
        column2.setText("Some other info " + currentIndex);
        column3.setText("Information " + currentIndex);

        currentIndex++;

        tableLayout.addView(newTableRow);
    }

    @Override
    protected void onSaveInstanceState(@Nullable Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("lastElementIndex", currentIndex);
        savedInstanceState.putBoolean("checkBoxState", deletionModeOn);
    }
}