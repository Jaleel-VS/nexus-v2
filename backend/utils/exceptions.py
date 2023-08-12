from fastapi import HTTPException

class NotFoundError(HTTPException):
    def __init__(self, detail: str):
        super().__init__(status_code=404, detail=detail)

class InternalServerError(HTTPException):
    def __init__(self, detail: str):
        super().__init__(status_code=500, detail=detail)