import py_eureka_client.eureka_client as eureka_client
from flask import Flask, request
from flask import jsonify

from parser_news_methods import *

rest_port = 8050

eureka_client.init(eureka_server="http://localhost:8761/eureka",
                   app_name="parser-service",
                   instance_port=rest_port,
                   instance_host='127.0.0.1')

app = Flask(__name__)


@app.route("/parse/actual", methods=['POST'])
def parse_actual_news():
    actual_news = actual(country="UA", period="12h", max_results=100)
    actual_news_response = jsonify(
        actualNews=actual_news
    )
    return actual_news_response

@app.route("/parse/query", methods=['POST'])
def parse_actual_news_by_query():
    data = request.json
    query = data["query"]
    period = data["period"]
    actual_news_by_query = by_qyery(query=query, country="UA", period=period, max_results=100)
    actual_news_response = jsonify(
        actualNews=actual_news_by_query
    )
    return actual_news_response

@app.route("/parse/topic", methods=['POST'])
def parse_actual_news_by_topic():
    data = request.json
    topic_list = data["topicList"]
    period = data["period"]
    news_by_topic = parse_by_topic(topics=topic_list, country="UA", period=period, max_results=100)
    news_topics_response = jsonify(
        topicNews=news_by_topic
    )
    return news_topics_response

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=rest_port)