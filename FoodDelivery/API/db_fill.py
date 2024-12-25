import sqlite3


def fill_restaurants():
    connection.execute(
        "INSERT INTO Restaurants (id, name, city, address, imageUrl) VALUES (?, ?, ?, ?, ?)", [1, "MacDonalds", "Рівне", "вул. Чорновола 57", "static/restaurants/macdonalds.jpg"]
    )
    connection.execute(
        "INSERT INTO Restaurants (id, name, city, address, imageUrl) VALUES (?, ?, ?, ?, ?)", [2, "KFC", "Рівне", "вул. Соборна 68Б", "static/restaurants/kfc.jpg"]
    )
    connection.execute(
        "INSERT INTO Restaurants (id, name, city, address, imageUrl) VALUES (?, ?, ?, ?, ?)", [3, "MacDonalds", "Київ", "вул. Політехнічна 71", "static/restaurants/macdonalds.jpg"]
    )
    connection.execute(
        "INSERT INTO Restaurants (id, name, city, address, imageUrl) VALUES (?, ?, ?, ?, ?)", [4, "MacDonalds", "Львів", "вул. Піщана 32", "static/restaurants/macdonalds.jpg"]
    )
    connection.execute(
        "INSERT INTO Restaurants (id, name, city, address, imageUrl) VALUES (?, ?, ?, ?, ?)", [5, "Father", "Рівне", "вул. Чорновола 12", "static/no_photo.jpg"]
    )
    connection.commit()


def fill_menu():
    connection.execute(
        "INSERT INTO Items (id, name, ingredients, price, restaurantId, imageUrl) "
        "VALUES (?, ?, ?, ?, ?, ?)", [1, "BigMac", " ", 120, 1, "static/items/bigmacmenu.jpg"]
    )
    connection.execute(
        "INSERT INTO Items (id, name, ingredients, price, restaurantId, imageUrl) "
        "VALUES (?, ?, ?, ?, ?, ?)", [2, "MacChicken", " ", 70, 1, "static/no_photo.jpg"]
    )
    connection.execute(
        "INSERT INTO Items (id, name, ingredients, price, restaurantId, imageUrl) "
        "VALUES (?, ?, ?, ?, ?, ?)", [3, "Big Tasty", " ", 130, 1, "static/no_photo.jpg"]
    )
    connection.execute(
        "INSERT INTO Items (id, name, ingredients, price, restaurantId, imageUrl) "
        "VALUES (?, ?, ?, ?, ?, ?)", [4, "Chicken Strips", " ", 150, 2, "static/no_photo.jpg"]
    )
    connection.execute(
        "INSERT INTO Items (id, name, ingredients, price, restaurantId, imageUrl) "
        "VALUES (?, ?, ?, ?, ?, ?)", [5, "Pizza Margarita", " ", 210, 3, "static/no_photo.jpg"]
    )
    connection.execute(
        "INSERT INTO Items (id, name, ingredients, price, restaurantId, imageUrl) "
        "VALUES (?, ?, ?, ?, ?, ?)", [6, "Pizza 4 cheeses", " ", 240, 3, "static/no_photo.jpg"]
    )
    connection.execute(
        "INSERT INTO Items (id, name, ingredients, price, restaurantId, imageUrl) "
        "VALUES (?, ?, ?, ?, ?, ?)", [7, "Cola", " ", 30, 2, "static/no_photo.jpg"]
    )
    connection.execute(
        "INSERT INTO Items (id, name, ingredients, price, restaurantId, imageUrl) "
        "VALUES (?, ?, ?, ?, ?, ?)", [8, "Pepsi", " ", 35, 1, "static/no_photo.jpg"]
    )
    connection.commit()


connection = sqlite3.connect("database.db")


fill_restaurants()
fill_menu()