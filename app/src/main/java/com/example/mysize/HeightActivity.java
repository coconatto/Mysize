package com.example.mysize;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class HeightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height);

        //スピナー処理
        Spinner setHeightSpinner = findViewById(R.id.heightParamSppinner);

        //スピナーのアイテム選択時の処理 無名クラス
        setHeightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            //アイテムを選択した時の処理
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView heightOfValue = findViewById(R.id.valueOfHeight);
                Spinner spinner = (Spinner)parent;
                String item = spinner.getSelectedItem().toString();
                if (!item.isEmpty()) {
                    heightOfValue.setText(item);
                }
            }

            //アイテムを何も選択しなかった時の処理
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //シークバー処理
        SharedPreferences pref = getSharedPreferences("MY_SIZE",MODE_PRIVATE);
        TextView heightOfValue = findViewById(R.id.valueOfHeight);
        String heightData = pref.getString("HEIGHT","160");
        heightOfValue.setText(heightData);

        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setProgress(Integer.parseInt(heightData));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            //値を変更したときの処理
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView heightOfValue = findViewById(R.id.valueOfHeight);
                heightOfValue.setText(progress);
            }

            @Override
            //シークバーに触れた時の処理
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            //シークバーを離した時の処理
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

    }

    @Override
    protected void onPause(){
        super.onPause();
        TextView heightOfValue = findViewById(R.id.valueOfHeight);
        SharedPreferences pref = getSharedPreferences("MY_SIZE",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("HEIGHT",Integer.parseInt(heightOfValue.getText().toString()));
        editor.apply();
    }
}
