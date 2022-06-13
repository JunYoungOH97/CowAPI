import boto3

from secrets import *

def connectionS3():
    try:
        s3 = boto3.client(
            service_name='s3',
            region_name=AWS_S3_BUCKET_REGION,
            aws_access_key_id=AWS_ACCESS_KEY,
            aws_secret_access_key=AWS_SECRET_ACCESS_KEY
        )

    except Exception as e:
        print(e)
    else:
        return s3
    
def getObjectS3(s3, s3Path):
    try:
        s3.download_file(s3Path)
    except Exception as e:
        print(e)
        return False
    return True