#include <jni.h>
#include <string>
#include <android/log.h>

const int PRIO = ANDROID_LOG_INFO;
const char* TAG = "wangweijun_c";

extern "C" JNIEXPORT jstring JNICALL
Java_com_tencent_mars_xlogsample_NdkSimple_getSingnaturePassword(JNIEnv *env, jclass clazz) {
    const char *password = "123456";
    return env->NewStringUTF(password);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_tencent_mars_xlogsample_NdkSimple_changeName(JNIEnv *env, jobject thiz) {
    // 获取对象的属性 (非常像写java反射的代码)
    jfieldID fid = env->GetFieldID(env->GetObjectClass(thiz), "name", "Ljava/lang/String;");
    jstring jstring1 = static_cast<jstring>(env->GetObjectField(thiz, fid));
    const char* name = env->GetStringUTFChars(jstring1, NULL);
    __android_log_print(PRIO, TAG, "原来的值 name= %s", name);

    jstring newName = env->NewStringUTF("duanxia");
    // 修改java对象的属性
    env->SetObjectField(thiz, fid, newName);
    __android_log_print(PRIO, TAG, "设置成功");
}

extern "C" JNIEXPORT void JNICALL
Java_com_tencent_mars_xlogsample_NdkSimple_changeAge(JNIEnv *env, jclass clazz) {
    // jclass clazz, jmethodID methodID
    jfieldID fid = env->GetStaticFieldID(clazz, "age", "I");
    env->SetStaticIntField(clazz, fid, 1000);
    __android_log_print(PRIO, TAG, "修改静态变量成功");

    // 同时调用java层产生随机数返回给C
    jmethodID mId = env->GetStaticMethodID(clazz, "getUUID", "()Ljava/lang/String;");
    jstring result = static_cast<jstring>(env->CallStaticObjectMethod(clazz, mId));
    const char* resultStr = env->GetStringUTFChars(result, NULL);
    __android_log_print(PRIO, TAG, "收到java层的随机数 %s",resultStr);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_tencent_mars_xlogsample_NdkSimple_callAddMethod(JNIEnv *env, jobject thiz) {
    // 查询所有属性的签名
// \app\build\intermediates\javac\debug\classes>javap -p -s com.tencent.mars.xlogsample.NdkSimple

    jmethodID mId = env->GetMethodID(env->GetObjectClass(thiz), "add", "(II)I");
    int result = env->CallIntMethod(thiz, mId, 100, 200);
    __android_log_print(PRIO, TAG, "调用java方法成功 re =%d ", result);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_tencent_mars_xlogsample_NdkSimple_callAddMethod2(JNIEnv *env, jobject thiz, jint num1,
                                                          jint num2) {
    jmethodID mId = env->GetMethodID(env->GetObjectClass(thiz), "add", "(II)I");
    int result = env->CallIntMethod(thiz, mId, num1, num2);
    __android_log_print(PRIO, TAG, "调用java方法传参2 re =%d ", result);
}

extern "C" JNIEXPORT jobject JNICALL
Java_com_tencent_mars_xlogsample_NdkSimple_createPoint(JNIEnv *env, jclass clazz) {
    // 返回值Jobject // (II)V
    jclass pointClazz = env->FindClass("com/tencent/mars/xlogsample/Point");
    // 注意调用构造函数的方法名是 init 哈,如此简单,就是java中的反射，所有函数都来自JNIEnv.xxxxxx
    jmethodID mid = env->GetMethodID(pointClazz, "<init>", "(II)V");
    return env->NewObject(pointClazz, mid, 11, 22);

}
