from CategorizationService import CategotizationService

class CategorizationController:
    def __init__(self):
        self.service = CategotizationService()

    def runPipeline(self):
        self.service.PipeLine()

    def setResult(self):
        self.result = self.service.getResult()

    def getResult(self):
        self.runPipeline()
        self.setResult()
        return self.result