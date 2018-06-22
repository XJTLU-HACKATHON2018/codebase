import time

def trans_to_timestamp(date):
    timeStruct = time.strptime(date, "%Y-%m-%d")
    timeStamp = int(time.mktime(timeStruct))
    return timeStamp

def trans_to_Date(timeStamp):
    localTime = time.localtime(timeStamp)
    #strTime = time.strftime("%Y-%m-%d %H:%M:%S", localTime)
    strTime = time.strftime("%Y-%m-%d", localTime)
    return strTime
