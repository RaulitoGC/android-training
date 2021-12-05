#include <jni.h>

#include <string>
#include "calculate.h"


extern "C" JNIEXPORT jstring JNICALL
Java_com_taf_calculator_MainActivity_setExpcalretToTextView(
        JNIEnv *env,
        jobject, jstring expression){

    std::string ret = "";

    try {
        std::string exp = env->GetStringUTFChars(expression, JNI_FALSE);
        ret = std::to_string(calculate_expression(exp));
    } catch (const std::invalid_argument &e) {
        ret = e.what();
    } catch (const std::exception & e) {
        using namespace std::string_literals;
        ret = "internal error: "s + e.what() + " exception type: " + typeid(e).name();
    } catch (...) {
        ret = "internal error";
    }

    return env->NewStringUTF(ret.c_str());
}


