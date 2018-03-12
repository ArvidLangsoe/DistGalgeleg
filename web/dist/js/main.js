const RESOURCES_URI = "http://localhost:8080/galgeleg/rest";
const GAME_RESOURCE_URI = "/game";
const HISTORY_RESOURCE_URI = "/history";
const AUTH_RESOURCE_URI = "/auth";
const TEMPLATES_URI = "dist/mustache/";
const COOKIE_NAME = "logged_in";

$(document).ready(function() {
    if(getCookie(COOKIE_NAME) == null)
        loadPage("login.mst", "#main", false);
    else
        loadPage("master.mst", "#main", false);


    $(document).on("click", "#nav-main a, #game_end_layout button", function(event) {
        event.preventDefault();

        $(this).addClass("active").parent().siblings().children().removeClass("active");

        var id = $(this).attr(("id"));
        switch(id) {
            case "link-game":
                loadPage("game.mst", "#content", true, GAME_RESOURCE_URI+"/"+getCookie(COOKIE_NAME)[0], "GET");
                break;
            case "link-history":
                loadPage("history.mst", "#content", true, HISTORY_RESOURCE_URI, "GET");
                break;
            case "link-rules":
                loadPage("rules.mst", "#content", false);
                break;
            case "link-admin":
                loadPage("admin.mst", "#content", false);
                break;
            case "link-logo":
                loadPage("start.mst", "#content", false);
                break;
            case "link-logout":
                loadPage("login.mst", "#main", true, AUTH_RESOURCE_URI+"/logout/"+getCookie(COOKIE_NAME)[0], "POST");
                setCookie(COOKIE_NAME, "", -1);
                break;
            case "link-play":
                loadPage("game.mst", "#content", true, GAME_RESOURCE_URI+"/"+getCookie(COOKIE_NAME)[0]+"?new=true", "POST");
            default:
                console.log("Andre")
        }
    });

    $(document).on("submit", "#game_layout form", function(event) {
        event.preventDefault();
        var formData = $(this).serializeArray();
        var letter = formData[0].value;
        loadPage("game.mst", "#content", true, GAME_RESOURCE_URI+"/"+getCookie(COOKIE_NAME)[0]+"?letter="+letter, "POST");
    });

    $(document).on("submit", "#login_layout form", function(event) {
        event.preventDefault();
        var formData = $(this).serializeArray();
        var playerId = formData[0].value;
        var password = formData[1].value;
        loadPage("master.mst", "#main", true, AUTH_RESOURCE_URI+"/login?playerId="+playerId+"&password="+password, "POST");
    });

});

function render(mustacheTemplate, htmlElement, restData) {
    $.get(TEMPLATES_URI+mustacheTemplate, function(template) {
        var rendered = Mustache.render(template, restData);
        $(htmlElement).html(rendered);
    }).fail(function(msg) {
        console.log("ERROR render: " + msg);
    });
}

function loadPage(template, view, withRest, uri, type) {
    var json;
    var $alert = $(".alert-danger");
    if(withRest) {
        rest(RESOURCES_URI+uri, type).done(function (data) {
            $alert.attr("hidden");

            try {
                json = JSON.parse(data);
            } catch (e) {
                json = data;
            }
            console.log(json);

            if(json.loggedIn) {
                setCookie(COOKIE_NAME, json.playerId+"+"+json.admin, 7);
            }
            if(json.gameHasEnded) {
                render("game_end.mst", view, json);
            }
            else {
                render(template, view, json);
            }
        }).fail(function (data) {
            //json = JSON.stringify(data);
            $alert.removeAttr("hidden").html(data.responseText);
            //console.log("ERROR REST:\n"+ json);
        });
    } else {
        var cookieObj = getCookie(COOKIE_NAME);
        if(cookieObj != null){
            var admin = (cookieObj[1] === "true");
            render(template, view, { playerId: cookieObj[0], admin: admin });}
        else {
            render(template, view);
        }
    }

}

function rest(url, type) {
    return $.ajax({
        url : url,
        type : type,
        dataType: "text"
    });
}

/*
* From w3schools.com: https://www.w3schools.com/js/js_cookies.asp
* */
function setCookie(cname, cvalue, exdays) {
    var date = new Date();
    date.setTime(date.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ date.toUTCString();
    document.cookie = cname+"="+cvalue+";"+expires;
}

/*
* From w3schools.com: https://www.w3schools.com/js/js_cookies.asp
* */
function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            var obj = c.substring(name.length, c.length).split("+");
            return obj;
        }
    }
    return null;
}