package com.confuse.dailypang

import android.util.Log
import java.util.Calendar



fun firstDayWeek(year : Int , month : Int) : Int{
    val instance = Calendar.getInstance()
    instance.set(year,month,1)
    return instance.get(Calendar.DAY_OF_WEEK)
}