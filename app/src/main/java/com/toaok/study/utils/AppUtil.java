package com.toaok.study.utils;import android.Manifest;import android.content.Context;import android.content.Intent;import android.content.pm.PackageManager;import android.net.Uri;import android.provider.Settings;import android.support.v4.app.ActivityCompat;import android.support.v7.app.AlertDialog;/** * @author Toaok * @version 1.0  2018/9/26. */public class AppUtil {    public static void callPhone(Context context, String phoneNumber) {        Intent intent = new Intent(Intent.ACTION_CALL);        /*          Calling startActivity() from outside of an Activity  context requires the FLAG_ACT        * */        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );        Uri data = Uri.parse("tel:" + phoneNumber);        intent.setData(data);        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {            // TODO: Consider calling            //    ActivityCompat#requestPermissions            // here to request the missing permissions, and then overriding            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,            //                                          int[] grantResults)            // to handle the case where the user grants the permission. See the documentation            // for ActivityCompat#requestPermissions for more details.            return;        }        context.startActivity(intent);    }    /**     * 调用系统发短信界面     *     * @param activity    Activity     * @param phoneNumber 手机号码     * @param smsContent  短信内容     */    public static void sendMessage(Context activity, String phoneNumber, String smsContent) {        /*if (phoneNumber == null || phoneNumber.length() < 4) {            return;        }*/        Uri uri = Uri.parse("smsto:" + phoneNumber);        Intent it = new Intent(Intent.ACTION_SENDTO, uri);        it.putExtra("sms_body", smsContent);        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);        activity.startActivity(it);    }    /**     * 打开APP应用信息请求权限     * @param context   上下文     * @param features  功能     */    public static void openAppDetails(Context context,String features) {        AlertDialog.Builder builder = new AlertDialog.Builder(context);        builder.setMessage("该功能需要用到"+features+"功能，请到 “应用信息 -> 权限” 中授予！");        builder.setPositiveButton("去手动授权", (dialog, which) -> {            Intent intent = new Intent();            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);            intent.addCategory(Intent.CATEGORY_DEFAULT);            intent.setData(Uri.parse("package:" + context.getPackageName()));            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);            context.startActivity(intent);        });        builder.setNegativeButton("取消", null);        builder.show();    }}