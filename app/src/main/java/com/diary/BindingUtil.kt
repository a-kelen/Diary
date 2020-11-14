package com.diary
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.diary.database.Note
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("valueMonthFormatted")
fun TextView.setMonthFormated(item : Note?) {
    item?.let {
        var date = Date(it.created)
        var res = when(date.month) {
            0 -> "Jan."
            1 -> "Feb."
            2 -> "Mar."
            3 -> "Apr."
            4 -> "May."
            5 -> "Jun."
            6 -> "Jul."
            7 -> "Aug."
            8 -> "Sept."
            9 -> "Oct."
            10 -> "Nov."
            11 -> "Dec."
            else -> ""
        }
        text = res
    }
}

@BindingAdapter("valueDayFormatted")
fun TextView.setDayFormated(item : Note?) {
    item?.let {
        var date = Date(it.created)
        var formater = SimpleDateFormat("dd")
        text = formater.format(date)
    }
}

@BindingAdapter("valueTitleFormatted")
fun TextView.setTitleFormated(item : Note?) {
    item?.let {
        text = it.title
    }
}
@BindingAdapter("valueContentFormatted")
fun TextView.setContentFormated(item : Note?) {
    item?.let {
        text = it.content
    }
}
