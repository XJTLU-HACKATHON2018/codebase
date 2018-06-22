from datetime import datetime
import time

def parse_time(strtime):
    return time.mktime(datetime.strptime(strtime, "%Y/%m/%d").timetuple())

#print(parse_time("1995/12/23"))
