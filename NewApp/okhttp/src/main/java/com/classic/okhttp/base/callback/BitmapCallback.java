package com.classic.okhttp.base.callback;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.IOException;
import okhttp3.Response;

/**
 * Created by zhy on 15/12/14.
 */
public abstract class BitmapCallback extends Callback {
    @Override public Bitmap onResult(Response response) throws IOException {
        return BitmapFactory.decodeStream(response.body().byteStream());
    }

    @Override public void onResponse(Object response) {
        this.onSuccess((Bitmap) response);
    }

    public abstract void onSuccess(Bitmap response);
}
