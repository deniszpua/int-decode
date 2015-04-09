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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decode);

        //registering editText action listener
        final EditText editText = (EditText) findViewById(R.id.numberInput);
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

        TextView message = (TextView) findViewById(R.id.decodeMessage);
        ITNDecode itn = new ITNDecoder(input);
        if (itn.isValid()) {
            message.setText(R.string.correctStringMessage);
        }
        else if (itn.getControlDigitValue() != ITNDecode.UNKNOWN) {
            String checkMessage = getString(R.string.checkControlDigit);
            message.setText(String.format(checkMessage, itn.getControlDigitValue()));
        }
        else {
            message.setText(R.string.incorrectNumberFormat);
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
        //TODO
    }
}
