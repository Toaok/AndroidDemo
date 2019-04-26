package indi.toaok.androiddemo.utils;import android.Manifest;import android.app.Application;import android.content.Context;import android.content.Intent;import android.content.pm.PackageManager;import android.content.pm.ResolveInfo;import android.net.Uri;import android.os.Build;import androidx.core.app.ActivityCompat;import indi.toaok.utils.Utils;import java.io.File;import java.util.List;/** * @author Toaok * @version 1.0  2018/9/26. */public class AppUtil {    public static Application getApplication() {        return Utils.getApp();    }    /**     * 调用系统拨打电话     * 需要权限 {@link Manifest.permission#CALL_PHONE }     *     * @param context     Context     * @param phoneNumber 手机号码     */    public static void callPhone(Context context, String phoneNumber) {        Intent intent = new Intent(Intent.ACTION_CALL);        /*          Calling startActivity() from outside of an Activity  context requires the FLAG_ACT        * */        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);        Uri data = Uri.parse("tel:" + phoneNumber);        intent.setData(data);        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {            return;        }        context.startActivity(intent);    }    /**     * 调用系统发短信界面     * 不需要权限     *     * @param context    Context     * @param phoneNumber 手机号码     * @param smsContent  短信内容     */    public static void sendMessage(Context context, String phoneNumber, String smsContent) {        /*if (phoneNumber == null || phoneNumber.length() < 4) {            return;        }*/        Uri uri = Uri.parse("smsto:" + phoneNumber);        Intent it = new Intent(Intent.ACTION_SENDTO, uri);        it.putExtra("sms_body", smsContent);        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);        context.startActivity(it);    }    /**     * 获取系统版本号     *     * @return int     */    public static int getAndroidSDKVersion() {        int version = 0;        try {            version = Integer.valueOf(android.os.Build.VERSION.SDK_INT);        } catch (NumberFormatException e) {        }        return version;    }    /**     * 安装APK     *     * @param context Context     * @param file File     */    public static void installApk(Context context, File file) {        try {            Intent intent = new Intent(Intent.ACTION_VIEW);            Uri apkUri = null;            //判断版本是否是 7.0 及 7.0 以上            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {                apkUri = FileProviderUtils.uriFromFile(context, file);                //添加对目标应用临时授权该Uri所代表的文件                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);            } else {                apkUri = Uri.fromFile(file);            }            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");            //查询所有符合 intent 跳转目标应用类型的应用，注意此方法必须放置setDataAndType的方法之后            List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);            //然后全部授权            for (ResolveInfo resolveInfo : resInfoList) {                String packageName = resolveInfo.activityInfo.packageName;                context.grantUriPermission(packageName, apkUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);            }            context.startActivity(intent);        } catch (Exception e) {            e.printStackTrace();        }    }}