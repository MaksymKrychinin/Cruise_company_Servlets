function showForm(elementId) {
    document.getElementById('signIn').parentElement.classList.remove('show');
    document.getElementById('register').parentElement.classList.remove('show');
    document.getElementById(elementId).parentElement.classList.add('show');
}

function closeForm(elementId) {
    document.getElementById(elementId).parentElement.classList.remove('show');
}
function openSlideBar() {
    document.getElementById("slideBarMenu").classList.add('slideBarMenuOpen');
}

function closeSlideBar() {
    document.getElementById("slideBarMenu").classList.remove('slideBarMenuOpen');
}