from __future__ import absolute_import
from __future__ import print_function
from __future__ import division

from flask import Flask, request, json
from mercurius.data import candlereader
import numpy as np
import ccxt

app = Flask(__name__)

@app.route('/uploadAlgo', methods=['POST', 'GET'])
def run_algo():
    if request.method == "POST":
        data = request.get_json()
        exchange = data["exchange"]
        start_time = data["start_time"]
        end_time = data["end_time"]
        timeframe = data["timeframe"]
        symbols = data["symbols"]
        da = candlereader.CandleReader(symbols, start_time, end_time, timeframe, exchange).get_data()
        close_price = da.sel(feature='close')
        #algo.run()
        response = {}
        response['close'] = close_price.data.tolist()
        response['date'] = [x/1e6 for x in da['openTime'].values.astype(np.int64)]
        #print(response['date'])
        #print(type(response['date']))
        print('PASS')
        return json.jsonify(response)
    print('FAIL')
    return 'FAIL'

@app.route('/coin/<symbol>', methods=['POST', 'GET'])
def get_coin_price(coin):
    """default poloniex"""
    if request.method == "POST":
        symbol = str(coin).upper()+"/BTC"
        df = candlereader.CandleReader(symbol).get_data().to_pandas()
        print(df)
        print(type(df))
        return 'PASS'
    return 'FAIL'

@app.route('/balances', methods=['GET'])
def get_balance(exchange="binance"):
    assets = ['BTC', 'VEN', 'BNB', 'USDT']
    exchange = getattr(ccxt, exchange)({'apiKey': '8f8tN9PmXCQSfU4RpBvE0Y8vQioQkbH2vUkVC6cS0jTpSplGufBxSmAOpkQokYt4', 'secret': '9w0aoElX1iRrhOOptnMiYRei57sBAZQmMZKQpwsp3IeYqBTP7LpsRJhhFvO1WWFv'})
    balances = exchange.fetchBalance()
    res = {}
    for i in assets:
        res[i] = balances[i]
    return json.jsonify(res)

@app.route('/orders', methods=['GET'])
def get_orders(exchange="binance"):
    pairs = ['ETH/BTC', 'VEN/BTC', 'BNB/BTC', 'BTC/USDT'] #we could include this as input parameter later
    exchange = getattr(ccxt, exchange)({'apiKey': '8f8tN9PmXCQSfU4RpBvE0Y8vQioQkbH2vUkVC6cS0jTpSplGufBxSmAOpkQokYt4', 'secret': '9w0aoElX1iRrhOOptnMiYRei57sBAZQmMZKQpwsp3IeYqBTP7LpsRJhhFvO1WWFv'})
    res = {}
    for i in pairs:
        res[i] = exchange.fetchOrders(i)
    return json.jsonify(res)


def main():
    app.run(host='0.0.0.0', debug=True)


if __name__ == "__main__":
    main()
