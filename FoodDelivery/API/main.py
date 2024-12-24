import requests


def main():
    response = requests.post("http://192.168.0.108:8000/login/", json={"login": "admin", "password": "1111"})
    # response = requests.get("http://192.168.0.108:8000/users/")
    print(response.status_code)
    # print(response.text)
    print(response.json())
    response = requests.get("http://192.168.0.108:8000/restaurants/rivne")
    print(response.status_code)
    print(response.json())


if __name__ == '__main__':
    main()
