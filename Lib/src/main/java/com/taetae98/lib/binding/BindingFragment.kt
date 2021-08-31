package com.taetae98.lib.binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.taetae98.lib.base.BaseFragment

abstract class BindingFragment<VB: ViewDataBinding>(
    @LayoutRes
    layoutRes: Int
) : BaseFragment() {
    protected val binding: VB by lazy {
        DataBindingUtil.inflate(layoutInflater, layoutRes, null ,false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        onCreateViewDataBinding()
        return binding.root
    }

    protected fun onCreateViewDataBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
    }
}