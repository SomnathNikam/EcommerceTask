package com.example.ecommercetask.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercetask.model.ProductResponse
import com.example.ecommercetask.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _product = MutableLiveData<ProductResponse>()
    val product: LiveData<ProductResponse> = _product

    init {
        fetchProductDetailsInfo()
    }
    private fun fetchProductDetailsInfo() {
        viewModelScope.launch {
            val response = repository.getProductDetails("6701", "253620", "en", "KWD")
            if (response.isSuccessful) {
                _product.value = response.body()
            } else {
                Log.e("API_ERROR", response.message())
            }
        }

    }
}
