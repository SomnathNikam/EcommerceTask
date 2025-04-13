package com.example.ecommercetask.model

data class ProductResponse(
    val status: Int,
    val message: String,
    val data: ProductData
)

data class ProductData(
    val id: String,
    val sku: String,
    val is_return: Int,
    val celebrity_id: Int,
    val name: String,
    val attribute_set_id: String,
    val price: String,
    val final_price: String,
    val status: String,
    val type: String,
    val web_url: String,
    val brand_name: String,
    val brand: String,
    val is_following_brand: Int,
    val brand_banner_url: String,
    val is_salable: Boolean,
    val is_new: Int,
    val is_sale: Int,
    val is_trending: Int,
    val is_best_seller: Int,
    val image: String,
    val created_at: String,
    val updated_at: String,
    val weight: Any?, // Nullable since it's null in your JSON
    val description: String,
    val short_description: String?,
    val how_to_use: String?,
    val manufacturer: String?,
    val key_ingredients: String?,
    val returns_and_exchanges: String?,
    val shipping_and_delivery: String?,
    val about_the_brand: String?,
    val meta_title: String,
    val meta_keyword: String?,
    val meta_description: String,
    val size_chart: String?,
    val wishlist_item_id: Int,
    val has_options: String,
    val options: List<Any>,
    val bundle_options: List<Any>,
    val configurable_option: List<ConfigurableOption>,
    val remaining_qty: Int,
    val images: List<String>
)

data class ConfigurableOption(
    val attribute_id: Int,
    val type: String,
    val attribute_code: String,
    val attributes: List<Attribute>
)

data class Attribute(
    val value: String,
    val option_id: String,
    val attribute_image_url: String,
    val price: String,
    val images: List<String>,
    val color_code: String?,
    val swatch_url: String
)

