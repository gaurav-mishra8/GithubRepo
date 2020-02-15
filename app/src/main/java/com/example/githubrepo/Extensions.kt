package com.example.githubrepo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.githubrepo.ui.ViewModelFactory

inline fun <reified T : ViewModel> ViewModelStoreOwner.getViewModel(block: T.() -> Unit): T {

    val vm = ViewModelProvider(this).get(T::class.java)
    vm.block()
    return vm

}

inline fun <reified T : ViewModel> ViewModelStoreOwner.getViewModelByFactory(
    factory: ViewModelFactory,
    block: T.() -> Unit
): T {

    val vm = ViewModelProvider(this, factory).get(T::class.java)
    vm.block()
    return vm

}