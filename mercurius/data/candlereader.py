from __future__ import absolute_import
from __future__ import division
from __future__ import print_function

import ccxt
import pandas as pd
import numpy as np
import xarray as xr
#import sqlalchemy
from datetime import datetime, timedelta


class CandleReader(object):
    """A wrapper for ccxt. Convert OHLCV to pandas dataframe
    """

    def __init__(self, symbols, start=(datetime.utcnow()-timedelta(minutes=60)).strftime("%Y-%m-%d %H:%M:%S"), end=(datetime.utcnow()).strftime("%Y-%m-%d %H:%M:%S"), timeframe='15m', exchange='poloniex'):
        """Params
        : symobols (list): a list of symbol of target assets
        : start : (str) UTC start time
        : end : (str) UTC end time
        : time frame (str) : the interval getting data
        : exchange (str) : the exchange for downloading data
        """
        super(CandleReader, self).__init__()
        self.exchange = getattr(ccxt, exchange)()
        #print("start: ", start)
        #print("end: ", end)
        self.start = self.exchange.parse8601(start)
        self.end = self.exchange.parse8601(end)
        self.timeframe = timeframe
        self.symbols = symbols


    def _to_df(self, ohlcv):
        #index = pd.Series(np.arange(start, end, interval), name='openTime')
        #init_chart = pd.DataFrame(np.nan, index=index, columns
        df = pd.DataFrame(
            ohlcv, columns=['timestamp', 'open', 'high', 'low', 'close', 'volume'])
        df['openTime'] = pd.to_datetime(df.timestamp / 1e3, unit='s')
        df.index = pd.DatetimeIndex(df['openTime']).round('S')
        df = df.drop(columns=['openTime','timestamp'])
        return df

    def get_data(self):
        raw_data_dict = {}
        if not isinstance(self.end, int) or not isinstance(self.start, int):
            raise TypeError("Instance Time Type needs to be int")

        if self.end > self.start:
            num_candles = (self.end-self.start)/self.exchange.parse_timeframe(self.timeframe)/1e3
            #print("start time: ", self.start)
            #print("end time: ", self.end)
            #print("num of candles: ", num_candles)
        else:
            raise ValueError('end_date should be larger than start_data')

        for symbol in self.symbols:
            ohlcv = self.exchange.fetch_ohlcv(symbol, self.timeframe, self.start, num_candles)
            raw_data_dict[symbol] = self._to_df(ohlcv)

        chart = xr.DataArray(pd.Panel(raw_data_dict, dtype=np.float32), dims=["asset", "openTime", "feature"])
        return chart.transpose("feature", "asset", "openTime")

    def get_close(self):
        da = self.get_data()
        close_price = da.sel(feature='close')
        return close_price.data


    def save_to_sql(self, df):
        df.to_csv('')
        pass
        #engine = create_engine("mysql+mysqldb://...")
        #df.to_sql('candlestick', engine, if_exists='replace', index=False, chunksize=10000)

if __name__ == "__main__":
    from mercurius.strategy import olmar
    exchange = ccxt.poloniex()
    symbols = ['ETH/BTC', 'ETC/BTC']
    #symbol = 'ETH/BTC'

    #index = 4
    #length = 80
    #heigh = 15
    #timeframe = '15m'
    #start = exchange.parse8601('2017-10-15 00:00:00')
    #end = exchange.parse8601('2017-10-25 00:00:00')

    # print(type(start))
    # print(end)
#    if end > start:
#        num_candles = (end-start)/exchange.parse_timeframe(timeframe)/1e3
#        print(num_candles)
#

    #testdr = CandleReader(symbols, '2017-10-15 00:00:00',
    #                    '2017-10-25 00:00:00', '15m', 'poloniex')
    testdr = CandleReader(symbols, '2017-04-22 00:00:00', '2018-04-22 00:00:00', '15m', 'poloniex').get_data()
    close_data = CandleReader(symbols, '2017-04-22 00:00:00', '2018-04-22 00:00:00', '15m', 'poloniex').get_close()

    print(close_data.shape)

    #re = olmar.olmar().get_b(close_data, np.ones(2)/2)
    re = olmar.olmar().trade(close_data)
    print(re)

    #init_pv = 1
    #weight = np.array([.5, .5])
    #seq_pv = weight[:,None] * close_data
    #resu = seq_pv.sum(axis=0)
    #resu = resu / resu[0]
    #cum_resu = np.cumprod(resu)
    #ucrp_re = pd.DataFrame(cum_resu, index=testdr["openTime"], columns=['data'])

    #ucrp.to_json('ucrp.json', orient='table')
    #print(ucrp_re.head())

    #print(testdr.get_data()) #test passed

    # df = pd.DataFrame(exchange.fetch_ohlcv(symbol, '15m', start, num_candles), columns=[
    #                  'timestamp', 'open', 'high', 'low', 'close', 'volume'])
    #df['date'] = pd.to_datetime(df.timestamp / 10**3, unit='s')
    #df.index = pd.DatetimeIndex(df['date']).round('S')
    # print(df.tail())
    # print('------------------tail->head---------------------')
    # print(df.head())

    # print(df.dtypes)
    #df.index = datetime.fromtimestamp(int(df.timestamp / 1000).strftime('%Y-%m-%d %H:%M:%S')).to_datetime()
    # print(df.head())
    #df.timestamp = pd.DatetimeIndex(df.timestamp)
    # print(df.iloc[0])
    # print(datetime.utcfromtimestamp(df.iloc[0].loc['timestamp'] / 1000).strftime('%Y-%m-%dT%H:%M:%SZ')) #convert time in miliseconds to seconds
    # print(datetime.utcfromtimestamp(df.iloc[1].loc['timestamp'] / 1000).strftime('%Y-%m-%dT%H:%M:%SZ')) #convert time in miliseconds to seconds
