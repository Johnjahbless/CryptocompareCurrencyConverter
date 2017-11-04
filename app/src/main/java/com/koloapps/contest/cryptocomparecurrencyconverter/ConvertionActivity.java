package com.koloapps.contest.cryptocomparecurrencyconverter;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import com.koloapps.contest.cryptocomparecurrencyconverter.Model.Utils;

import java.text.DecimalFormat;

public class ConvertionActivity extends AppCompatActivity {
    DecimalFormat df = new DecimalFormat("####0.00");
    EditText first;
    TextView second;
    Spinner spinner, spinner2;
    //defind String Array for spinner
    String[] spinner_first = {"BTC - BitCoin", "ETH- Ethereum"};
    String[] spinner_second = {"USD - US Dollar", "EUR", "NAIRA - Nigeria", "GBP - British Pound",
            "ING - Indian Ruppe", "AUD - Austrialian Dollar", "CAD - Canadian Dollar",
            "SGD - Singapore Dollar", "CHF - Swiss Frame", "MYR - Malaysian Riggit", "JPY - Japanese Yen",
            "CNY - Chinese Yuan Renminbi", "NZD - New Zealand Dollar", "ZAR - South Africa Rand", "BRL - Brazilian Real",
            "SAR - Saudi Arabian Riyal", "KES - Kenyan Shilling", "KRW - South Korean Won", "GHS - Ghanaian Cedi",
            "ARS - Argentine Peso", "RUB - Russian Ruble"};

    //defined variable for spinner selected value
    double first_selected, second_selected;

    //defined variable for the value parsed from MainActivity
    double BtcGetUsd, EthGetUSD;

    //defined variable to store EditText value
    double getText;

