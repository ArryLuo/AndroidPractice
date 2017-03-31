package com.lyl.download;

import android.app.DownloadManager;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class DownloadActivity extends AppCompatActivity implements View.OnClickListener {

    private String mDownloadUrl = "http://www.tingge123.com/mp3/2016-06-14/1465876835.mp3";
    private String mTitle = "多幸运";
    private String mFileName = "多幸运.mp3";
    private String mDescription = "是有多幸运，才会遇到你。";
    private String mPath = Environment.DIRECTORY_DOWNLOADS;
    private String mAudioPath;

    private Button mBtnDown;
    private TextView mTxtProgress;
    private TextView mTxtFileName;
    private ProgressBar mPbUpdate;
    private DownloadManager mDownloadManager;
    private DownloadManager.Request mRequest;

    private Timer mTimer;
    private TimerTask mTask;
    private long mId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        mBtnDown = (Button) findViewById(R.id.down);
        mTxtProgress = (TextView) findViewById(R.id.txt_progress);
        mTxtFileName = (TextView) findViewById(R.id.txt_file_name);
        mPbUpdate = (ProgressBar) findViewById(R.id.pb_update);
        mBtnDown.setOnClickListener(this);

        // 初始化下载
        initDownload();
    }


    private void initDownload() {
        // 拿到系统下载
        mDownloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        // 设置请求地址
        mRequest = new DownloadManager.Request(Uri.parse(mDownloadUrl));

        // 设置通知栏
        mRequest.setTitle(mTitle);
        mRequest.setDescription(mDescription);
        mRequest.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        mRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        mRequest.setMimeType("audio/mp3");
        mRequest.setAllowedOverRoaming(false);

        // 设置下载的位置
        Environment.getExternalStoragePublicDirectory(mPath).mkdir();
        mRequest.setDestinationInExternalPublicDir(mPath, mFileName);

        // 设置下载的回调
        final DownloadManager.Query query = new DownloadManager.Query();
        mTimer = new Timer();
        mTask = new TimerTask() {
            @Override
            public void run() {
                Cursor cursor = mDownloadManager.query(query.setFilterById(mId));
                if (cursor != null && cursor.moveToFirst()) {
                    if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                        mPbUpdate.setProgress(100);
                        if (!TextUtils.isEmpty(mAudioPath)) {
                            playAudio(mAudioPath);
                        }
                        mTask.cancel();
                    }
                    String title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE));
                    mAudioPath = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    int bytes_downloaded = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                    int bytes_total = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));

                    int pro = (bytes_downloaded * 100) / bytes_total;
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putInt("pro", pro);
                    bundle.putString("name", title);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            }
        };
        mTimer.schedule(mTask, 0, 1000);
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            int pro = bundle.getInt("pro");
            String name = bundle.getString("name");
            mPbUpdate.setProgress(pro);
            mTxtProgress.setText(String.valueOf(pro) + "%");
            mTxtFileName.setText(name);
        }
    };


    @Override
    public void onClick(View view) {
        mId = mDownloadManager.enqueue(mRequest);
        mTask.run();
        mBtnDown.setClickable(false);
    }


    /**
     * 播放指定名称的歌曲
     */
    public void playAudio(String audioPath) {
        Intent mIntent = new Intent();
        mIntent.setAction(android.content.Intent.ACTION_VIEW);
        Uri uri = Uri.parse(audioPath);
        mIntent.setDataAndType(uri, "audio/mp3");
        startActivity(mIntent);
    }
}
