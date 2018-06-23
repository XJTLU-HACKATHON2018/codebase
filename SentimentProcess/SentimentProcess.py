import sys
import got3 as got
from textblob import TextBlob
import re
import pandas as pd
import TimeT
import json
import threading
import time
from langdetect import detect

def printTweet(descr, t):
	print(descr)
	print("Username: %s" % t.username)
	print("Retweets: %d" % t.retweets)
	print("Text: %s" % t.text)
	print("Mentions: %s" % t.mentions)
	print("Time: %s\n" % str(t.date))

class myThread (threading.Thread):
	def __init__(self,  name, start_date, end_date):
		threading.Thread.__init__(self)
		self.name = name
		self.start_date = start_date
		self.end_date = end_date

	def run(self):
		print("Starting Thread："+self.name)
		save_data(start_date=self.start_date, end_date=self.end_date)
		print("end Thread: "+self.name)

def clean_tweet(tweet):
	'''
    Utility function to clean tweet text by removing links, special characters
    using simple regex statements.
    '''
	return ' '.join(re.sub("(@[A-Za-z0-9]+)|([^0-9A-Za-z \t])| (\w+:\ / \ / \S+)", " ", tweet).split())

def get_tweet_sentiment(tweet):
	# create TextBlob object of passed tweet text
	analysis = TextBlob(clean_tweet(tweet))
	# set sentiment
	return analysis.sentiment.polarity


def get_tweets(query, count, since, until):
	'''
    Main function to fetch tweets and parse them.
    '''
	# empty list to store parsed tweets
	tweets = []
	while(len(tweets)<count):
		tweetss = None
		while(tweetss == None):
			try:
				tweetCriteria = got.manager.TweetCriteria().setQuerySearch(query).setSince(since).setUntil(until).setMaxTweets(count)
				tweetss = got.manager.TweetManager.getTweets(tweetCriteria)
			except Exception as e:
				pass
		for tweet in tweetss:
			try:
				if (detect(tweet.text) == 'en'):
					parsed_tweet = {}
					parsed_tweet['text'] = tweet.text
					parsed_tweet['time'] = tweet.date.strftime("%Y-%m-%d")
					parsed_tweet['sentiment'] = get_tweet_sentiment(tweet.text)
					if tweet.retweets > 0:
						if parsed_tweet not in tweets:
							tweets.append(parsed_tweet)
					else:
						tweets.append(parsed_tweet)
				else:
					pass
			except Exception as e:
				print("Empty Twitter text")

	print(str(len(tweets))+"Successful")
	return tweets

def get_twitter_sentiment(assets, start_date, end_date):
	startTS = TimeT.trans_to_timestamp(start_date)
	endTS = TimeT.trans_to_timestamp(end_date)
	dates = []
	while (startTS <= endTS):
		dates.append(TimeT.trans_to_Date(startTS))
		startTS = startTS + 86400

	column = {}
	rawdata = {}

	for asset in assets:
		date_sentiment_list = []
		date_raw_data = []
		for date in dates:
			tweets = get_tweets(query=asset, count=100, since='2015-01-01', until=date)
			file = open('raw.json', "a+")
			json.dump(tweets, file)
			file.close()
			sums = 0
			for tweetp in tweets:
				sums = sums + tweetp['sentiment']
			n = sums / len(tweets)
			date_sentiment_list.append(n)
			date_raw_data.append(tweets)
		column[asset] = date_sentiment_list
		rawdata[asset] = date_raw_data

	return pd.DataFrame(column, index=dates), rawdata

def save_data(start_date, end_date):
	assets = ['$ETH', '$LTC', '$XRP', '$ETC', '$DASH', '$XMR', '$XEM', '$FCT', '$GNT', '$ZEC', '$BTC']
	save1, save2 = get_twitter_sentiment(assets=assets, start_date=start_date, end_date=end_date)
	save1.to_csv(start_date + "--" + end_date + '.csv', index=True, sep=',')

	file = open(start_date + "--" + end_date + '-raw.json', "w+")
	json.dump(save2, file)
	file.close()

def main():
	start_date = sys.argv[1]
	end_date = sys.argv[2]
	startTS = TimeT.trans_to_timestamp(start_date)
	endTS = TimeT.trans_to_timestamp(end_date)
	period = int((endTS-startTS)/8)
	n = 0
	threads = []
	while (startTS < endTS):
		end = startTS + period
		thread = myThread(str(n), start_date=TimeT.trans_to_Date(startTS), end_date=TimeT.trans_to_Date(end))
		threads.append(thread)
		print(TimeT.trans_to_Date(end))
		startTS = startTS + period
		n = n+1

	for thread in threads:
		thread.start()
		time.sleep(2)

	for t in threads:
		t.join()


if __name__ == '__main__':
	main()
