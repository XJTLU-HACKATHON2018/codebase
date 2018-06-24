package com.hstone.mkey;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
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
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;


public class MainActivity extends AppCompatActivity{
    private final String TAG = this.getClass().toString();

    private static final int REQUEST_CODE_IMAGE_CAMERA = 1;
    private static final int REQUEST_CODE_IMAGE_OP = 2;
    private static final int REQUEST_CODE_OP = 3;
    static public Uri mPath;
    static Context context;
    static EditText name,account,birthday,address,idcard,bankcard,school,phonenum,email;
    private EditText password;
    Button face_register,register,finger_register,second_register;
    int mYear = 0;
    int mMonth = 0;
    int mDay = 0;


    private FingerprintManagerCompat manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: 1111111111111");
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: 22222222222222");
            account = (EditText) findViewById(R.id.account);
            address = (EditText) findViewById(R.id.address);
            email = (EditText) findViewById(R.id.email);
            password = (EditText) findViewById(R.id.password);
            face_register = (Button) findViewById(R.id.btn_face);
            register = (Button) findViewById(R.id.register);
            school = (EditText) findViewById(R.id.school);
            idcard = (EditText) findViewById(R.id.idcard);
            name = (EditText) findViewById(R.id.name);
            phonenum = (EditText) findViewById(R.id.phone);
            bankcard = (EditText) findViewById(R.id.bankcard);
            birthday = (EditText) findViewById(R.id.birthday);
            finger_register = (Button) findViewById(R.id.btn_finger);
            manager = FingerprintManagerCompat.from(this);
            finger_register.setEnabled(false);

            this.context=new View(this).getContext();

