from pydantic import BaseModel
from typing import Optional
from enum import Enum

class UserRole(str, Enum):
    ADMIN = "ADMIN"
    INFLUENCER = "INFLUENCER"
    BRAND = "BRAND"
    SUPPLIER = "SUPPLIER"

class BaseUserModel(BaseModel):
    username: str
    password: str
    email: str
    address: str
    role: UserRole

class BaseUserLoginModel(BaseModel):
    username: str
    password: str

class BaseUserResponseModel(BaseModel):
    id: str
    username: str
    email: str
    address: str
    role: UserRole
