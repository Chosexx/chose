package com.bawei.chosexx.chen;

import android.annotation.TargetApi;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.chosexx.R;
import com.bawei.chosexx.base.BaseActivity;
import com.bawei.chosexx.chen.say.AudioRecoderDialog;
import com.bawei.chosexx.chen.say.AudioRecoderUtils;
import com.bawei.chosexx.chen.utils.DataCleanManager;

import java.io.File;
import java.io.FileReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MySetting extends BaseActivity {
    //implements View.OnTouchListener, AudioRecoderUtils.OnAudioStatusUpdateListener

    @BindView(R.id.myset)
    RelativeLayout myset;
    @BindView(R.id.jiluone)
    RelativeLayout jiluone;
    @BindView(R.id.friend)
    RelativeLayout friend;
    @BindView(R.id.clear)
    RelativeLayout clear;
    @BindView(R.id.tv_clear)
    TextView tv_clear;
    @BindView(R.id.about_as)
    RelativeLayout aboutAs;
    @BindView(R.id.suggestions)
    RelativeLayout suggestions;
    @BindView(R.id.my_response)
    RelativeLayout myResponse;

   /* @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.et_response)
    EditText et_response;
    @BindView(R.id.btn_luyin)
    Button btn_luyin;

    private AudioRecoderDialog recoderDialog;
    private AudioRecoderUtils recoderUtils;
    private long downT;*/


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mysetting);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.friend)
    void FriendOnClick() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置弹窗标题
        builder.setTitle("发现一个看片神器");
        //设置弹窗消息
        builder.setMessage("https://github.com/xiaoshitou13/ThreePlayer.git");
        //消极
        builder.setNegativeButton("复制", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                ClipboardManager clip = (ClipboardManager) MySetting.this.getSystemService(Context.CLIPBOARD_SERVICE);
                clip.setText("https://github.com/xiaoshitou13/ThreePlayer.git");
                Toast.makeText(MySetting.this, "已复制到粘贴板", Toast.LENGTH_SHORT).show();
            }
        });
        //积极
        builder.setPositiveButton("关闭", null);
        builder.show();
    }

    @OnClick(R.id.clear)
    void clearOnClick() {

        File filesDir = MySetting.this.getFilesDir();
        try {
            String cacheSize = DataCleanManager.getCacheSize(filesDir);
            tv_clear.setText("缓存大小：" + cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        DataCleanManager.cleanApplicationData(MySetting.this, filesDir.getAbsolutePath());
        Toast.makeText(MySetting.this, "缓存已清理", Toast.LENGTH_SHORT).show();
    }





   /* @OnClick(R.id.my_response)
    void responseOnClick() {
        //判断SDK版本是否大于等于19，大于就让他显示，小于就要隐藏，不然低版本会多出来一个
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            //还有设置View的高度，因为每个型号的手机状态栏高度都不相同
        }

        final AlertDialog.Builder builder3 = new AlertDialog.Builder(this);

        View s = LayoutInflater.from(MySetting.this).inflate(R.layout.fankui, null);

        builder3.setView(s);

        final AlertDialog alertDialog = builder3.show();


        Button gb = s.findViewById(R.id.fkqx);
        Button ss = s.findViewById(R.id.fkss);


        btn_luyin.setOnTouchListener(this);
        gb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        recoderDialog = new AudioRecoderDialog(this);
        recoderDialog.setShowAlpha(0.98f);
        recoderUtils = new AudioRecoderUtils(new File(Environment.getExternalStorageDirectory() + "/recoder.amr"));
        recoderUtils.setOnAudioStatusUpdateListener(this);


        ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!et_email.getText().toString().equals("") && !et_response.getText().toString().equals("")) {
                    Toast.makeText(MySetting.this, "发送成功", Toast.LENGTH_SHORT).show();
                    et_email.setText("");
                    et_response.setText("");
                } else {
                    Toast.makeText(MySetting.this, "请输入邮箱或者信息", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                recoderUtils.startRecord();
                downT = System.currentTimeMillis();
                recoderDialog.showAtLocation(view, Gravity.CENTER, 0, 0);
                btn_luyin.setBackgroundResource(R.drawable.shape_recoder_btn_recoding);
                return true;
            case MotionEvent.ACTION_UP:
                recoderUtils.stopRecord();
                recoderDialog.dismiss();
                btn_luyin.setBackgroundResource(R.drawable.shape_recoder_btn_normal);
                File f = new File(Environment.getExternalStorageDirectory() + "/recoder.mp3");

                try {
                    FileReader reader = new FileReader(f);// 获取该文件的输入流
                    char[] bb = new char[1024];// 用来保存每次读取到的字符
                    String str = "";// 用来将每次读取到的字符拼接，当然使用StringBuffer类更好
                    int n;// 每次读取到的字符长度
                    while ((n = reader.read(bb)) != -1) {
                        str += new String(bb, 0, n);
                    }
                    reader.close();// 关闭输入流，释放连接
                    et_response.setText(str);


                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
        }
        return false;
    }

    @Override
    public void onUpdate(double db) {
        if (null != recoderDialog) {
            int level = (int) db;
            recoderDialog.setLevel((int) db);
            recoderDialog.setTime(System.currentTimeMillis() - downT);
        }
    }*/
}
