package com.example.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class MyLooper extends Thread {

    public Handler mHandler;
    private Handler mainHandler;

    public MyLooper(Handler mainThreadHandler) {
        this.mainHandler = mainThreadHandler;
    }

    @Override
    public void run() {
        Log.d("MyLooper", "run");

        Looper.prepare();

        mHandler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {

                int age = msg.getData().getInt("AGE");
                String job = msg.getData().getString("JOB");

                Log.d("MyLooper", "получено сообщение: возраст = " + age + ", работа = " + job);

                try {
                    sleep(age * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Message message = Message.obtain();
                Bundle bundle = new Bundle();

                bundle.putString(
                        "result",
                        "мне " + age + " лет, я работаю: " + job +
                                ". задержка составила " + age + " секунд"
                );

                message.setData(bundle);

                mainHandler.sendMessage(message);
            }
        };

        Looper.loop();
    }
}