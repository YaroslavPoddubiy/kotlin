from sqlmodel import SQLModel, Field


class User(SQLModel, table=True):
    __tablename__ = "users"
    login: str = Field(default=None, primary_key=True)
    password: str = Field(default=None, nullable=False)
    first_name: str = Field(default=None, nullable=True)
    last_name: str = Field(default=None, nullable=True)
    email: str | None = Field(default=None, nullable=True)
    address: str | None = Field(default=None, nullable=True)
    admin_status: bool = Field(default=False, nullable=False)


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
    restaurant_id: int = Field(default=None, nullable=False, foreign_key="restaurants.id")
    imageUrl: str = Field(default="static/no_photo.jpg", nullable=False)


class Cart(SQLModel, table=True):
    __tablename__ = "carts"
    id: int | None = Field(default=None, primary_key=True)
    user_login: str = Field(default=None, foreign_key="users.login")
    item_id: int = Field(default=None, foreign_key="items.id")


class Order(SQLModel, table=True):
    __tablename__ = "orders"
    id: int | None = Field(default=None, primary_key=True)
    user_login: str = Field(default=None, foreign_key="users.login")
