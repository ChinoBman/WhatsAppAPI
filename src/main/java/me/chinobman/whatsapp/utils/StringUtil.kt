package me.chinobman.whatsapp.utils

import com.google.gson.Gson

class StringUtil {

    companion object {
        fun <T> toJson(arg : String, clazz : Class<T>?) : T? {
            if(!arg.equals(null)) {
                var json = arg.split("Conn\",")[1].replace("}]", "}", false)
                return Gson().fromJson(json, clazz)
            } else return null
        }
    }

}