package com.hstone.mkey;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;

import static android.content.ContentValues.TAG;


public class Register extends AppCompatActivity {


    private static final int REQUEST_CODE_IMAGE_CAMERA = 1;
    private static final int REQUEST_CODE_IMAGE_OP = 2;
    private static final int REQUEST_CODE_OP = 3;

        EditText name,account,birthday,address,idcard,bankcard,school,phonenum,email;
        private EditText password;
        Button face_register,second_register;
        int mYear = 0;
        int mMonth = 0;
        int mDay = 0;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.d(TAG, "onCreate: 1111111111111111111111111");
            setContentView(R.layout.activity_registerall);
            Log.d(TAG, "onCreate: 22222222222222222222222");
           // button_register.setEnabled(false);
            account =(EditText) findViewById(R.id.account);
            address = (EditText) findViewById(R.id.address);
            email = (EditText) findViewById(R.id.email);
            password = (EditText) findViewById(R.id.password);
            face_register = (Button) findViewById(R.id.btn_face);
            second_register = (Button) findViewById(R.id.register);
            school =(EditText) findViewById(R.id.school);
            idcard =(EditText) findViewById(R.id.idcard);
            name =(EditText) findViewById(R.id.name);
            phonenum = (EditText) findViewById(R.id.phone);
            bankcard =(EditText) findViewById(R.id.bankcard);
            birthday = (EditText) findViewById(R.id.birthday);
            birthday.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(Register.this,
                           0,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    mYear = year;
                                    mMonth = month;
                                    mDay = dayOfMonth;
                                    birthday.setText(mYear + "-" + (mMonth + 1) + "-" + mDay);
                                }
                            }, mYear, mMonth, mDay);
                    DatePicker datePicker = datePickerDialog.getDatePicker();
                    Calendar ca = Calendar.getInstance();
                    ca.set(1960,0,1);

                    datePicker.setMinDate(ca.getTimeInMillis());
                    datePicker.setMaxDate(System.currentTimeMillis());
                    datePickerDialog.show();
                }

            });
            face_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(Register.this)
                            .setTitle("请选择注册方式")
                            //.setIcon(android.R.drawable.ic_dialog_info)
                            .setItems(new String[]{"打开图片", "拍摄照片"}, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case 1:
                                            //添加异常情况处理
                                            if (!AppUtils.isHaveCame(MediaStore.ACTION_IMAGE_CAPTURE)) {
                                                Toast.makeText(Register.this, "该手机没有安装相机", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                            //原代码使用"android.media.action.IMAGE_CAPTURE"
                                            Intent getImageByCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                                            ContentValues values = new ContentValues(1);    //准备一个长度为1的键值对组
                                            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");    //指定内容类型
                                            //向存储在SD卡上的图像文件文件ContentProvider的URI插入一个 并返回Uri
                                            new MainActivity().mPath  = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                                            //指定拍照存储路径 如果指定了目标uri，data就没有数据，如果没有指定uri，则data就返回有数据
                                            getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, new MainActivity().mPath);

                                            startActivityForResult(getImageByCamera, REQUEST_CODE_IMAGE_CAMERA);
                                            //new MainActivity().onActivityResult(1,-1,new Intent());
                                            break;
                                        case 0:
                                            //隐式意图 选择最匹配的
                                            Intent getImageByAlbum = new Intent(Intent.ACTION_GET_CONTENT);
                                            //只返回可通过 openFileDescriptor() 以文件流形式表示的“可打开”文件
                                            getImageByAlbum.addCategory(Intent.CATEGORY_OPENABLE);
                                            //查看类型 String IMAGE_UNSPECIFIED = "image/*";
                                            getImageByAlbum.setType("image/jpeg");
                                            startActivityForResult(getImageByAlbum, REQUEST_CODE_IMAGE_OP);

                                            break;
                                    }
                                }
                            })
                            .show();
                         }
                    });


            second_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(account.getText())||TextUtils.isEmpty(name.getText())||TextUtils.isEmpty(email.getText())||TextUtils.isEmpty(bankcard.getText())||TextUtils.isEmpty(phonenum.getText())||TextUtils.isEmpty(idcard.getText())||TextUtils.isEmpty(birthday.getText())||TextUtils.isEmpty(school.getText())||TextUtils.isEmpty(school.getText())||TextUtils.isEmpty(password.getText())){
                        Toast.makeText(Register.this,"您输入的信息不完全请检查",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Register.this,"注册成功",Toast.LENGTH_SHORT).show();
                        String text = "姓名\n"+name.getText().toString()+"\n账号"+account.getText().toString()+"\n生日"+birthday.getText().toString()+"\n手机号"+phonenum.getText().toString()+"\n身份证号"+idcard.getText().toString()+"\n银行卡"+bankcard.getText().toString()+"\n学校"+school.getText().toString()+"\n所在地"+address.getText().toString()+"\n邮箱"+address.getText().toString()+"\n密码"+password.getText().toString();
                        new CreatFile().writeTxtToFile(text,"/sdcard/Android/data/com.hstone.mKey/cache/",account.getText().toString());
                    }

                }
            });



        }

    }

