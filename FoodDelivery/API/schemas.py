from pydantic import BaseModel


class LoginForm(BaseModel):
    login: str
    password: str


class AddToCartForm(BaseModel):
    userLogin: str
    itemId: int