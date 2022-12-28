function dateMinSetter() {
    var allDateClassSelectors = document.getElementsByClassName("date");
    for (let i = 0; i < allDateClassSelectors.length; i++) {
        allDateClassSelectors.item(i).setAttribute("min", dateNow());
    }
}
function dateNow(){
    var date = new Date();
    return date.toLocaleDateString();
}