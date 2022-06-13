import urllib
import cv2
from S3Repository import *
import numpy as np
from PIL import Image

class CategorizationRepository:
    def __init__(self):
        self.imgPath = "./SampleData/Dog.jpg"
        self.categoryPath = "./CategoryData/imagenet_classes.txt"
        self.modelState = "./ModelState/vgg19_bn-c79401a0.pth"
        
    def getImgPath(self):
        return self.imgPath

    def getCategoryPath(self):
        return self.categoryPath

    def getModelState(self):
        return self.modelState

    def readImage(self, path):
        resp = urllib.request.urlopen(path)
        image = np.asarray(bytearray(resp.read()), dtype="uint8")
        image = cv2.imdecode(image, cv2.IMREAD_COLOR)
        image = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
        self.image = image

    def getImg(self, path):
        self.readImage(path)
        self.image = Image.fromarray(self.image)
        return self.image
        