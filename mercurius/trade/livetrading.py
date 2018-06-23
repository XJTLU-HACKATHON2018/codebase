import ccxt

def test(apikey, secret, exchange='binance'):
    exchange = getattr(ccxt, exchange)({'apiKey': apikey, 'secret': secret})
    exchange.load_markets()

    #l = ['ETH/BTC', 'ETC/BTC', 'USDT/BTC']

    #for i in l:
    #    print(i)
    #    print(exchange.fetchBalance(i))
    print(exchange.fetchOpenOrders())
    #print(exchange.fetchOrder())

    #orderbook = exchange.fetchOrderBook(exchange.symbols[0])
    ##symbols = exchange.fetch_markets()
    ##print(type(symbols))
    ##for i in symbols:
    ##    print(i)
    ##orderbook = exchange.fetch_order_book ()
    #print(exchange.symbols)
    #bid = orderbook['bids'][0][0] if len (orderbook['bids']) > 0 else None
    #ask = orderbook['asks'][0][0] if len (orderbook['asks']) > 0 else None
    #spread = (ask - bid) if (bid and ask) else None
    #print (exchange.id, 'market price', { 'bid': bid, 'ask': ask, 'spread': spread })
    #print(exchange.fetch_tickers())

if __name__ == "__main__":
    test()
