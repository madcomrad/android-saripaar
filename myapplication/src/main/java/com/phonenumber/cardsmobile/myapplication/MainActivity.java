package com.phonenumber.cardsmobile.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.*;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Validator.ValidationListener {

    @Order(1)
    @Min(value = 2)
    @Max(value = 100, flags = Rule.FLAG_FOCUS_LOST)
    EditText text1;

    @Order(2)
    @Min(value = 2)
    EditText text2;

    EditText text3;
    Button button;
    Validator mValidator;

    TextWatcher mTextWatchertWacher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            mValidator.validate(false);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 = (EditText) findViewById(R.id.editText);
        text2 = (EditText) findViewById(R.id.editText2);
        text3 = (EditText) findViewById(R.id.editText3);

        text1.setTag("Text 1");
        text1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    mValidator.validateOnly(v);
                }
            }
        });
        text2.setTag("Text 2");

        text1.addTextChangedListener(mTextWatchertWacher);
        text2.addTextChangedListener(mTextWatchertWacher);
        text3.addTextChangedListener(mTextWatchertWacher);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mValidator.validate(true);
            }
        });

        mValidator = new Validator(this);
        mValidator.setValidationListener(this);
        mValidator.setValidationMode(Validator.Mode.IMMEDIATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onValidationSucceeded(boolean ultimate) {
        if (ultimate) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors, boolean ultimate) {
        Toast.makeText(this, (ultimate ? "ULTIMATE FAIL" : "FAIL") + errors.get(0).getView().getTag().toString(), Toast.LENGTH_SHORT).show();
    }
}
