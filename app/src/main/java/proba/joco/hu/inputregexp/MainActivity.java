package proba.joco.hu.inputregexp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Regex;
import com.mobsandgeeks.saripaar.annotation.Required;


public class MainActivity extends Activity implements Validator.ValidationListener {

    Button btnCheck;
    TextView helloText;

    @Required(order = 0)
    @Regex(order = 0,pattern = "[A-Z]")
    private EditText etRegexp;

    String minta = "^/D{3}//.";

    Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        validator= new Validator(this);

        validator = new Validator(this);
        validator.setValidationListener(this);

        btnCheck = (Button) findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });

        helloText = (TextView) findViewById(R.id.textHello);
        etRegexp = (EditText) findViewById(R.id.etRegexp);


    }


    @Override
    public void onValidationSucceeded() {
        Toast.makeText(this,"Télleg csupa nagytetű", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        String message = failedRule.getFailureMessage();

        if (failedView instanceof EditText) {
            failedView.requestFocus();
            ((EditText) failedView).setError(message);
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

    }
}
