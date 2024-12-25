from sqlmodel import SQLModel, Field
from sqlalchemy import PrimaryKeyConstraint


class User(SQLModel, table=True):
    __tablename__ = "users"
    login: str = Field(default=None, primary_key=True)
    password: str = Field(default=None, nullable=False)
    firstName: str = Field(default=None, nullable=True)
    lastName: str = Field(default=None, nullable=True)
    email: str | None = Field(default=None, nullable=True)
    city: str | None = Field(default=None, nullable=True)
    address: str | None = Field(default=None, nullable=True)
    isAdmin: bool = Field(default=False, nullable=False)


class Restaurant(SQLModel, table=True):
    __tablename__ = "restaurants"
    id: int | None = Field(default=None, primary_key=True)
    name: str = Field(default=None, nullable=False)
    city: str = Field(default=None, nullable=False)
    address: str = Field(default=None, nullable=False)
    imageUrl: str = Field(default="static/no_photo.jpg", nullable=False)


class Item(SQLModel, table=True):
    __tablename__ = "items"
    id: int | None = Field(default=None, primary_key=True)
    name: str = Field(default=None, nullable=False)
    ingredients: str = Field(default=None, nullable=False)
    price: float = Field(default=None, nullable=False)
    restaurantId: int = Field(default=None, nullable=False, foreign_key="restaurants.id")
    imageUrl: str = Field(default="static/no_photo.jpg", nullable=False)


class Cart(SQLModel, table=True):
    __tablename__ = "carts"
    userLogin: str = Field(default=None, foreign_key="users.login")
    itemId: int = Field(default=None, foreign_key="items.id")
    itemQuantity: int = Field(default=1, nullable=False)
    __table_args__ = (PrimaryKeyConstraint("userLogin", "itemId"),)


class Order(SQLModel, table=True):
    __tablename__ = "orders"
    id: int | None = Field(default=None, primary_key=True)
    userLogin: str = Field(default=None, foreign_key="users.login")


class PaymentMethod(SQLModel, table=True):
    __tablename__ = "payment_methods"
    id: int | None = Field(default=None, primary_key=True)
    cardNumber: int = Field(default=None, nullable=False)
    userLogin: str = Field(default=None, foreign_key="users.login")