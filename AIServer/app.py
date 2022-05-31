## uvicorn app:app --host 0.0.0.0 --port 8080

from fastapi import FastAPI, WebSocket
from CategorizationController import CategorizationController

app = FastAPI()

@app.get("/")
async def root():
    controller = CategorizationController()
    print(controller.getResult())
    return {"message": "Hello World"}