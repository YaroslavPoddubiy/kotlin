import requests

from routes import add_to_cart


def main():
    # response = requests.post("http://192.168.0.108:8000/login/", json={"login": "user", "password": "password"})
    # response = requests.get("http://192.168.0.108:8000/users/")
    response = requests.post("http://192.168.0.108:8000/remove_from_cart",
                             json={"userLogin": "user",
                                   "itemId": 1})
    # response = requests.get("http://192.168.0.108:8000/cart/user")
    print(response.status_code)
    # print(response.text)
    print(response.json())
    # response = requests.get("http://192.168.0.108:8000/restaurants/rivne")
    print(response.status_code)
    print(response.json())


if __name__ == '__main__':
    main()
