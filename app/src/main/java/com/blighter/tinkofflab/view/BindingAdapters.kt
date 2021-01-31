package com.blighter.tinkofflab.view

import android.net.Uri
import android.widget.ImageButton
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.blighter.tinkofflab.R
import com.blighter.tinkofflab.composeDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("app:gifURL")
fun bindImage(imageView: ImageView, gifURL: String?) {
    if (gifURL != null) {
        Glide.with(imageView.context)
            .asGif()
            .apply(
                RequestOptions()
                    .placeholder(composeDrawable(imageView.context))
            )
            .load(Uri.parse(gifURL))
            .into(imageView)
    }
}

@BindingAdapter("app:previousGifs")
fun ifEnabled(imageButton: ImageButton, number: Int) {
    if (number < 2) {
        imageButton.isEnabled = false
        imageButton.setImageResource(R.drawable.arrow_back_disabled)
    } else if (number == 2) {
        imageButton.isEnabled = true
        imageButton.setImageResource(R.drawable.arrow_back_enabled)
    }
}

@BindingAdapter("app:isVisible")
fun ifLoading(imageView: ImageView, loadingStatus: Boolean) {
    if (loadingStatus) {
        imageView.setImageDrawable(composeDrawable(imageView.context))
    }
}

@BindingAdapter("app:isError")
fun ifError(imageView: ImageView, errorStatus: Boolean) {
    if (errorStatus) {
        imageView.setImageDrawable(imageView.context.getDrawable(R.drawable.ic_baseline_error_24))
    }
}

