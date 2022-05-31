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