package com.example.fooddelivery

import android.util.Log
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class UserViewModel : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> get() = _items

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            try {
                val response = FastApiClient.apiService.login(loginRequest)
                _user.postValue(response)
            } catch (e: Exception) {
                _error.postValue("${e.message}")
                _user.postValue(null)
            }
        }
    }

    fun register(loginRequest: LoginRequest) {
        viewModelScope.launch {
            try {
                val response = FastApiClient.apiService.register(loginRequest)
                _user.postValue(response)
            } catch (e: Exception) {
                _user.postValue(null)
            }
        }
    }

    fun getCart() {
        viewModelScope.launch {
            try {
                val response = _user.value?.let { FastApiClient.apiService.cart(it.login) }
                _items.postValue(response)
            } catch (e: Exception) {
                _items.postValue(null)
            }
        }
    }

    fun addToCart(itemId: Int) {
        viewModelScope.launch {
            try {
                val response = FastApiClient.apiService.addToCart(AddToCartRequest(_user.value?.login!!, itemId))
            } catch (e: Exception){
                _error.postValue("${e.message}")
            }
        }
    }

    fun removeFromCart(itemId: Int) {
        viewModelScope.launch {
            try {
                FastApiClient.apiService.removeFromCart(AddToCartRequest(_user.value?.login!!, itemId))
            } catch (e: Exception){
                _error.postValue("${e.message}")
            }
        }
    }
}


class RestaurantsViewModel : ViewModel() {
    private val _restaurants = MutableLiveData<List<Restaurant>>()
    val restaurants: LiveData<List<Restaurant>> get() = _restaurants

//    init {
//        fetchAllRestaurants()
//    }

    fun fetchAllRestaurants() {
        viewModelScope.launch {
            try {
                val response = FastApiClient.apiService.getAllRestaurants()
                _restaurants.postValue(response)
            } catch (e: Exception) {
                _restaurants.postValue(emptyList())
            }
        }
    }

    fun fetchRestaurants(city: String) {
        viewModelScope.launch {
            try {
                val response = FastApiClient.apiService.getRestaurants(city)
                _restaurants.postValue(response)
            } catch (e: Exception) {
                _restaurants.postValue(emptyList())
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