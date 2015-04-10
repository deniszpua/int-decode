package com.github.deniszpua.itndecode;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.EditText;

import com.github.deniszpua.itndecode.decoder.ITNDecode;
import com.github.deniszpua.itndecode.decoder.ITNDecoder;


public class DecodeActivity extends ActionBarActivity {
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decode);

        //initialize fields
        editText = (EditText) findViewById(R.id.numberInput);
        textView = (TextView) findViewById(R.id.decodeMessage);

        //registering editText action listener
        editText.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    verifyInput(editText.getText().toString());
                    handled = true;
                }
                return handled;
            }
        });
    }
    private void verifyInput(String input) {
        //TODO check input number for correctness and
        //show appropriate message after editing field
        System.out.printf("Input string: %s%n", input);

        ITNDecode itn = new ITNDecoder(input);
        if (itn.isValid()) {
            textView.setText(R.string.correctStringMessage);
        }
        else if (itn.getControlDigitValue() != ITNDecode.UNKNOWN) {
            String checkMessage = getString(R.string.checkControlDigit);
            textView.setText(String.format(checkMessage, itn.getControlDigitValue()));
        }
        else {
            textView.setText(R.string.incorrectNumberFormat);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_decode, menu);
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

    public void displayDecodeResult(View view) {
        String input = editText.getText().toString();
        ITNDecode itn = new ITNDecoder(input);
        if (itn.isValid()) {
            textView.setText(
                    String.format(
                            getString(R.string.itn_data_message),
                            itn.getBirthday(),
                            itn.getSex()
                    )
            );
        }
        else {
            verifyInput(input);
        }
    }
}
