package com.sunilsharma.forecastmvvm.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class DataConverter
{
    var gson = Gson()

//    @TypeConverter
//    fun stringToSomeObjectList(data: String?): List<String?>? {
//        if (data == null) {
//            return Collections.emptyList()
//        }
//        val listType = object :
//            TypeToken<List<String?>?>() {}.type
//        return gson.fromJson<List<String?>>(data, listType)
//    }
//
//    @TypeConverter
//    fun someObjectListToString(someObjects: List<String?>?): String? {
//        return gson.toJson(someObjects)
//    }

    @TypeConverter
    fun stringAsStringList(strings: String?): List<String> {
        val list = mutableListOf<String>()
        strings
            ?.split(",")
            ?.forEach {
                list.add(it)
            }

        return list
    }

    @TypeConverter
    fun stringListAsString(strings: List<String>?): String {
        var result = ""
        strings?.forEach { element ->
            result += "$element,"
        }
        return result.removeSuffix(",")
    }
}