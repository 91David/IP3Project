$(function () {
    var wrapper = $(".wrapper"),
        toggle = $(".toggle"),
        table = $("#mainTable"),
        nav = $(".side-nav");
    toggle.on("click", function () {
        wrapper.toggleClass("nav-open");
        table.toggleClass("noShadow");
        // Change the font-awesome icons on click.
        toggle.toggleClass("fa-bars");
        toggle.toggleClass("fa-times");
    });
});