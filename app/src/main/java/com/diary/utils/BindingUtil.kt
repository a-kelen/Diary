package com.diary.utils
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.diary.R
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

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}
