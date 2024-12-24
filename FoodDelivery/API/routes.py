from fastapi import FastAPI, Depends, HTTPException
from sqlmodel import create_engine, Session, select
from typing import Annotated
from sqlalchemy import func
from models import *
from schemas import *
from fastapi.middleware.cors import CORSMiddleware
from fastapi.staticfiles import StaticFiles


def create_db():
    SQLModel.metadata.create_all(engine)


def get_session():
    with Session(engine) as session:
        yield session


app = FastAPI()


origins = ["*"]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

app.mount("/static", StaticFiles(directory="static"), name="static")

engine = create_engine("sqlite:///database.db")

SessionDep = Annotated[Session, Depends(get_session)]


# def get_users():
#     all_users = []
#     with open('users.json', 'r') as f:
#         for user in json.load(f):
#             all_users.append(user)
#     print(all_users)
#     return all_users


@app.get("/")
async def main():
    return {"content": "Hello World!"}


@app.get("/hello/")
async def hello():
    return {"message": "Hello World!"}


@app.get("/users/")
async def users(session: SessionDep):
    statement = select(User)
    all_users = session.exec(statement)
    return all_users


@app.get("/login/")
async def login():
    pass


@app.post("/login/")
async def login(form: LoginForm, session: SessionDep):
    user = session.get(User, form.login)
    if not user:
        return {"login": "none"}
    if user.password != form.password:
        return {"login": "none"}
        # raise HTTPException(
        #     400,
        #     "Wrong login or password",
        # )
    return user


@app.get("/register/")
async def register():
    pass


@app.post("/register/")
async def register(form: LoginForm, session: SessionDep):
    user = session.get(User, form.login)
    if user:
        raise HTTPException(400, "User is already registered")
    session.add(User(login=form.login, password=form.password))
    session.commit()
    return user


@app.get("/logout/")
async def logout():
    pass


@app.get("/restaurants/{city}/")
async def restaurants(city, session: SessionDep):
    statement = select(Restaurant)
    # statement = select(Restaurant)
    result = session.exec(statement)
    restaurants = []
    for restaurant in result:
        # print(restaurant.city.lower(), city.lower())
        # restaurants.append({"restaurant_city": restaurant.city.lower(),
        #                     "request_city": city.lower()})
        if restaurant.city.lower() == city.lower():
            restaurants.append(restaurant)
        # restaurants.append({"restaurant_city": str(restaurant.city).lower(),
        #                     "request_city": str(city).lower(),
        #                     "are_equal": str(restaurant.city).lower() == str(city).lower()})
        # restaurants.append(restaurant)
    # return {"city": city}
    # restaurants.append({"city": "test"})
    return restaurants


@app.get("/restaurants/")
async def all_restaurants(session: SessionDep):
    statement = select(Restaurant)
    result = session.exec(statement)
    restaurants = []
    for restaurant in result:
        restaurants.append(restaurant)
    print(restaurants)
    return restaurants


@app.get("/menu/{restaurant_id}")
async def menu(restaurant_id, session: SessionDep):
    statement = select(Item).where(Item.restaurant_id == restaurant_id)
    result = session.exec(statement)
    items = []
    for item in result:
        items.append(item)
    return items


@app.get("/menu/")
async def menu(restaurant_id, session: SessionDep):
    statement = select(Item)
    result = session.exec(statement)
    items = []
    for item in result:
        items.append(item)
    return items


@app.get("/item/{item_id}")
async def item(item_id, session: SessionDep):
    _item = session.get(Item, item_id)
    return _item


@app.on_event("startup")
def startup():
    create_db()