            new CreatFile().writeTxtToFile("", "/sdcard/Android/data/com.hstone.mKey/cache/", "k");
            if(isFinger()){
                finger_register.setEnabled(true);
                finger_register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        manager.authenticate(null, 0, null, new MyCallBack(), null);
                    }
                });
             }



            birthday.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
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
                    ca.set(1960, 0, 1);

                    datePicker.setMinDate(ca.getTimeInMillis());
                    datePicker.setMaxDate(System.currentTimeMillis());
                    datePickerDialog.show();
                }

            });
            face_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("请选择注册方式")
                            //.setIcon(android.R.drawable.ic_dialog_info)
                            .setItems(new String[]{"打开图片", "拍摄照片"}, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case 1:
                                            //添加异常情况处理
                                            if (!AppUtils.isHaveCame(MediaStore.ACTION_IMAGE_CAPTURE)) {
                                                Toast.makeText(MainActivity.this, "该手机没有安装相机", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                            //原代码使用"android.media.action.IMAGE_CAPTURE"
                                            Intent getImageByCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                                            ContentValues values = new ContentValues(1);    //准备一个长度为1的键值对组
                                            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");    //指定内容类型
                                            //向存储在SD卡上的图像文件文件ContentProvider的URI插入一个 并返回Uri
                                            new MainActivity().mPath = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

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


        register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(account.getText()) || TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(bankcard.getText()) || TextUtils.isEmpty(phonenum.getText()) || TextUtils.isEmpty(idcard.getText()) || TextUtils.isEmpty(birthday.getText()) || TextUtils.isEmpty(school.getText()) || TextUtils.isEmpty(school.getText()) || TextUtils.isEmpty(password.getText())) {
                        Toast.makeText(MainActivity.this, "您输入的信息不完全请检查", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        String text = "姓名" + name.getText().toString() + "\n账号" + account.getText().toString() + "\n生日" + birthday.getText().toString() + "\n手机号" + phonenum.getText().toString() + "\n身份证号" + idcard.getText().toString() + "\n银行卡" + bankcard.getText().toString() + "\n学校" + school.getText().toString() + "\n所在地" + address.getText().toString() + "\n邮箱" + address.getText().toString() + "\n密码" + password.getText().toString();
                        new CreatFile().writeTxtToFile(text, "/sdcard/Android/data/com.hstone.mKey/cache/", account.getText().toString());
                        try {
                            new Client().connect(text);
                            Log.d(TAG, "onClick: 连接");
                        }catch (Exception e){
                            e.printStackTrace();
                            Log.d(TAG, "错误");

                        }

                    }

                }
            });

            face_register = (Button) findViewById(R.id.btn_face);
            face_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("请选择注册方式")
                            //  .setIcon(android.R.drawable.ic_dialog_info)
                            .setItems(new String[]{"打开图片", "拍摄照片"}, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case 1:
                                            //添加异常情况处理
                                            if (!AppUtils.isHaveCame(MediaStore.ACTION_IMAGE_CAPTURE)) {
                                                Toast.makeText(MainActivity.this, "该手机没有安装相机", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                            Intent getImageByCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                            ContentValues values = new ContentValues(1);
                                            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                                            mPath = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                                            getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, mPath);
                                            startActivityForResult(getImageByCamera, REQUEST_CODE_IMAGE_CAMERA);
                                            break;
                                        case 0:
                                            Intent getImageByAlbum = new Intent(Intent.ACTION_GET_CONTENT);
                                            getImageByAlbum.addCategory(Intent.CATEGORY_OPENABLE);
                                            getImageByAlbum.setType("image/jpeg");
                                            startActivityForResult(getImageByAlbum, REQUEST_CODE_IMAGE_OP);
                                            break;
                                    }
                                }
                            })
                            .show();
                }
            });
           /* new Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //获取Application里非静态变量
                    if (((Application) getApplicationContext()).mFaceDB.mRegister.isEmpty()) {
                        Toast.makeText(MainActivity.this, "没有注册人脸，请先注册！", Toast.LENGTH_SHORT).show();
                    } else {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("请选择相机")
                                .setIcon(android.R.drawable.ic_dialog_info)
                                .setItems(new String[]{"后置相机", "前置相机"}, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startDetector(which);
                                    }
                                }).show();
                    }
                }
            });*/
        }

    public boolean isFinger() {
        if (!manager.isHardwareDetected()) {
            Toast.makeText(this, "没有指纹识别模块", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!manager.hasEnrolledFingerprints()) {
            Toast.makeText(this, "没有录入指纹", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_IMAGE_OP && resultCode == RESULT_OK) {
            mPath = data.getData();
            String file = getPath(mPath);
            Bitmap bmp = Application.decodeImage(file);
            if (bmp == null || bmp.getWidth() <= 0 || bmp.getHeight() <= 0) {
                Log.e(TAG, "error");
            } else {
                Log.i(TAG, "bmp [" + bmp.getWidth() + "," + bmp.getHeight());
            }
            startRegister(bmp, file);
        } else if (requestCode == REQUEST_CODE_OP) {
            Log.i(TAG, "RESULT =" + resultCode);
            if (data == null) {
                return;
            }
            Bundle bundle = data.getExtras();   //将捆绑的参数取出
            String path = bundle.getString("imagePath");
            Log.i(TAG, "path=" + path);
          } else if (requestCode == REQUEST_CODE_IMAGE_CAMERA && resultCode == RESULT_OK) {
            //当头型不正时便无法获取到人脸特征
            String file = getPath(mPath);
            Bitmap bmp = Application.decodeImage(file);
            startRegister(bmp, file);
        }
    }




    /**
     * @param uri
     * @return
     */
    private String getPath(Uri uri) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (DocumentsContract.isDocumentUri(this, uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    return null;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    return null;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(this, contentUri, selection, selectionArgs);
            }
        }
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualImageCursor = managedQuery(uri, proj, null, null, null);
        int actual_image_column_index = actualImageCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        actualImageCursor.moveToFirst();
        String img_path = actualImageCursor.getString(actual_image_column_index);
        String end = img_path.substring(img_path.length() - 4);
        if (0 != end.compareToIgnoreCase(".jpg") && 0 != end.compareToIgnoreCase(".png")) {
            return null;
        }
        return img_path;
    }


    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param mBitmap
     */
    private void startRegister(Bitmap mBitmap, String file) {
        Intent it = new Intent(MainActivity.this, RegisterActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("imagePath", file);
        it.putExtras(bundle);   //使用Bundle更利于多重传递 快速取出参数且可以传递对象 http://blog.csdn.net/garretly/article/details/6207950
        startActivityForResult(it, REQUEST_CODE_OP);
    }

    private void startDetector(int camera) {
        Intent it = new Intent(MainActivity.this, DetecterActivity.class);
        it.putExtra("Camera", camera);
        startActivityForResult(it, REQUEST_CODE_OP);

    }

    }
 class MyCallBack extends FingerprintManagerCompat.AuthenticationCallback {
    private static final String TAG = "MyCallBack";

    // 当出现错误的时候回调此函数，比如多次尝试都失败了的时候，errString是错误信息
    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        Log.d(TAG, "onAuthenticationError: " + errString);
    }

    // 当指纹验证失败的时候会回调此函数，失败之后允许多次尝试，失败次数过多会停止响应一段时间然后再停止sensor的工作
    @Override
    public void onAuthenticationFailed() {
        //Toast.makeText(FingerprintActivity.this,"验证失败",Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onAuthenticationFailed: " + "验证失败");
        Toast.makeText(MainActivity.context,"验证指纹失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        Log.d(TAG, "onAuthenticationHelp: " + helpString);
    }

    // 当验证的指纹成功时会回调此函数，然后不再监听指纹sensor
    @Override
    public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult
                                                  result) {
        Toast.makeText(MainActivity.context,"验证指纹成功",Toast.LENGTH_SHORT).show();
        new CreatFile().writeTxtToFile("have fingerprint", "/sdcard/Android/data/com.hstone.mKey/cache/", "have fingerprint");
        Log.d(TAG, "onAuthenticationSucceeded: " + "验证成功");

    }
}

