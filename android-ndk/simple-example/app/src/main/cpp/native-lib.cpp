#include <jni.h>
#include <string>
#include <android/log.h>

using namespace std;

extern "C" JNIEXPORT jstring JNICALL
Java_com_rguzmanc_nativecode_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}


extern "C"
JNIEXPORT jstring JNICALL
Java_com_rguzmanc_nativecode_MainActivity_getAnotherStringFromJNI(JNIEnv *env, jobject thiz) {
    string name = "Dash";
    return env->NewStringUTF(name.c_str());
}
extern "C"
JNIEXPORT void JNICALL
Java_com_rguzmanc_nativecode_MainActivity_00024Companion_printHelloDash(JNIEnv *env, jobject thiz,
                                                                        jstring name) {
    const char *str = env->GetStringUTFChars(name, JNI_FALSE);
    __android_log_write(ANDROID_LOG_INFO, "Tag", str);
    env->ReleaseStringChars(name, reinterpret_cast<const jchar *>(str));
}