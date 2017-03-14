var modal = document.getElementById('dummy');    //Enter the id of the modal div
var btn = document.getElementById("dumBtn");     //Enter the id of the button to call it
var span = document.getElementsByClassName("close")[0];

btn.onclick = function () {
    modal.style.display = "block";
}
span.onclick = function () {
    modal.style.display = "none";
}
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
