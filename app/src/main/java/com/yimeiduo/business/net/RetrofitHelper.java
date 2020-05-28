package com.yimeiduo.business.net;

import com.yimeiduo.business.Constant;
import com.yimeiduo.business.entity.response.LoginEntity;
import com.yimeiduo.business.net.converter.XGsonConverterFactory;
import com.yimeiduo.business.util.MyLog;
import com.yimeiduo.business.util.SpUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * RetrofitHelper用于Retrofit的初始化
 */
public class RetrofitHelper {
    private static final int CONNECT_TIMEOUT = 10;
    private static final int READ_TIMEOUT = 20;
//    public static final String WEBSERVICE_URL = "http://server.ricelink.cn:18003";//me
//    private static final String WEBSERVICE_URL = "http://192.168.0.126:8003";//曾冲
//    public static final String WEBSERVICE_URL = "http://192.168.0.229:8003";//刚松
//    public static final String WEBSERVICE_URL = "http://192.168.0.248:8003";//志文
    //            public static final String WEBSERVICE_URL = "http://192.168.0.117:8003";//佳鸿
//    public static final String WEBSERVICE_URL = "http://192.168.0.171:8003";//文龙
//    public static final String WEBSERVICE_URL = "http://119.23.35.90:8003";//测试环境
    private static final int WRITE_TIMEOUT = 10;
    private static RetrofitHelper manager;
    private OkHttpClient mClient;
    private Retrofit mRetrofit;
    private RetrofitService retrofitService;

    private RetrofitHelper( ) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = null;

                /*if (chain.request().url().toString().contains("/user/login")) {
                    request = chain.request()
                            .newBuilder()
                            .addHeader("Authorization", Contract.header)
                            .build();
                } else {
                    request = chain.request()
                            .newBuilder()
                            .addHeader("Authorization", "youtu  " + access_token())
                            .build();

                }*/

                request = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", "youtu  " + access_token())
                        .build();


/*//                        if (chain.proceed(request).code()== 401){
//                            AppManager.getAppManager().finishOthersActivity(LoginStrongActivity.class);
//                            context.startActivity(new Intent(context,LoginStrongActivity.class));
//                        }*/

                return chain.proceed(request);
            }

        }).build();
        builder.retryOnConnectionFailure(true);
        mClient = builder.build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE)
                .addConverterFactory(XGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mClient)
                .build();
        retrofitService = mRetrofit.create(RetrofitService.class);
    }

    public static RetrofitHelper getInstance() {
        if (manager == null) {
            synchronized (Object.class) {
                if (manager == null) {
                    manager = new RetrofitHelper();
                }
            }
        }
        return manager;
    }


    public RetrofitService getRetrofitService() {
        return retrofitService;
    }

    public String access_token() {
        LoginEntity loginEntity = SpUtil.getObjFromSP(Constant.SP_LOGINENTITY);
        if(loginEntity!=null){
            MyLog.i("RetrofitHelper","getToken--"+loginEntity.getToken());
            return loginEntity.getToken();
        }
        return "";
    }

    /**
     * 根据Response，判断Token是否失效
     * 401表示token过期
     *
     * @param response
     * @return
     */
    private boolean isTokenExpired(Response response) {
        if (response.code() == 401) {
            return true;
        }
        return false;
    }

}
