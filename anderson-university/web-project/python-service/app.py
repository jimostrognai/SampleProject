from flask import Flask, request, jsonify
from flask.ext.cors import CORS
import json

__author__ = 'brianschultz'
version = "1.0"
name = "Anderson Internship Test Service"
DEBUG = True

app = Flask(__name__)
app.config.from_object(__name__)
CORS(app)

dict1 = {'userName': 'BrianSchultz', 'role': 'Manager'}
dict2 = {'userName': 'JoshFrederick', 'role': 'Manager'}
userArray = [dict1, dict2]


# health check. returns simple info and confirms that service is up
@app.route('/health', methods=["GET"])
def gethealthcheck():
    return getprettyprintedjson({"service": name, "version": version})


# GET or POST for users endpoint.  This is only relevant to the current run of the service and will be reset
@app.route('/users', methods=["GET", "POST"])
def usersendpoint():
    if request.method == "GET":
        return getprettyprintedjson({'users': userArray})
    else:  # post
        try:
            header = request.headers.get("Content-Type")
            header = str(header)
            content = json.loads(request.get_data())
            if not header or ('application/json' not in header):
                return getprettyprintedjson({'info': 'invalid header. expected Content-Type: application/json'}), 400
            else:
                if len(content) < 2:
                    return getprettyprintedjson({'info': 'required two arguments: userName & role.'}), 400
                if 'userName' not in content:
                    return getprettyprintedjson({'info': 'userName argument is empty or not set'}), 400
                if 'role' not in content:
                    return getprettyprintedjson({'info': 'role argument is empty or not set'}), 400
                if ' ' in content['userName']:
                    return getprettyprintedjson({'info': 'No spaces allowed in userName'}), 400
                if len(content['userName']) < 1:
                    return getprettyprintedjson({'info': 'No value for userName'}), 400
                if len(content['role']) < 1:
                    return getprettyprintedjson({'info': 'No value for role'}), 400
                for u in userArray:
                    if u['userName'].lower() == content['userName'].lower():
                        return getprettyprintedjson({'info': 'requested userName '+ content['userName'] +' is already in use'}), 409
                userArray.append({'userName': content['userName'], 'role': content['role']})
                return getprettyprintedjson({'info': 'list updated'}), 200
        except:
            return getprettyprintedjson({'info': 'invalid syntax.'}), 400

# resets userArray to default value when user loads page.
@app.route('/reset', methods=["POST"])
def resetdata():
    global userArray
    userArray = [dict1, dict2]

    return getprettyprintedjson({'info': 'reset was successful'}), 200

# GET, DELETE a specific user, passing in username parameter
@app.route('/users/<username>',  methods=["GET", "DELETE"])
def userusernameendpoints(username):
    if request.method == "GET":
        if not username:
            return getprettyprintedjson({'info': 'userName argument is empty or not set'}), 400
        for dictionary in userArray:
            if dictionary.get('userName') == username:
                return getprettyprintedjson(dictionary)
        return getprettyprintedjson({'info': username + ' not found.'}), 404
    else:  # Delete
        if not username:
            return getprettyprintedjson({'info': 'username argument is not empty or not set'}), 400
        for u in userArray:
            if u['userName'].lower() == username.lower():
                userArray.remove(u)
                return getprettyprintedjson({'info': username + ' user deleted'}), 200
        return getprettyprintedjson({'info': username + ' user not found'}), 404


# prints out the json in a pretty way for postman or any other ui client
def getprettyprintedjson(jsonobject):
    return jsonify(jsonobject)


# Runs this as a service
if __name__ == '__main__':
    app.run(host="0.0.0.0", threaded=True)
