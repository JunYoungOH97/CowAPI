import torch
from PIL import Image
from torchvision import transforms
import torchvision
from CategorizationRepository import CategorizationRepository

class CategotizationService:
    def __init__(self, s3Path):
        self.repository = CategorizationRepository()
        self.img = self.repository.getImg(s3Path)


    ## self.setModel()
    def setModelState(self):
        self.modelState = self.repository.getModelState()

    def setModel(self):
        self.setModelState()
        self.model = torchvision.models.vgg19_bn(pretrained=False)
        self.model.load_state_dict(torch.load(self.modelState))
        self.model.eval()

        if torch.cuda.is_available():
            self.model.to('cuda')


    ## self.setData()
    def getDataFromRepository(self):
        self.data = self.repository.getImgPath()

    @staticmethod
    def preProcessing(input_image):
        preprocess = transforms.Compose([
            transforms.Resize(256),
            transforms.CenterCrop(224),
            transforms.ToTensor(),
            transforms.Normalize(mean=[0.485, 0.456, 0.406], std=[0.229, 0.224, 0.225]),
        ])
        input_tensor = preprocess(input_image)

        return input_tensor

    def setData(self):
        self.getDataFromRepository()

        # input_image = Image.open(self.data)
        input_tensor = self.preProcessing(self.img)
        self.input_batch = input_tensor.unsqueeze(0)

        if torch.cuda.is_available():
            self.input_batch = self.input_batch.to('cuda')



    ## self.runModel()
    def runModel(self):
        with torch.no_grad():
            output = self.model(self.input_batch)
            self.probabilities = torch.nn.functional.softmax(output[0], dim=0)


    ## self.setResult()
    def setCategory(self):
        self.categoryPath = self.repository.getCategoryPath()

    def setResult(self):
        self.setCategory()

        with open(self.categoryPath, "r") as f:
            categories = [s.strip() for s in f.readlines()]
            
        top1_prob, top1_catid = torch.topk(self.probabilities, 1)

        category = categories[top1_catid[0]].split(",")[1].lstrip(" ")

        self.result = [str(category), str(top1_prob[0].item())]
        

    def getResult(self):
        return self.result

    def PipeLine(self):
        self.setModel()
        self.setData()
        self.runModel()
        self.setResult()