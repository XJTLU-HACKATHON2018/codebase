from __future__ import absolute_import
from __future__ import print_function
from __future__ import division

from flask import Flask, request, json
from flask_cors import CORS, cross_origin
from mercurius.data import candlereader
from datetime import datetime, timedelta
import numpy as np
import ccxt

app = Flask(__name__)
cors = CORS(app)
app.config['CORS_HEADERS'] = 'Content-Type'

@app.route('/uploadAlgo', methods=['POST', 'GET'])
def run_algo():
    if request.method == "POST":
        data = request.get_json()
        exchange = data["exchange"]
        start_time = data["start_time"]
        end_time = data["end_time"]
        timeframe = data["timeframe"]
        symbols = data["symbols"]
        da = candlereader.CandleReader(
            symbols, start_time, end_time, timeframe, exchange).get_data()
        close_price = da.sel(feature='close')
        # algo.run()
        response = {}
        response['close'] = close_price.data.tolist()
        response['date'] = [
            x/1e6 for x in da['openTime'].values.astype(np.int64)]
        # print(response['date'])
        # print(type(response['date']))
        print('PASS')
        return json.jsonify(response)
    print('FAIL')
    return 'FAIL'


@app.route('/coin', methods=['POST', 'GET'])
@cross_origin
def get_coin_price(exchange="binance"):
    """default poloniex"""
    res = {}
    symbol = request.args.get('symbol', 'eth')
    start = request.args.get('start', (datetime.utcnow(
    )-timedelta(minutes=120)).strftime("%Y-%m-%d %H:%M:%S"))
    end = request.args.get(
        'end', (datetime.utcnow()).strftime("%Y-%m-%d %H:%M:%S"))
    print(start)
    print(end)
    tf = request.args.get('tf', '15m')  # timeframe
    exchange = getattr(ccxt, exchange)({'apiKey': '8f8tN9PmXCQSfU4RpBvE0Y8vQioQkbH2vUkVC6cS0jTpSplGufBxSmAOpkQokYt4',
                                        'secret': '9w0aoElX1iRrhOOptnMiYRei57sBAZQmMZKQpwsp3IeYqBTP7LpsRJhhFvO1WWFv', 'enableRateLimit': True, 'options': {'adjustForTimeDifference': True}})
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
    app.logger.info(data)

    res['time'] = time_list
    res['ohlc'] = ohlc
    res['vol'] = volume

    return json.jsonify(res)


@app.route('/balances', methods=['GET'])
def get_balance(exchange="binance"):
    assets = ['BTC', 'VEN', 'BNB', 'USDT']
    exchange = getattr(ccxt, exchange)({'apiKey': '8f8tN9PmXCQSfU4RpBvE0Y8vQioQkbH2vUkVC6cS0jTpSplGufBxSmAOpkQokYt4',
                                        'secret': '9w0aoElX1iRrhOOptnMiYRei57sBAZQmMZKQpwsp3IeYqBTP7LpsRJhhFvO1WWFv'})
    balances = exchange.fetchBalance()
    res = {}
    for i in assets:
        res[i] = balances[i]
    return json.jsonify(res)


@app.route('/orders', methods=['GET'])
def get_orders(exchange="binance"):
    # we could include this as input parameter later
    pairs = ['ETH/BTC', 'VEN/BTC', 'BNB/BTC', 'BTC/USDT']
    exchange = getattr(ccxt, exchange)({'apiKey': '8f8tN9PmXCQSfU4RpBvE0Y8vQioQkbH2vUkVC6cS0jTpSplGufBxSmAOpkQokYt4',
                                        'secret': '9w0aoElX1iRrhOOptnMiYRei57sBAZQmMZKQpwsp3IeYqBTP7LpsRJhhFvO1WWFv'})
    res = {}
    for i in pairs:
        res[i] = exchange.fetchOrders(i)
    return json.jsonify(res)


def main():
    app.run(host='0.0.0.0', debug=True)


if __name__ == "__main__":
    main()
