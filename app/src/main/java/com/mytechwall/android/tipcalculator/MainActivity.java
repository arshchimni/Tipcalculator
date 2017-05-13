package com.mytechwall.android.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textViewBillTotal)
    TextView billTotalAmount;

    @BindView(R.id.normalService)
    ImageButton normalService;

    @BindView(R.id.goodService)
    ImageButton goodService;

    @BindView(R.id.excellentService)
    ImageButton execllentService;

    @BindView(R.id.textViewTipPercent)
    TextView textViewTipPercent;

    @BindView(R.id.tipTotal)
    TextView tipTotal;

    @BindView(R.id.editTextBillAmount)
    EditText editTextBillAmount;

    float finalBillAmount = 0;
    float tipPercent1 = 0;
    float tipTotal1 = 0;
    float enterBillAmount;

    float NORMAL_TIP = 10;
    float GOOD_TIP = 15;
    float EXCELLENT_TIP = 20;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        /*String savedBillAmount="";
        String savedTipTotal="";
        String savedTipPercent="";*/
        if (savedInstanceState!=null){
            finalBillAmount= Float.parseFloat(savedInstanceState.getString("BILL_TOTAL"));
            tipPercent1= Float.parseFloat(savedInstanceState.getString("TIP_PERCENT"));
            tipTotal1= Float.parseFloat(savedInstanceState.getString("TIP_TOTAL"));
        }
        setTipValues();


    }

    private void setTipValues() {

        billTotalAmount.setText(getString(R.string.msin_msg_billtotalresult, finalBillAmount));
        tipTotal.setText(getString(R.string.msin_msg_tiptotal, tipTotal1));
        textViewTipPercent.setText(getString(R.string.main_msg_tippercent, tipPercent1));
    }


    @OnClick({R.id.normalService, R.id.goodService, R.id.excellentService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.normalService:
                tipPercent1 = NORMAL_TIP;
                break;
            case R.id.goodService:
                tipPercent1 = GOOD_TIP;
                break;
            case R.id.excellentService:
                tipPercent1 = EXCELLENT_TIP;
                break;
        }
        calculateFinalBill();
        setTipValues();
    }

    @OnTextChanged(R.id.editTextBillAmount)
    public void onTextChanged() {
        calculateFinalBill();
        setTipValues();

    }

    private void calculateFinalBill() {
        if (tipPercent1 == 0) {
            tipPercent1 = GOOD_TIP;
        }
        if (!editTextBillAmount.getText().toString().equals("")&&!editTextBillAmount.getText().toString().equals(".")
                &&editTextBillAmount.getText().toString()!=null){
            enterBillAmount=Float.valueOf(editTextBillAmount.getText().toString());
        }
        else {
            enterBillAmount=0;
        }
        tipTotal1=(enterBillAmount*tipPercent1)/100;
        finalBillAmount=enterBillAmount+tipTotal1;


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("BILL_TOTAL",billTotalAmount.getText().toString());
        outState.putString("TIP_TOTAL",tipTotal.getText().toString());
        outState.putString("TIP_PERCENT",textViewTipPercent.getText().toString());

        super.onSaveInstanceState(outState);
    }
}
