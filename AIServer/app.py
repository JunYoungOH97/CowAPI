## uvicorn app:app --host 0.0.0.0 --port 8080

from fastapi import FastAPI, WebSocket
from fastapi.encoders import jsonable_encoder
from CategorizationController import CategorizationController
from fastapi.middleware.cors import CORSMiddleware
from secrets import *
from fastapi.concurrency import run_in_threadpool
from celery import Celery
from pydantic import BaseModel

class S3Dto(BaseModel):
    s3Path: str

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=corsURL,
    allow_credentials=False,
    allow_methods=["*"],
    allow_headers=["*"],
)

celery = Celery("CowAPI", broker=f'redis://{ip}:{port}')
celery.conf.update()

@celery.task()
def task(s3Path):
    controller = CategorizationController(s3Path)
    return controller.returnToSpring()


@app.post("/")
async def root(s3Dto : S3Dto):
    result = task(s3Dto.s3Path)
    return jsonable_encoder(result)