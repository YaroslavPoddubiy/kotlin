package com.example.fooddelivery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class RestaurantsViewModel : ViewModel() {
    private val _restaurants = MutableLiveData<List<Restaurant>>()
    val restaurants: LiveData<List<Restaurant>> get() = _restaurants

    init {
        fetchAllRestaurants()
    }

    fun fetchAllRestaurants() {
        viewModelScope.launch {
            try {
                val response = FastApiClient.apiService.getAllRestaurants()
                _restaurants.postValue(response)
            } catch (e: Exception) {
                _restaurants.postValue(emptyList<Restaurant>())
            }
        }
    }

    fun fetchRestaurants(city: String) {
        viewModelScope.launch {
            try {
                val response = FastApiClient.apiService.getRestaurants(city)
                _restaurants.postValue(response)
            } catch (e: Exception) {
                _restaurants.postValue(emptyList<Restaurant>())
            }
        }
    }
}


class ItemsViewModel : ViewModel() {
    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> get() = _items

    fun fetchAllItems() {
        viewModelScope.launch {
            try {
                val response = FastApiClient.apiService.getAllItems()
                _items.postValue(response)
            } catch (e: Exception) {
                _items.postValue(listOf())
            }
        }
    }

    fun fetchItems(restaurantId: Int) {
        viewModelScope.launch {
            try {
                val response = FastApiClient.apiService.getItems(restaurantId)
                _items.postValue(response)
            } catch (e: Exception) {
                _items.postValue(listOf())
            }
        }
    }

}


class ItemViewModel : ViewModel() {
    val _items = MutableLiveData<Item>()
    val item: LiveData<Item> get() = _items

    fun getItemById(itemId: Int){
        viewModelScope.launch {
            try {
                val response = FastApiClient.apiService.getItemById(itemId)
                _items.postValue(response)
            } catch (e: Exception) {
                _items.postValue(Item(0, "${e.message}", "error", 0.0, 0, "static/no_photo.jpg"))
            }
        }
    }
}