package ark.clanner.juststudent.utils;

import okhttp3.*;

import java.io.IOException;

/**
 * Created by Clanner on 2018/5/3.
 */
public class OKHttpUtil {
    private static final OkHttpClient instance = new OkHttpClient();
    private static final Request.Builder builder = new Request.Builder();

    public static String get(String url) {
        try {
            return instance.newCall(builder.url(url).build()).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "请求失败，请稍后重试";
        }
    }

    public static String post(String url) {
        try {
            return instance.
                    newCall(builder.url(url)
                            .post(new FormBody.Builder().build()).build())
                    .execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "请求失败，请稍后重试";
        }
    }

    public static String post(String url, Param... params) {
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        for (Param p : params) {
            bodyBuilder.add(p.key, p.value);
        }
        Request request = builder.url(url)
                .post(bodyBuilder.build())
                .build();
        final String[] result = new String[1];
        instance.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result[0] = "请求失败，请稍后重试,错误信息是：" + e.getMessage();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body());
                result[0] = response.body().string();
            }
        });
        return result[0];
    }

    public static final class Param {
        public String key;
        public String value;

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
