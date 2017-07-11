package com.czg.hook;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by czg on 2017/7/11.
 */

public class HookAmsUtils {
    private static final String TAG=HookAmsUtils.class.getSimpleName();
    private Class<?> mProxyActivityClass = ProxyActivity.class;
    private Context mContext;

    public HookAmsUtils(Class<?> proxyActivityClass,Context context) {
        mProxyActivityClass = proxyActivityClass;
        mContext = context;
    }

    public void forkAms() throws Exception{
        Log.e(TAG,"startFork");
        Class<?> activityClass = Class.forName("android.app.ActivityManagerNative");
        Field gDefaultField = activityClass.getDeclaredField("gDefault");
        gDefaultField.setAccessible(true);
        Object gdefaultField = gDefaultField.get(null);
        Class<?> singleTonClass = Class.forName("android.util.SingleTon");
        Field mInstanceField = singleTonClass.getDeclaredField("mInstance");
        mInstanceField.setAccessible(true);
        Object activityManagerService = mInstanceField.get(gdefaultField);
        Class<?> iActivityClass = Class.forName("android.app.IActivityManager");//重新创建一个实例
        mInstanceField.set(iActivityClass,activityManagerService);
        Proxy.newProxyInstance(singleTonClass.getClassLoader(), new Class[]{iActivityClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {



                return null;
            }
        });

    }
}
