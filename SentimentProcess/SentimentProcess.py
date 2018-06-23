import re
import tweepy
import TimeT
from tweepy import OAuthHandler
from textblob import TextBlob
import pandas as pd


class TwitterClient(object):
    '''
    Generic Twitter Class for sentiment analysis.
    '''

    def __init__(self):
        '''
        Class constructor or initialization method.
        '''
        # keys and tokens from the Twitter Dev Console
        consumer_key = 'eFYfSvT05fejti8wBQDXELjMH'
        consumer_secret = 'dO5q1CVGvnGjvbsZ2rGEfEici2zj3P8vtqJsL2B2kguQGviOZZ'
        access_token = '1010079466511478784-BKibdGczsxMJ73xJAHfkAugNiNhjaT'
        access_token_secret = 'W8euzQSLYZfFElt1CRqNMYxXpD4Un39MlKIAtIwMdN21O'

        # attempt authentication
        try:
            # create OAuthHandler object
            self.auth = OAuthHandler(consumer_key, consumer_secret)
            # set access token and secret
            self.auth.set_access_token(access_token, access_token_secret)
            # create tweepy API object to fetch tweets
            self.api = tweepy.API(self.auth, wait_on_rate_limit=True, wait_on_rate_limit_notify=True)
        except:
            print("Error: Authentication Failed")

    def clean_tweet(self, tweet):
        '''
        Utility function to clean tweet text by removing links, special characters
        using simple regex statements.
        '''
        return ' '.join(re.sub("(@[A-Za-z0-9]+)|([^0-9A-Za-z \t])| (\w+:\ / \ / \S+)", " ", tweet).split())

    def get_tweet_sentiment(self, tweet):
        '''
        Utility function to classify sentiment of passed tweet
        using textblob's sentiment method
        '''
        # create TextBlob object of passed tweet text
        analysis = TextBlob(self.clean_tweet(tweet))
        # set sentiment
        return analysis.sentiment.polarity

    def get_tweets(self, query, count, until):
        '''
        Main function to fetch tweets and parse them.
        '''
        # empty list to store parsed tweets
        tweets = []
        try:
            # parsing tweets one by one
            for tweet in tweepy.Cursor(self.api.search, q=query, until=until, lang="en").items(count):
                # empty dictionary to store required params of a tweet
                parsed_tweet = {}

                # saving text of tweet
                parsed_tweet['text'] = tweet.text
                # saving sentiment of tweet
                # parsed_tweet['time'] = tweet.created_at
                parsed_tweet['sentiment'] = self.get_tweet_sentiment(tweet.text)

                # appending parsed tweet to tweets list
                if tweet.retweet_count > 0:
                    # if tweet has retweets, ensure that it is appended only once
                    if parsed_tweet not in tweets:
                        tweets.append(parsed_tweet)
                else:
                    tweets.append(parsed_tweet)

            # return parsed tweets
            return tweets

        except tweepy.TweepError as e:
            # print error (if any)
            print("Error : " + str(e))


def get_twitter_sentiment(assets, start_date, end_date):

    api = TwitterClient()

    startTS = TimeT.trans_to_timestamp(start_date)
    endTS = TimeT.trans_to_timestamp(end_date)
    dates = []
    while(startTS<=endTS):
        dates.append(TimeT.trans_to_Date(startTS))
        startTS = startTS+86400

    column = {}

    for asset in assets:
        date_sentiment_list = []
        for date in dates:
            tweets = api.get_tweets(query=asset, count=500, until=date)
            sums = 0
            for tweetp in tweets:
                sums = sums + tweetp['sentiment']
            n = sums / len(tweets)
            date_sentiment_list.append(n)
        column[asset] = date_sentiment_list

    return pd.DataFrame(column, index=dates)


def main():

    # assets = ['Litecoin', 'EOSIO', 'Ethereum', 'Bitcoin']
    assets = ['$LTC', '$EOS', '$ETH', '$BTC']
    save = get_twitter_sentiment(assets=assets, start_date='2018-06-11', end_date='2018-06-21')
    save.to_csv('test.csv', index=True, sep=',')



if __name__ == "__main__":
    # calling main function
    main()
