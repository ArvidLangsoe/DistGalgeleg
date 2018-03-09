const RESOURCES_URI = "http://localhost:8080/galgeleg/rest";
const GAME_RESOURCE = "/game";
const DATA_RESOURCE = "/data";
const TEMPLATES_URI = "dist/mustache/";
var user = "x";
var gameActive = false;

$(document).ready(function() {
    loadMaster();

    $(document).on("click", "#nav-main a", function(event) {
        event.preventDefault();
        //console.log($(this).parent().siblings().children());
        $(this).addClass("active").parent().siblings().children().removeClass("active");

        var id = $(this).attr(("id"));
        switch(id) {
            case "link-game":
                loadGame();
                break;
            case "link-data":
                console.log("Data");
                break;
            case "link-rules":
                console.log("Regler");
                break;
            default:
                console.log("Andre")
        }
    });


});

function render(mustacheTemplate, htmlElement, restData) {
    $.get(TEMPLATES_URI+mustacheTemplate, function(template) {
        var rendered = Mustache.render(template, restData);
        $(htmlElement).html(rendered);
    }).fail(function(msg) {
        console.log("ERROR: " + msg);
    });
}

function loadMaster() {
    render("master.mst", "#main", { name: "Jeppe", role: "Admin" });
    /*
    ajaxRest(RESOURCE_URI+"word/visible", "GET").done(function(data) {
        render("game.mst", "#content", { word: data });
    });
    */
}

function loadGame() {
    if(!gameActive) {
        ajaxRest(RESOURCES_URI+GAME_RESOURCE+"/"+user+"?new=true", "POST").done(function (data) {
            console.log(data);
            //render("game.mst", "#content", {"word": "***", "numWrongLetters": 5});
            render("game.mst", "#content", JSON.parse(data));
        }).fail(function (data) {
            console.log(data);
        });
    }

    gameActive = true;
}

function ajaxRest(url, type, data) {
    return $.ajax({
        url : url,
        type : type,
        dataType: "text",
        data: data
    });
}

/*
$(document).on("submit", "#guess_letter_form", function(event) {
        event.preventDefault();
        var formData = $(this).serializeArray();

        ajaxRest(RESOURCE_URI+"word/guess", "POST", formData[0].value).done(function (data) {
            $("#word").text(data);
        }).fail(function(data) {
            console.log("ERROR in guess letter form")
        });

        // reset form
        $(this)[0].reset();
    });

function loadMaster() {
    render("master.mst", "#main", { name: "Jeppe", role: "Admin" });
    ajaxRest(RESOURCE_URI+"word/visible", "GET").done(function(data) {
        render("game.mst", "#content", { word: data });
    });
}

function gamePage() {
    ajaxRest(RESOURCE_URI+"word/visible", "GET").done(function (data) {
        $.get(TEMPLATES_URI+"game.mst", function(template) {
            var rendered = Mustache.render(template, {word: data});
            $("#main").html(rendered);
        }).fail(function(data) {

        });
    });
}
*/