from __future__ import absolute_import, print_function, division
from mercurius.data.candlereader import CandleReader
from pgportfolio.learn.nnagent import NNAgent
from pgportfolio.tools.configprocess import load_config
from pgportfolio.marketdata.globaldatamatrix import get_sentiment_data
from datetime import datetime, timedelta
import numpy as np
import xarray
import pandas as pd


def assets2symbols(assets):
    return [asset+"/BTC" for asset in assets]


def backtest(start, end, agent_index = 1,location = "train_package/"):
    """
    :param location: location of given model
    :param start: start date of backtest
    :param end: end date of backtest
    :param agent_index: index of agent
    :return: dictionary {"portfolio_changes_history":series, "portfolio_weights_history":dataframe,
                        "global_data":xarray}
    """
    config = load_config(agent_index)
    assets = ['ETH', 'LTC', 'XRP', 'ETC', 'DASH', 'XMR', 'XEM', 'FCT', 'GNT', 'ZEC']
    agent = NNAgent(config, location+str(agent_index)+"/netfile")
    reader = CandleReader(symbols=assets2symbols(assets), start=start, end=end, timeframe="30m")
    global_data = reader.get_data()
    global_sentiment = get_sentiment_data(assets2symbols(assets), start, end, "30min")
    global_sentiment = xarray.DataArray(global_sentiment.T.values[None, :, :], dims=["feature", "asset", "openTime"],
                     coords={"feature": ["sentiment"], "asset": global_sentiment.columns,
                             "openTime": global_sentiment.index})
    global_data = xarray.concat([global_data[:-1,:,:], global_sentiment], dim="feature")
    commission_rate = config["trading"]["trading_consumption"]

    window_size = config["input"]["window_size"]
    global_steps = len(global_data["openTime"]) - window_size
    last_w = np.zeros(len(assets)+1)
    last_w[0] = 1
    portfolio_changes_history = {}
    portfolio_weights_history = {}
    print(global_data)
    for i in range(global_steps):
        input_window = global_data[:,:,i:i+window_size]
        y = global_data[0, :, i+1]/global_data[0, :, i]
        datetime = input_window["openTime"][-1].values
        weights = agent.decide_by_history(input_window.values, last_w)
        transaction_cost = np.sum(np.abs(weights-last_w))*commission_rate
        last_w = weights
        portfolio_change = np.dot(y, weights[1:])*(1-transaction_cost)
        portfolio_changes_history[datetime] = portfolio_change
        portfolio_weights_history[datetime] = weights
    return {"portfolio_changes_history": pd.Series(portfolio_changes_history),
            "portfolio_weights_history": pd.DataFrame(portfolio_weights_history),
            "global_data": global_data}


if __name__ == "__main__":
    results = backtest("2018-06-01 00:10:00", "2018-06-05 01:00:00")
    print(datetime.today().timestamp())
    print((datetime.today() - timedelta(days = 1)).timestamp())

    print(results)





