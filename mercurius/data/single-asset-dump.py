import json
import ccxt
from datetime import datetime, timedelta

def get_coin_price(exchange="poloniex"):
    """default poloniex"""
    res = {}
    symbols = ['eth', 'ltc', 'xrp', 'etc', 'dash', 'xmr', 'xem', 'fct', 'gnt', 'zec']
    start = (datetime.utcnow()-timedelta(days=360)).strftime("%Y-%m-%d %H:%M:%S")
    end = (datetime.utcnow()).strftime("%Y-%m-%d %H:%M:%S")
    tf = '15m'  # timeframe
    exchange = getattr(ccxt, exchange)({'enableRateLimit': True, 'options': {'adjustForTimeDifference':True}})

    for symbol in symbols:
        pair = str(symbol).upper()+"/BTC"
        start = exchange.parse8601(start)
        end = exchange.parse8601(end)
        num_candles = int((end - start) / exchange.parse_timeframe(tf)/1e3)
        data = exchange.fetch_ohlcv(pair, tf, start, num_candles)
        time_list = []
        ohlc = []
        volume = []
        for i in data:
            time_list.append((datetime.utcfromtimestamp(int(i[0]/1e3))).strftime('%Y-%m-%d %H:%M:%S'))
            ohlc.append(i[1:5])
            volume.append(i[5])

        res['time'] = time_list
        res['ohlc'] = ohlc
        res['vol'] = volume #no need for volume

        with open(str(pair)+'.json', 'w') as outf:
            json.dump(res, outf)




if __name__ == "__main__":
    get_coin_price()
