package com.example.secondar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import android.view.PixelCopy;
import android.view.View;

@TargetApi(24)

public class ScreenShot {

    public interface PostTake {

        void onSuccess(Bitmap bitmap);
        void onFailure(int error);
    }

    public ScreenShot() {

    }

    @TargetApi(26)
    public void take(View view, Activity activity, PostTake callback) {

        if (callback == null) {

            throw new IllegalArgumentException("Screenshot request without a callback");
        }

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        int[] location = new int[2];
        view.getLocationInWindow(location);

        Rect rect = new Rect(location[0], location[1], location[0] + view.getWidth(), location[1] + view.getHeight());
        PixelCopy.OnPixelCopyFinishedListener listener = copyResult -> {

            if (copyResult == PixelCopy.SUCCESS) {

                callback.onSuccess(bitmap);
            } else {

                callback.onFailure(copyResult);
            }
        };

        try {

            PixelCopy.request(activity.getWindow(), rect, bitmap, listener, new Handler());
        } catch (IllegalArgumentException e) {

            e.printStackTrace();
        }
    }
}