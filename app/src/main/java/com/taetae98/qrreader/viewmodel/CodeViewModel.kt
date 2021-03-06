package com.taetae98.qrreader.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.zxing.BarcodeFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CodeViewModel @Inject constructor(
    stateHandle: SavedStateHandle
): ViewModel() {
    val code by lazy { stateHandle.getLiveData("CODE_VIEW_MODEL_CODE", "") }
    val format by lazy { stateHandle.getLiveData("CODE_VIEW_MODEL_FORMAT", BarcodeFormat.QR_CODE) }
}