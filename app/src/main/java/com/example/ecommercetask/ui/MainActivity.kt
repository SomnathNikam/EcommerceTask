package com.example.ecommercetask.ui

import ColorItemAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.ecommercetask.R
import com.example.ecommercetask.databinding.ActivityMainBinding
import com.example.ecommercetask.repository.ProductRepository
import com.example.ecommercetask.utils.NetworkUtils
import com.example.ecommercetask.viewmodel.ProductViewModel
import com.example.ecommercetask.viewmodel.ProductViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ProductViewModel
    private var quantity = 1
    private val minQuantity = 1
    private val maxQuantity = 10

    private var isProductDescriptionExpanded = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageToggle.setOnClickListener {
            binding.imageToggle.setImageResource(R.drawable.uparrowbtn)
        }
        binding.btnIncrease.setOnClickListener {
            increaseQuantity()
        }

        binding.btnDecrease.setOnClickListener {
            decreaseQuantity()
        }
        binding.toolbar.ivBackbtn.setOnClickListener {
            finish()
        }


        val colors = listOf(
            R.drawable.icon_cropped,
            R.drawable.icon_cropped,
            R.drawable.icon_cropped,
            R.drawable.icon_cropped,
            R.drawable.icon_cropped,
            R.drawable.icon_cropped,
            R.drawable.icon_cropped,
            R.drawable.icon_cropped,
            R.drawable.icon_cropped,
            R.drawable.icon_cropped

        )

        val adapter = ColorItemAdapter(colors) { selectedColorResId ->

        }

        binding.colorRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.colorRecyclerView.adapter = adapter

        if (NetworkUtils.isConnected(this)) {
            loaddetails()
        }else{
            Toast.makeText(this,"Please connect to the Internet",Toast.LENGTH_SHORT).show()
        }

    }

    private fun loaddetails(){
        val repository = ProductRepository()
        val factory = ProductViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[ProductViewModel::class.java]

        viewModel.product.observe(this) { productResponse ->
            val product = productResponse.data
            binding.productBrand.text = "${product.brand_name}"
            binding.productName.text = product.name
            binding.toolbar.abTitle.text = product.name
            binding.productPrice.text = "${product.price} KWD"
            binding.productSku.text = "Sku : ${product.sku}"
            binding.productDescription.text = HtmlCompat.fromHtml(product.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
            expandableText()
            if (product.images.isNotEmpty()) {
                binding.productImage.load(product.images[0])
            }
        }
    }
    private fun decreaseQuantity() {
        if (quantity > minQuantity) {
            quantity--
            binding.textQuantitybtn.text = quantity.toString()
        }
    }

    private fun increaseQuantity() {
        if (quantity < maxQuantity) {
            quantity++
            binding.textQuantitybtn.text = quantity.toString()
        }
    }
    private fun expandableText() {

        binding.imageToggle.setOnClickListener {
            isProductDescriptionExpanded = !isProductDescriptionExpanded

            if (isProductDescriptionExpanded) {
                binding.productDescription.maxLines = Integer.MAX_VALUE
                binding.productDescription.ellipsize = null
                binding.imageToggle.setImageResource(R.drawable.uparrowbtn)
            } else {
                binding.productDescription.maxLines = 3
                binding.productDescription.ellipsize = TextUtils.TruncateAt.END
                binding.imageToggle.setImageResource(R.drawable.down_arrow)
            }
        }
    }
}