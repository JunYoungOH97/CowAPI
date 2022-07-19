from CategorizationService import CategotizationService

class CategorizationController:
    def __init__(self, s3Path):
        self.service = CategotizationService(s3Path)

    def runPipeline(self):
        self.service.PipeLine()

    def setResult(self):
        self.runPipeline()
        self.result = self.service.getResult()

    def getResult(self):
        return self.result

    def returnToSpring(self):
        self.setResult()
        result = self.getResult()

        returnResult = dict()
        returnResult["category"] = result[0]
        returnResult["score"] = result[1]

        return returnResult
