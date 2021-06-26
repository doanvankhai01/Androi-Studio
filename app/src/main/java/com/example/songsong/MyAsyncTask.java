package com.example.songsong;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyAsyncTask extends AsyncTask<Void,Integer,Void> {
    private Activity mContext;

    public MyAsyncTask(Activity contextParent){
        this.mContext = contextParent;
    }
    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        // hàm này sẽ chạy đầu tiên khi AsyncTask này đưuọc gọi
        // Ở đây sẽ thông báo quá trình load bắt đầu "Start"
        Toast.makeText(mContext,"Start",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        // HÀm được hiện tiếp sau hàm onPreExecute()
        // Hàm này được thực hiện tác vụ chạy ngầm
        // tuyệt đối không vẽ giao diện trong hàm này
        for (int i = 0; i<= 100; i++){
            SystemClock.sleep(100);
            // khi hàm này thì onProgressUpdate sẽ thực thi
            publishProgress(i);
        }
        return null;
    }

    protected boolean hoanHao(int n){
        int sum = 0, i;
        for(i=1; i <= n/2; i++){
            if (n%i == 0){
                sum +=i;
            }
        }
        return (sum==n);
    }
    @Override
    protected void onProgressUpdate(Integer... values){
        // hàm thực hiện update giao diện khi có dữ liệu từ hàm doInBackground gửi xuống
        super.onProgressUpdate(values);
        // thông qua context để lấy được control trong MainActivity
        ProgressBar progressBar = (ProgressBar) mContext.findViewById(R.id.prbDemo);
        // Vì publishProgress chỉ truyền 1 số đối
        // nên mảng values chỉ có 1 phần tử
        int number = values[0];
        //tăng giá trị của Progressbar lên
        progressBar.setProgress(number);
        // đồng thời hiển thị giá trị là % lên TextView
        TextView textView = (TextView) mContext.findViewById(R.id.txtStatus);
        textView.setText(number + "%");
    }
    @Override
    protected void onPostExecute (Void aVoid){
        super.onPostExecute(aVoid);
        //hàm đuợc thực hiện khi tiến trình kết thúc
        //Ở đây mình thông báo là đã "Finshed" để người dùng biết
        Toast.makeText(mContext,"Okie, Finished",Toast.LENGTH_SHORT).show();
    }
}
