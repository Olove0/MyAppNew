package com.classic.okhttp.base.callback;

import com.classic.okhttp.base.OkHttpUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.Response;

/**
 * Created by zhy on 15/12/15.
 */
public abstract class FileCallBack extends Callback {
    /**
     * 目标文件存储的文件夹路径
     */
    private String destFileDir;
    /**
     * 目标文件存储的文件名
     */
    private String destFileName;

    /**
     * @param destFileDir 目标文件存储的文件夹路径
     * @param destFileName 目标文件存储的文件名
     */
    public FileCallBack(String destFileDir, String destFileName) {
        this.destFileDir = destFileDir;
        this.destFileName = destFileName;
    }

    public abstract void onSuccess(File response);

    @Override public File onResult(Response response) throws IOException {
        return saveFile(response);
    }

    @Override public void onResponse(Object response) {
        this.onSuccess((File) response);
    }

    public File saveFile(Response response) throws IOException {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
            is = response.body().byteStream();
            final long total = response.body().contentLength();
            long sum = 0;

            File dir = new File(destFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, destFileName);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                sum += len;
                fos.write(buf, 0, len);
                final long finalSum = sum;
                OkHttpUtils.getInstance().getDelivery().post(new Runnable() {
                    @Override public void run() {
                        onProgress(total, finalSum);
                    }
                });
            }
            fos.flush();

            return file;
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
            }
        }
    }
}
