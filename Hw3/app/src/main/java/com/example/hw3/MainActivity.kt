package com.example.hw3

import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        @Suppress("DEPRECATION") //將Toast.view啟用(應為版本改動所以有些方法不能用)
        //showtoast function
        fun showToast() {
            val toast = Toast(this@MainActivity); //宣告toast物件，this後面一定要@mainactivity，因為他進入toast後不知道this是指哪裡
            toast.setGravity(Gravity.TOP, 0, 50); //設定彈出位置
            toast.duration = Toast.LENGTH_SHORT; //toast的duration參數=length_short
            val inflater = layoutInflater;
            val layout: View = inflater.inflate(R.layout.custom_toast,findViewById(R.id.custom_toast_root)); //連結layout到自訂義xml
            toast.view=layout; //彈出視窗設為layout
            toast.show(); //show出來
        }
        //showlistdialog function
        fun showListDialog() {
            val list = arrayOf("message1", "message2", "message3", "message4", "message5"); //設定陣列，arrayof可以放各種型態
            val dialog_list = AlertDialog.Builder(this@MainActivity); //宣告alertdialog.builder方法
            dialog_list.setTitle("使用LIST呈現"); //設定標題
            //看選道地幾個就指向陣列中的第幾個，並利用toast彈出視窗
            dialog_list.setItems(list) { dialogInterface, i ->
                Toast.makeText(this@MainActivity, "你選的是" + list[i], Toast.LENGTH_SHORT).show() };
            dialog_list.show(); //show
        }
        val btn = findViewById<Button>(R.id.button1) //創建一個Button物件btn，綁定元件
        //將button1指定給let，下面的程式才會回傳直(最重要)
        button1?.let {
            btn.setOnClickListener {
                //因為在kotlin中所有東西預設都是final，所以不用在設
                val dialog =
                    AlertDialog.Builder(this@MainActivity); //呼叫AlertDialog中的Builder方法，並丟Main進去
                dialog.setTitle("請選擇功能") //呼叫settile方法(視窗的標題)
                dialog.setMessage("請根據下方按鈕選擇顯示的物件") //呼叫message方法(視窗的提示)
                //中立選項(如果選到就關閉alertdialog)，並用toast顯示出close
                dialog.setNeutralButton("cancel") { dialog: DialogInterface, i ->
                    Toast.makeText(this@MainActivity, "dialog close", Toast.LENGTH_SHORT).show(); };
                //正面選項(如果選到就呼叫showlistdialog)
                dialog.setPositiveButton("顯示list") { dialog: DialogInterface, i -> showListDialog(); };
                //負面選項(如果選到就呼叫showtoast)
                dialog.setNegativeButton("自訂義Toast") { dialog: DialogInterface, i -> showToast(); };
                dialog.show(); //顯示對話方塊(彈出視窗)
            }
        }
    }
}