    ImageView imageView;
    View line;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertion);

        // get value parsed from MainActivity
        BtcGetUsd = getIntent().getExtras().getDouble("btc");
        EthGetUSD = getIntent().getExtras().getDouble("eth");

        //initialized views
        first = (EditText) findViewById(   R.id.firstEdit);
        second = (TextView) findViewById(R.id.secondEdit);
        imageView = (ImageView) findViewById(R.id.img);
        line = findViewById(R.id.view);

        //initialized spinnerFirst
        spinner = (Spinner) findViewById(R.id.spinnerFirst);
        //define ArrayAdapter1 for the Spinner and String Array defined (spinner_first)
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ConvertionActivity.this,
                R.layout.support_simple_spinner_dropdown_item, spinner_first);
        //parse arrayAdapter1
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int pos = spinner.getSelectedItemPosition();
                if (pos == 0) {
                    imageView.setImageResource(R.drawable.btc_logo);
                    //get Spinner selected item value
                    first_selected = BtcGetUsd;

                    //Convert
                    double uu = (first_selected / second_selected) * getText;

                    //display in TextView
                    second.setText(df.format(uu));
                    line.setBackgroundColor(getResources().getColor(R.color.btcColor));
                }
                if (pos == 1) {
                    //get Spinner selected item value
                    first_selected = EthGetUSD;

                    //Convert
                    double uu = (first_selected / second_selected) * getText;

                    //display in TextView
                    second.setText(df.format(uu));
                    imageView.setImageResource(R.drawable.eth_logo);
                    line.setBackgroundColor(getResources().getColor(R.color.ethColor));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //initialized spinnersecond
        spinner2 = (Spinner) findViewById(R.id.spinnersecond);

        //define ArrayAdapter2 for the Spinner and String Array defined (spinner_second)
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(ConvertionActivity.this,
                R.layout.support_simple_spinner_dropdown_item, spinner_second);
        spinner2.setAdapter(arrayAdapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //get spinner2 selected item value to string
                String sel = spinner2.getSelectedItem().toString();

                //compare the value
                //if true second_selected should set currency exchange rate. e.g EUR = 1.16095
                if (sel == "USD - US Dollar") {
                    second_selected = 1;
                    //Convert
                    double uu = (first_selected / second_selected) * getText;
                    //display the result in TextView with Local Currency Symbol
                    second.setText(Utils.getCurrencySymbol("USD") + df.format(uu));
                } else if (sel == "EUR") {
                    second_selected = 1.16095;
                    double uu = (first_selected / second_selected) * getText;
                    second.setText(Utils.getCurrencySymbol("EUR") + df.format(uu));
                } else if (sel == "NAIRA - Nigeria") {
                    second_selected = 0.0033;
                    double uu = (first_selected / second_selected) * getText;
                    second.setText(Utils.getCurrencySymbol("NGN") + df.format(uu));
                } else if (sel == "GBP - British Pound") {
                    second_selected = 1.31281;
                    double uu = (first_selected / second_selected) * getText;
                    second.setText(Utils.getCurrencySymbol("GBP") + df.format(uu));
                } else if (sel == "ING - Indian Ruppe") {
                    second_selected = 0.01538;
                    double uu = (first_selected / second_selected) * getText;
                    second.setText(Utils.getCurrencySymbol("ING") + df.format(uu));
                } else if (sel == "AUD - Austrialian Dollar") {
                    second_selected = 0.76834;
                    double uu = (first_selected / second_selected) * getText;
                    second.setText(Utils.getCurrencySymbol("AUD") + df.format(uu));
                } else if (sel == "CAD - Canadian Dollar") {
                    second_selected = 0.78099;
                    double uu = (first_selected / second_selected) * getText;
                    second.setText(Utils.getCurrencySymbol("CAD") + df.format(uu));
                } else if (sel == "SGD - Singapore Dollar") {
                    second_selected = 0.73270;
                    double uu = (first_selected / second_selected) * getText;
                    second.setText(Utils.getCurrencySymbol("SGD") + df.format(uu));
                } else if (sel == "CHF - Swiss Franc") {
                    second_selected = 1.00224;
                    double uu = (first_selected / second_selected) * getText;
                    second.setText(Utils.getCurrencySymbol("CHF") + df.format(uu));
                } else if (sel == "MYR - Malaysian Riggit") {
                    second_selected = 0.23565;
                    double uu = (first_selected / second_selected) * getText;
                    second.setText(Utils.getCurrencySymbol("MYR") + df.format(uu));
                } else if (sel == "JPY - Japanese Yen") {
                    second_selected = 0.00880;
                    double uu = (first_selected / second_selected) * getText;
                    second.setText(Utils.getCurrencySymbol("JPY") + df.format(uu));
                } else if (sel == "CNY - Chinese Yuan Renminbi") {
                    second_selected = 0.15040;
                    double uu = (first_selected / second_selected) * getText;
                    second.setText(Utils.getCurrencySymbol("CNY") + df.format(uu));
                } else if (sel == "NZD - New Zealand Dollar") {
                    second_selected = 0.68779;
                    double uu = (first_selected / second_selected) * getText;
                    second.setText(Utils.getCurrencySymbol("NZD") + df.format(uu));
                } else if (sel == "ZAR - South Africa Rand") {
                    second_selected = 0.07097;
                    double uu = (first_selected / second_selected) * getText;
                    second.setText(Utils.getCurrencySymbol("ZAR") + df.format(uu));
                } else if (sel == "BRL - Brazilian Real") {
                    second_selected = 0.30903;
                    double uu = (first_selected / second_selected) * getText;
                    second.setText(Utils.getCurrencySymbol("BRL") + df.format(uu));
                } else if (sel == "SAR - Saudi Arabian Riyal") {
                    second_selected = 0.26665;
                    double uu = (first_selected / second_selected) * getText;
                    second.setText(Utils.getCurrencySymbol("SAR") + df.format(uu));
                } else if (sel == "KES - Kenyan Shilling") {
                    second_selected = 0.00962;
                    double uu = (first_selected / second_selected) * getText;
                    second.setText(Utils.getCurrencySymbol("KES") + df.format(uu));
                } else if (sel == "KRW - South Korean Won") {
                    second_selected = 0.00089;
                    double uu = (first_selected / second_selected) * getText;
                    second.setText(Utils.getCurrencySymbol("KRW") + df.format(uu));
                } else if (sel == "GHS - Ghanaian Cedi") {
                    second_selected = 0.22547;
                    double uu = (first_selected / second_selected) * getText;
                    second.setText(Utils.getCurrencySymbol("GHS") + df.format(uu));
                } else if (sel == "ARS - Argentine Peso") {
                    second_selected = 0.05687;
                    double uu = (first_selected / second_selected) * getText;
                    second.setText(Utils.getCurrencySymbol("ARS") + df.format(uu));
                } else if (sel == "RUB - Russian Ruble") {
                    second_selected = 0.01719;
                    double uu = (first_selected / second_selected) * getText;
                    second.setText(Utils.getCurrencySymbol("RUB") + df.format(uu));
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        first.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String fd = editable.toString();
                if (fd.length() > 0) {
                    getText = Double.valueOf(fd);
                    double uu = (first_selected / second_selected) * getText;
                    second.setText(df.format(uu));
                }
            }
        });
    }


}
