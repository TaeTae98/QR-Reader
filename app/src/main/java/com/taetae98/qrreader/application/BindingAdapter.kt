package com.taetae98.qrreader.application

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView
import com.google.zxing.BarcodeFormat
import com.taetae98.qrreader.view.BarcodeView

object BindingAdapter {
    @JvmStatic
    @BindingAdapter(value = ["barcode", "format"], requireAll = false)
    fun barcode(view: BarcodeView, barcode: String = "", format: BarcodeFormat = BarcodeFormat.QR_CODE) {
        view.barcode = barcode
        view.format = format
    }

    @JvmStatic
    @BindingAdapter(value = ["barcode", "format"], requireAll = false)
    fun barcode(view: ShapeableImageView, barcode: String = "", format: BarcodeFormat = BarcodeFormat.QR_CODE) {
        view.setImageBitmap(barcode.toBarcode(format))
    }

    @JvmStatic
    @BindingAdapter("floatingVisible")
    fun floatingVisible(view: FloatingActionButton, boolean: Boolean = false) {
        if (boolean) {
            view.show()
        } else {
            view.hide()
        }
    }

    @JvmStatic
    @BindingAdapter("isActivated")
    fun isActivated(view: View, boolean: Boolean) {
        view.isActivated = boolean
    }
}