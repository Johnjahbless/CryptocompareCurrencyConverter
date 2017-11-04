package com.koloapps.contest.cryptocomparecurrencyconverter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;


import com.koloapps.contest.cryptocomparecurrencyconverter.APICALL.APIClient;
import com.koloapps.contest.cryptocomparecurrencyconverter.APICALL.APIService;
import com.koloapps.contest.cryptocomparecurrencyconverter.Model.BTC;
import com.koloapps.contest.cryptocomparecurrencyconverter.Model.CryptoCrcy;
import com.koloapps.contest.cryptocomparecurrencyconverter.Model.ETH;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    CryptoCrcy cryptoCrcy;
    BTC btc;
    ETH eth;

    double BtcGetUsd, EthGetUSD;
    ProgressDialog progressDialog;
    TextView textView_btc;
    TextView textView_eth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialized progressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait ....");
        //start progressDialog
        progressDialog.show();

        LoadCryptoCompaire();

        textView_btc = (TextView) findViewById(R.id.btc_Text);
        textView_eth = (TextView) findViewById(R.id.eth_Text);

        //initialized cardView
        CardView cardView_btc = (CardView) findViewById(R.id.btc_card);
        CardView cardView_eth = (CardView) findViewById(R.id.eth_card);

        cardView_btc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //parse both BTC & ETH value to ConvertionActivity
                Intent intent = new Intent(getApplicationContext(), ConvertionActivity.class);
                intent.putExtra("btc", BtcGetUsd);
                intent.putExtra("eth", EthGetUSD);
                startActivity(intent);
            }
        });
        cardView_eth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //parse both BTC & ETH value to ConvertionActivity
                Intent intent = new Intent(getApplicationContext(), ConvertionActivity.class);
                intent.putExtra("btc", BtcGetUsd);
                intent.putExtra("eth", EthGetUSD);
                startActivity(intent);
            }
        });

    }


    //defined method for the response
    public void LoadCryptoCompaire() {
        //initialized the APIClient
        APIClient apiClient = new APIClient();
        try {
            //defined APIService to get client response
            APIService apiService = APIClient.getClient().create(APIService.class);
            //call client response
            Call<CryptoCrcy> btcCall = apiService.getBTC();
            btcCall.enqueue(new Callback<CryptoCrcy>() {
                @Override
                public void onResponse(Call<CryptoCrcy> call, Response<CryptoCrcy> response) {
                    //initialized respond class
                    cryptoCrcy = new CryptoCrcy();
                    cryptoCrcy = response.body();

                    //initialized the json object class
                    btc = new BTC();
                    eth = new ETH();

                    btc = cryptoCrcy.getBTC();
                    eth = cryptoCrcy.getETH();

                    //parse object to variable
                    BtcGetUsd = btc.getUSD();
                    EthGetUSD = eth.getUSD();

                    //display result in TextView with Local Currency Symbol
                    textView_btc.setText("1 BTC : " + Utils.getCurrencySymbol("USD") + BtcGetUsd);
                    textView_eth.setText("1 ETH : " + Utils.getCurrencySymbol("USD") + EthGetUSD);

                    //stop progressDialog
                    progressDialog.dismiss();


                }

                @Override
                public void onFailure(Call<CryptoCrcy> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        } catch (Exception e) {

        }
    }

}
