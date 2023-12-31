package com.example.myapplication;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private StringBuffer input = new StringBuffer();
    // 声明一个Map来建立视图和字符之间的映射
    private HashMap<Integer, String> map;
    private boolean isResult = false;

    private void setOutput(String output) {
        TextView tv = (TextView) findViewById(R.id.text);
        if (output.equals("")) {
            tv.setText("0");
        } else {

            tv.setText(output.replace('/', '÷').replace('*', '×'));

        }
    }

    private OnClickListener btnClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            try {

                String s = map.get(id);
                if (Objects.equals(s, "AC")) {
                    isResult=true;
                    input = new StringBuffer();
                    setOutput(input.toString());
                } else if (Objects.equals(s, "backspace")) {
                    if (input.length() != 0&& !isResult) {
                        input.deleteCharAt(input.length() - 1);
                        setOutput(input.toString());
                    }

                } else if (Objects.equals(s, "change")) {
                    setOutput("change");
                } else if (Objects.equals(s, "=")) {
                    if (input.length() != 0) {
                        Double result = (Double) engine.eval(input.toString().replaceAll("%", "*0.01") + "+0");

                        if (result % 1 == 0) {
                            int intValue = result.intValue();
                            input = new StringBuffer(Integer.toString(intValue));
                            setOutput("=" + input.toString());
                            isResult=true;
                        } else {
                            input = new StringBuffer(result.toString());
                            setOutput("=" + result.toString());
                            isResult=true;

                        }
                    }

                } else if (isOperation(s)) {
                    isResult=false;
                    if (isOperation(input.charAt(input.length() - 1) + ""))
                        input.deleteCharAt(input.length() - 1);
                    input.append(s);
                    setOutput(input.toString());
                } else {
                    isResult=false;
                    input.append(s);
                    setOutput(input.toString());
                }

            } catch (Exception e) {
                setOutput("Input is wrong.");
            }

        }
    };

    private boolean isOperation(String s) {
        return Objects.equals(s, "+") ||
                Objects.equals(s, "-") ||
                Objects.equals(s, "*") ||
                Objects.equals(s, "/");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        map = new HashMap<Integer, String>();
        map.put(R.id.number_0, "0");
        map.put(R.id.number_1, "1");
        map.put(R.id.number_2, "2");
        map.put(R.id.number_3, "3");
        map.put(R.id.number_4, "4");
        map.put(R.id.number_5, "5");
        map.put(R.id.number_6, "6");
        map.put(R.id.number_7, "7");
        map.put(R.id.number_8, "8");
        map.put(R.id.number_9, "9");
        map.put(R.id.ac, "AC");
        map.put(R.id.backspace, "backspace");
        map.put(R.id.change, "change");
        map.put(R.id.decimal, ".");
        map.put(R.id.equals, "=");
        map.put(R.id.divide, "/");
        map.put(R.id.subtract, "-");
        map.put(R.id.add, "+");
        map.put(R.id.multiply, "*");
        map.put(R.id.percent, "%");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.number_0).setOnClickListener(btnClick);
        findViewById(R.id.number_1).setOnClickListener(btnClick);
        findViewById(R.id.number_2).setOnClickListener(btnClick);
        findViewById(R.id.number_3).setOnClickListener(btnClick);
        findViewById(R.id.number_4).setOnClickListener(btnClick);
        findViewById(R.id.number_5).setOnClickListener(btnClick);
        findViewById(R.id.number_6).setOnClickListener(btnClick);
        findViewById(R.id.number_7).setOnClickListener(btnClick);
        findViewById(R.id.number_8).setOnClickListener(btnClick);
        findViewById(R.id.number_9).setOnClickListener(btnClick);
        findViewById(R.id.ac).setOnClickListener(btnClick);
        findViewById(R.id.backspace).setOnClickListener(btnClick);
        findViewById(R.id.change).setOnClickListener(btnClick);
        findViewById(R.id.decimal).setOnClickListener(btnClick);
        findViewById(R.id.equals).setOnClickListener(btnClick);
        findViewById(R.id.divide).setOnClickListener(btnClick);
        findViewById(R.id.subtract).setOnClickListener(btnClick);
        findViewById(R.id.add).setOnClickListener(btnClick);
        findViewById(R.id.multiply).setOnClickListener(btnClick);
        findViewById(R.id.percent).setOnClickListener(btnClick);
    }

}