$(function () {
    var wrapper = $(".wrapper"),
        toggle = $(".toggle"),
        nav = $(".side-nav");
    toggle.on("click", function () {
        wrapper.toggleClass("nav-open");
        // Change the font-awesome icons on click.
        toggle.toggleClass("fa-bars");
        toggle.toggleClass("fa-times");
    });

    $(window).on("click", function (e) {
        if (wrapper.hasClass("nav-open") &&
            !$(e.target).hasClass("toggle")) {
            //wrapper.removeClass("nav-open");
            //toggle.toggleClass("fa-bars");
            //toggle.toggleClass("fa-times");
        }
    });
});