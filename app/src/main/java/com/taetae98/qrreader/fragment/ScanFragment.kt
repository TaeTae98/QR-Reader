package com.taetae98.qrreader.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.google.zxing.integration.android.IntentIntegrator
import com.taetae98.modules.library.navigation.NavigationFragment
import com.taetae98.qrreader.R
import com.taetae98.qrreader.databinding.FragmentScanBinding
import com.taetae98.qrreader.manager.SimpleClipboardManager
import com.taetae98.qrreader.repository.BarcodeDataRepository
import com.taetae98.qrreader.viewmodel.BarcodeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ScanFragment : NavigationFragment<FragmentScanBinding>(R.layout.fragment_scan) {
    private val barcodeViewModel by activityViewModels<BarcodeViewModel>()

    @Inject
    lateinit var barcodeDataRepository: BarcodeDataRepository

    private val onScanResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        when(it.resultCode) {
            Activity.RESULT_OK -> {
                IntentIntegrator.parseActivityResult(it.resultCode, it.data).also { result ->
                    barcodeViewModel.barcode.value = result.contents
                    CoroutineScope(Dispatchers.IO).launch {
                        barcodeDataRepository.insert(result.contents)
                    }
                }
            }
        }
    }

    @Inject
    lateinit var simpleClipboardManager: SimpleClipboardManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        onCreateSupportActionBar()

        return binding.root
    }

    override fun onCreateViewDataBinding() {
        super.onCreateViewDataBinding()
        binding.barcodeViewModel = barcodeViewModel
        binding.setOnScan { onScan() }
    }

    private fun onCreateSupportActionBar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun onScan() {
        onScanResult.launch(IntentIntegrator.forSupportFragment(this).apply {
            setPrompt("")
            setOrientationLocked(false)
            setBeepEnabled(false)
        }.createScanIntent())
    }
}