const RESOURCE_URI = "http://localhost:8080/dist_forprojekt3_war_exploded/rest/";
const TEMPLATES_URI = "dist/mustache/";

$(document).ready(function() {
    loadMaster();
    //gamePage();

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
});

function loadMaster() {
    renderLoad("master.mst", "#main", { name: "Jeppe", role: "Admin" });
    ajaxRest(RESOURCE_URI+"word/visible", "GET").done(function(data) {
        renderLoad("game.mst", "#content", { word: data });
    });
}

function renderLoad(mustacheTemplate, htmlElement, restData) {
    $.get(TEMPLATES_URI+mustacheTemplate, function(template) {
        var rendered = Mustache.render(template, restData);
        $(htmlElement).html(rendered);
    }).fail(function(msg) {
        console.log("ERROR: " + msg);
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

function ajaxRest(url, type, data) {
    return $.ajax({
        url : url,
        type : type,
        dataType: "text",
        data: data
    });
}