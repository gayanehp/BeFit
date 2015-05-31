package com.example.gayaneh.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

public class BasicActivity extends Activity {

    private String buffer="";
    private double op1,op2=0;
    private Character operator=null;





    private Button button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9,button_0;
    private Button addButton, subButton,mulButton,divButton,eqlButton,decButton, delete;

    private EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        button_0 = (Button)findViewById(R.id.button14);
        button_1 = (Button)findViewById(R.id.button9);
        button_2 = (Button)findViewById(R.id.button10);
        button_3 = (Button)findViewById(R.id.button11);
        button_4 = (Button)findViewById(R.id.button5);
        button_5 = (Button)findViewById(R.id.button6);
        button_6 = (Button)findViewById(R.id.button7);
        button_7 = (Button)findViewById(R.id.button1);
        button_8 = (Button)findViewById(R.id.button2);
        button_9 = (Button)findViewById(R.id.button3);
        divButton = (Button)findViewById(R.id.button4);
        mulButton = (Button)findViewById(R.id.button8);
        subButton = (Button)findViewById(R.id.button12);
        decButton = (Button)findViewById(R.id.button13);
        eqlButton = (Button)findViewById(R.id.button15);
        addButton = (Button)findViewById(R.id.button16);
        delete = (Button)findViewById(R.id.delete);
        text = (EditText) findViewById(R.id.inputNumber);


        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            buffer= buffer+'1';
            text.setText(buffer);


            }
        });
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buffer=buffer+'2';
                text.setText(buffer);


            }
        });
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buffer=buffer+'3';
                text.setText(buffer);


            }
        });
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buffer=buffer+'4';
                text.setText(buffer);


            }
        });
        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buffer=buffer+'5';
                text.setText(buffer);


            }
        });
        button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buffer=buffer+'6';
                text.setText(buffer);


            }
        });
        button_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buffer=buffer+'7';
                text.setText(buffer);


            }
        });
        button_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buffer=buffer+'8';
                text.setText(buffer);


            }
        });
        button_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buffer=buffer+'9';
                text.setText(buffer);


            }
        });
        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buffer.equals(""))
                        buffer="0";
                else
                if (buffer.charAt(buffer.lastIndexOf(buffer))!= '+'&& buffer.charAt(buffer.lastIndexOf(buffer))!= '-' &&
                        buffer.charAt(buffer.lastIndexOf(buffer))!= 'x' && buffer.charAt(buffer.lastIndexOf(buffer))!= '/' &&
                        buffer.length()!=0   )
                    buffer=buffer+'0';
                text.setText(buffer);


            }
        });

        decButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buffer.equals(""))
                    buffer="0.";
                else
                    buffer=buffer+'.';
                text.setText(buffer);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if  (!buffer.equals(""))
                    buffer=buffer.substring(0,buffer.length()-1);
                text.setText(buffer);

            }
        });

        eqlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int decIndex=buffer.length()-buffer.indexOf('.')-1;
                if (buffer.equals(""))
                    buffer="0";
                if (buffer.indexOf('.')!=-1)
                    buffer = buffer.replace(".", "");
                else decIndex=0;
                if (operator == null ) {
                    if (op1 == 0.0) {
                        op1 = Integer.parseInt(buffer)/Math.pow(10,decIndex);
                        op1 = op1 * 2;
                    }
                }
                else
                if (op1==0.0)
                    op1 = Integer.parseInt(buffer)/Math.pow(10,decIndex);
                else
                    op2=Integer.parseInt(buffer)/Math.pow(10,decIndex);

                if (operator != null)
                    switch (operator){
                        case '+':{
                            op1=op1+op2;
                            break;
                        }
                        case '-':{
                            op1=op1-op2;
                            break;
                        }
                        case '/':{
                            if (op2 != 0.0)
                              op1=op1/op2;
                            else
                              buffer="NaN";
                            break;

                        }
                        case 'x':{
                            op1=op1*op2;
                            break;
                        }

                        default: operator=null;
                                 op1=0.0;

                    }
                if (! buffer.equals("NaN") )
                    text.setText(String.valueOf(op1));
                else
                    text.setText("Divided By Zero... NaN");
                buffer="";
                op2=0.0;
                operator='=';

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int decIndex=buffer.length()-buffer.indexOf('.')-1;
                if (buffer.equals(""))
                    buffer="0";
                if (buffer.indexOf('.')!=-1)
                    buffer = buffer.replace(".", "");
                else decIndex=0;
                if (operator == null) {
                    if (op1==0.0)
                        op1 = Integer.parseInt(buffer)/Math.pow(10,decIndex);
                    else
                    if (op1==0.0)
                        op1 = Integer.parseInt(buffer)/Math.pow(10,decIndex);
                    else
                        op2 = Integer.parseInt(buffer)/Math.pow(10,decIndex);
                }
                else
                    op2 = Integer.parseInt(buffer)/Math.pow(10,decIndex);
                if (operator != null)
                    switch (operator){
                        case '+':{
                            op1=op1+op2;
                            break;
                        }
                        case '-':{
                            op1=op1-op2;
                            break;
                        }
                        case '/':{
                            if (op2 != 0.0)
                                op1=op1/op2;
                            else
                                buffer="NaN";
                            break;

                        }
                        case 'x':{
                            op1=op1*op2;
                            break;

                        }

                        default: op1=op1*1;
                    }
                operator = '+';
                buffer="";
                op2=0;
                if (! buffer.equals("NaN") )
                    text.setText(buffer);
                else
                    text.setText("Divided By Zero... NaN");

                }
        });

        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int decIndex=buffer.length()-buffer.indexOf('.')-1;
                if (buffer.equals(""))
                    buffer="0";
                if (buffer.indexOf('.')!=-1)
                    buffer = buffer.replace(".", "");
                else decIndex=0;
                if (operator == null) {
                    if (op1==0.0)
                        op1 = Integer.parseInt(buffer)/Math.pow(10,decIndex);
                    else
                    if (op1==0.0)
                        op1 = Integer.parseInt(buffer)/Math.pow(10,decIndex);
                    else
                        op2 = Integer.parseInt(buffer)/Math.pow(10,decIndex);
                }
                else
                    op2 = Integer.parseInt(buffer)/Math.pow(10,decIndex);
                if (operator != null)
                    switch (operator){
                        case '+':{
                            op1=op1+op2;
                            break;
                        }
                        case '-':{
                            op1=op1-op2;
                            break;
                        }
                        case '/':{
                            if (op2 != 0.0)
                                op1=op1/op2;
                            else
                                buffer="NaN";
                            break;

                        }
                        case 'x':{
                            op1=op1*op2;
                            break;

                        }

                        default: op1=op1*1;
                    }
                operator = '-';
                buffer="";
                op2=0;
                if (! buffer.equals("NaN") )
                    text.setText(buffer);
                else
                    text.setText("Divided By Zero... NaN");
            }
        });

        mulButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int decIndex=buffer.length()-buffer.indexOf('.')-1;
                if (buffer.equals(""))
                    buffer="0";
                if (buffer.indexOf('.')!=-1)
                    buffer = buffer.replace(".", "");
                else decIndex=0;
                if (operator == null) {
                    if (op1==0.0)
                       op1 = Integer.parseInt(buffer)/Math.pow(10,decIndex);
                    else
                    if (op1==0.0)
                        op1 = Integer.parseInt(buffer)/Math.pow(10,decIndex);
                    else
                        op2 = Integer.parseInt(buffer)/Math.pow(10,decIndex);
                }
                else
                    op2 = Integer.parseInt(buffer)/Math.pow(10,decIndex);
                if (operator != null)
                    switch (operator){
                        case '+':{
                            op1=op1+op2;
                            break;
                        }
                        case '-':{
                            op1=op1-op2;
                            break;
                        }
                        case '/':{
                            if (op2 != 0.0)
                                op1=op1/op2;
                            else
                                buffer="NaN";
                            break;

                        }
                        case 'x':{
                            op1=op1*op2;
                            break;

                        }

                        default: op1=op1*1;
                    }
                operator = 'x';
                buffer="";
                op2=0;
                if (! buffer.equals("NaN") )
                    text.setText(buffer);
                else
                    text.setText("Divided By Zero... NaN");
            }
        });

        divButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int decIndex=buffer.length()-buffer.indexOf('.')-1;
                if (buffer.equals(""))
                    buffer="0";
                if (buffer.indexOf('.')!=-1)
                    buffer = buffer.replace(".", "");
                else decIndex=0;
                if (operator == null) {
                    if (op1==0.0)
                        op1 = Integer.parseInt(buffer)/Math.pow(10,decIndex);
                    else
                    if (op1==0.0)
                        op1 = Integer.parseInt(buffer)/Math.pow(10,decIndex);
                    else
                        op2 = Integer.parseInt(buffer)/Math.pow(10,decIndex);
                }
                else
                    op2 = Integer.parseInt(buffer)/Math.pow(10,decIndex);
                if (operator != null)
                    switch (operator){
                        case '+':{
                            op1=op1+op2;
                            break;
                        }
                        case '-':{
                            op1=op1-op2;
                            break;
                        }
                        case '/':{
                            if (op2 != 0.0)
                                op1=op1/op2;
                            else
                                buffer="NaN";
                            break;

                        }
                        case 'x':{
                            op1=op1*op2;
                            break;

                        }

                        default: op1=op1*1;
                    }
                operator = '/';
                buffer="";
                op2=0.0;
                if (! buffer.equals("NaN") )
                    text.setText(buffer);
                else
                    text.setText("Divided By Zero... NaN");
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_basic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch ( item.getItemId())
        {
            case R.id.action_Scientific: {


                Intent intent = new Intent(BasicActivity.this,ScientificActivity.class);
                intent.putExtra("buffer", buffer);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
            case R.id.action_Basic:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
