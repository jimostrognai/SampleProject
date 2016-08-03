/**
 * Created by brianschultz on 11/22/15.
 */
function viewModel() {

    var self = this;

    self.uri = 'http://localhost:5000/'
    self.users = ko.observableArray();
    self.healthCheck = ko.observable();
    self.user = ko.observable();
    self.userNameInput = ko.observable();
    self.roleInput = ko.observable();


    //ajax call to load a user into the user observable. sets the photo location based off the userName/role
    getUser = function (userName) {
        $.ajax({
            type: 'GET',
            url: self.uri + 'users/' + userName,
            dataType: "json",
            success: function(data) {
                var updatedData = setPhotoLocation(data);
                self.user(updatedData);
            },
            error:function(jq, st, error){
                alert(error);
            }
        });
    };

    //ajax call to delete a user from the database and reload the screen so user is no longer seen in UI
    deleteUser = function(userName) {
        $.ajax({
            type: 'DELETE',
            url: self.uri + 'users/' + userName,
            success: function() {
                console.log("user " + userName + " deleted");
                self.reload();
            },
            error: function(jp, st, error){
                alert(error);
            }
        });
    };

    //setting logic for picture to display
    setPhotoLocation = function (userData){
        if (userData.userName.toLowerCase().indexOf("koontz") > -1){
            userData.pictureLocation = "images/koontz.jpg";
        } else if (userData.role == "Manager"){
            userData.pictureLocation = "images/phb.png";
        } else {
            userData.pictureLocation = "images/dilbert.jpeg"
        }
        return userData;
    }



    //loads all of the user data into a container for use. called in getUsers
    function loadUserObject(userObject){
        var self = this;
        self.data = userObject;
        self.clickDetails = function (){
            console.log("getting details for user: " + userObject.userName);
            getUser(userObject.userName);
            $('#detailsModal').modal('show');
        };
        self.deleteIndUser = function(){
            console.log("deleting a specific user: " + userObject.userName);
            deleteUser(userObject.userName);
        }

    }


    //retrieves users from the database
    self.getUsers = function () {
        $.ajax({
            type: 'GET',
            url: self.uri + 'users',
            dataType: "json",
            success: function(data) {
                var userArray = data.users;
                for (var i = 0; i < userArray.length; i++) {
                    self.users.push(
                        new loadUserObject(userArray[i])
                    )
                }
            },
            error:function(jq, st, error){
                alert(error);
            }
        });


    };

    //ajax call to health check api
    self.getHealthCheck = function () {
        $.ajax({
            type: 'GET',
            url: self.uri + 'health',
            dataType: "json",
            success: function(data) {
                console.log(JSON.stringify(data));
                self.healthCheck({service: data.service, version: data.version})
            },
            error:function(jq, st, error){
                alert(error);
            }
        });
    };


    //instantiates the posts() object upon page load
    self.getUsers();

    self.displayUsers =function(){
        console.log("Returning updated user array to ui")
        return self.users();
    };

    //monitors the healthcheck observable and returns it to the ui
    self.displayHealthCheck = ko.computed(function(){
        console.log("Returning updated healthCheck to ui");
        return self.healthCheck();
    });


    //opens up the health check modal
    self.displayHealthCheckModal = function () {
        console.log("Displaying healthCheck modal")
        self.getHealthCheck();
        $('#aboutModal').modal('show');
        return true;
    };

    //called by the reload button on the ui.  Wipes out the array and reloads it.
    self.reload = function () {
        console.log("reloading users")
        self.users([])
        self.getUsers();
        console.log(self.users())
    };

    //resets userArray to default value when user loads page. Page reloaded after reset.
    self.postReset = function () {
        $.ajax({
            type: 'POST',
            url: self.uri + 'reset',
            success: function(data) {
                console.log(JSON.stringify(data));
            },
            error:function(jq, st, error){
                alert(error);
            }
        });

        self.reload();
    };

    //monitors user details observable and returns them to the ui
    self.displayDetails = ko.computed(function(){
        console.log("Returning updated user details to ui");
        console.log(JSON.stringify(self.user()));
        return self.user();
    });

    //opens the create user modal
    self.displayCreateUserModal = function () {
        console.log("Displaying create user modal");
        $('#add-user-modal-input').val('');
        self.userNameInput('');
        self.roleInput('');
        $('#add-user-modal').modal('show');
        return true;
    };

    //ajax call to post to users api
    self.postUser = function() {
        if(self.userNameInput != null && self.roleInput != null && self.userNameInput() != "" && self.roleInput() != "") {

            var User = {};
            User.userName = self.userNameInput();
            User.role = self.roleInput();
            $.ajax({
                type: 'POST',
                url: self.uri + 'users',
                headers: {
                    "Content-Type": "application/json"
                },
                dataType: "json",
                data: JSON.stringify(User),
                success: function () {
                    console.log("New user created successfully");
                    self.reload();
                    $('#add-user-modal').modal('toggle');
                },
                error: function (jq, st, error) {
                    alert(jq.responseText);
                }
            });
        }
        else {
            alert("Username or role not set")
        }

    }

    //adding tooltips dynamically via bootstrap & jquery.  gets applied to all buttons that have attributes:
    //data-toggle="tooltip" data-original-title="Tooltip text here"
    $('[data-toggle="tooltip"]').tooltip({
        trigger : 'hover'
    });  

}

//loads the viewModel as soon as the page is ready
$(document).ready(function () {
    console.log("Loading the viewmodel")
    ko.applyBindings(new viewModel());
});