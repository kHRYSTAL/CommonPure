package com.yimeiduo.business.net.exception;

import com.yimeiduo.business.entity.response.ErrorEntity;
import com.yimeiduo.business.entity.response.ExceptionEntity;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.text.ParseException;

import retrofit2.adapter.rxjava2.HttpException;


public class ExceptionHandle {

    public static final int UNAUTHORIZED = -1; // 401
//    public static final int TOKEN_TIME = -1; // 401
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ResponseException handleException(Throwable e) {
        //转换成ResponseException,根据状态码判定错误信息
        ResponseException ex;
//        e = e.getCause();
        if (e.getCause() instanceof HttpException) {
            HttpException httpException = (HttpException) e.getCause();
            /**
             * 传入状态码，根据状态码判定错误信息
             */
            ex = new ResponseException(e, ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                    ex.message = "未验证";//登录过期，请重新登录！  未验证
                    break;
                case FORBIDDEN:
                    ex.message = "服务禁止访问";
                    break;
                case NOT_FOUND:
                    ex.message = "服务不存在";
                    break;
                case REQUEST_TIMEOUT:
                    ex.message = "请求超时";
                    break;
                case GATEWAY_TIMEOUT:
                    ex.message = "网关超时";
                    break;
                case INTERNAL_SERVER_ERROR:
                    ex.message = "服务器内部错误";
                    break;
                case BAD_GATEWAY:

                    break;
                case SERVICE_UNAVAILABLE:
                    break;
                default:
                    ex.message = "网络错误";
                    break;
            }
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ResponseException(e, ERROR.PARSE_ERROR);
            ex.message = "解析错误";
            return ex;
        }else if (e.getCause() instanceof java.net.UnknownHostException) {
            ex = new ResponseException(e.getCause(), ERROR.NETWORD_ERROR);
            ex.message = "网络错误";
            return ex;
        }else if (e.getCause() instanceof ConnectException) {
            ex = new ResponseException(e.getCause(), ERROR.NETWORD_ERROR);
            ex.message = "连接失败";
            return ex;
        } else if (e.getCause() instanceof SocketTimeoutException) {
            ex = new ResponseException(e.getCause(), ERROR.TIMEOUT_ERROR);
            ex.message = "请求超时";
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ResponseException(e, ERROR.SSL_ERROR);
            ex.message = "证书验证失败";
            return ex;
        }
        /*else if (e.getCause() instanceof ErrorEntity) {
            ex = new ResponseException( e, Integer.valueOf(((ErrorEntity) e).getCode()));
            ex.message = ((ErrorEntity)e).getMessage();
            return ex;
        } */
        else if (e.getCause() instanceof ErrorEntity) {
            ex = new ResponseException(e.getCause(), Integer.valueOf(((ErrorEntity) e.getCause()).getCode()));
            ex.message = ((ErrorEntity) e.getCause()).getMsg();

            switch (Integer.valueOf(((ErrorEntity) e.getCause()).getCode())) {
                case UNAUTHORIZED:
//                case TOKEN_TIME:
                    ex.message = "登录过期，请重新登录！";//登录过期，请重新登录！
                    break;
            }
            return ex;
        }
//        com.google.gson.JsonSyntaxException: java.lang.NumberFormatException: Expected an int but was 0.08 at line 1 column 274 path $.data[0].setMealMoney
        else if (e.getCause() instanceof java.lang.NumberFormatException) {
            ex = new ResponseException(e, Integer.valueOf(((ExceptionEntity) e.getCause()).getCode()));
            ex.message = "数据格式转换错误";
            return ex;
        } else if (e.getCause() instanceof ExceptionEntity) {
            ex = new ResponseException(e, Integer.valueOf(((ExceptionEntity) e.getCause()).getCode()));
            ex.message = ((ExceptionEntity)e.getCause()).getMessage();
            return ex;
        } else {
            ex = new ResponseException(e, ERROR.UNKNOWN);//服务器异常
            ex.message = "未知异常";
            return ex;
        }
    }

    /**
     * 约定异常
     */
    public static class ERROR {
        /**
         * 自定义异常
         */
        private static final int UNAUTHORIZED = 401;//请求用户进行身份验证
        private static final int UNREQUEST = 403;//服务器理解请求客户端的请求，但是拒绝执行此请求
        private static final int UNFINDSOURCE = 404;//服务器无法根据客户端的请求找到资源
        private static final int SEVERERROR = 500;//服务器内部错误，无法完成请求。
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = 1003;
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        public static final int NETWORD_ERROR = 1002;
        /**
         * 证书出错
         */
        public static final int SSL_ERROR = 1005;

        /**
         * 请求超时
         */
        public static final int TIMEOUT_ERROR = 1006;
    }

    /**
     * 自定义Throwable
     */
    public static class ResponseThrowable extends Exception {
        public int code;
        public String message;

        public ResponseThrowable(Throwable throwable, int code) {
            super(throwable);
            this.code = code;
        }
    }

    /**
     * 服务器异常
     */
    public class ServerException extends RuntimeException {
        public int code;
        public String message;
    }

    /**
     * 统一异常类，便于处理
     */
    public static class ResponseException extends IOException {
        public int code;
        public String message;

        public ResponseException(Throwable throwable, int code) {
            super(throwable);
            this.code = code;
        }

    }

}