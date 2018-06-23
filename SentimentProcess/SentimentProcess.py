import sys
import got3 as got
from textblob import TextBlob
import re
import pandas as pd
import TimeT
import json
from langdetect import detect

def printTweet(descr, t):
	print(descr)
	print("Username: %s" % t.username)
	print("Retweets: %d" % t.retweets)
	print("Text: %s" % t.text)
	print("Mentions: %s" % t.mentions)
	print("Time: %s\n" % str(t.date))


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
		tweetCriteria = got.manager.TweetCriteria().setQuerySearch(query).setSince(since).setUntil(until).setMaxTweets(count)
		tweetss = got.manager.TweetManager.getTweets(tweetCriteria)
		for tweet in tweetss:
			if(detect(tweet.text) == 'en'):
				parsed_tweet = {}
				parsed_tweet['text'] = tweet.text
				parsed_tweet['time'] = tweet.date.strftime("%Y-%m-%d")
				parsed_tweet['sentiment'] = get_tweet_sentiment(tweet.text)
				if tweet.retweets > 0:
					if parsed_tweet not in tweets:
						tweets.append(parsed_tweet)
				else:
					tweets.append(parsed_tweet)
			else: pass
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
		file = open('raw.json', "a+")
		for date in dates:
			tweets = get_tweets(query=asset, count=100, since='2014-01-01', until=date)
			json.dump(tweets, file)
			sums = 0
			for tweetp in tweets:
				sums = sums + tweetp['sentiment']
			n = sums / len(tweets)
			date_sentiment_list.append(n)
			date_raw_data.append(tweets)
		column[asset] = date_sentiment_list
		rawdata[asset] = date_raw_data
		file.close()

	return pd.DataFrame(column, index=dates), rawdata

def main():
	'''
	tweetCriteria = got.manager.TweetCriteria().setQuerySearch('ethereum').setSince("2018-06-01").setUntil("2018-06-02").setMaxTweets(100)
	tweets = got.manager.TweetManager.getTweets(tweetCriteria)
	print(len(tweets))

	for tweet in tweets:
		printTweet("### Example 2 - Get tweets by query search [europe refugees]", tweet)
	'''
	assets = ['$ETH', '$LTC', '$XRP', '$ETC', '$DASH', '$XMR', '$XEM', '$FCT', '$GNT', '$ZEC', '$BTC']
	save1, save2 = get_twitter_sentiment(assets=assets, start_date=sys.argv[1], end_date=sys.argv[2])
	save1.to_csv(sys.argv[1]+"--"+sys.argv[2]+'.csv', index=True, sep=',')

	file = open(sys.argv[1]+"--"+sys.argv[2]+'-raw.json', "w+")
	json.dump(save2, file)
	file.close()

if __name__ == '__main__':
	main()
