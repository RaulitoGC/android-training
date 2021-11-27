#include <jni.h>
#include <string>

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