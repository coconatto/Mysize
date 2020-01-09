package com.example.mysize;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SharedPreference.getSharedPreference("設定したい共有プリファレンス名",他アプリからのデータ閲覧権限設定)
        //設定によって別アプリからのRW権限を設定することができる。
        SharedPreferences pref = getSharedPreferences("MY_SIZE", MODE_PRIVATE);

        //共有プリファレンスからデータ取得
        String editNeck = pref.getString("NECK", "");
        String editSleeve = pref.getString("SLEEVE", "");
        String editWaist = pref.getString("WAIST", "");
        String editInseam = pref.getString("INSEAM", "");

        //各ビューのIDを取得したインスタンス生成
        EditText neckData = (EditText) findViewById(R.id.neck);
        EditText sleeveData = (EditText) findViewById(R.id.sleeve);
        EditText waistData = (EditText) findViewById(R.id.waist);
        EditText inseamData = (EditText) findViewById(R.id.inseam);

        //各ビューの値に共有プリファレンスから取得したパラメータを代入
        neckData.setText(editNeck);
        sleeveData.setText(editSleeve);
        waistData.setText(editWaist);
        inseamData.setText(editInseam);

        //セーブボタンクリック時の挙動 無名クラス内のthisはネストクラスのインスタンス自信を指す為、
        //thisのみだとエラーとなる。無名クラスから見たアウタークラスである、MainActivity.thisと記載する。
        ImageButton heightButton = (ImageButton) findViewById(R.id.heightButton);
        heightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //別のインスタンスへの遷移処理
                Intent intent = new Intent(MainActivity.this,HeightActivity.class);
                startActivity(intent);
            }
        });
    }
}
