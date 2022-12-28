function phoneMask() {
    var mask = "+38(0__)-___-__-__";
    var phoneNumber = document.getElementById("phone").value;
    var numbersInPhoneNumber = phoneNumber.toString().replace(/\D+/g, "");
    numbersInPhoneNumber = numbersInPhoneNumber.slice(0, 12);
    if (numbersInPhoneNumber.length == 1) {
        mask = mask.replace("_", numbersInPhoneNumber.charAt(0));
    }
    for (let i = 3; i < numbersInPhoneNumber.length; i++) {
        mask = mask.replace("_", numbersInPhoneNumber.charAt(i));
    }
    var cursorPosition = mask.search("_");
    document.getElementById("phone").value = mask;
    document.getElementById("phone").setSelectionRange(cursorPosition, cursorPosition);
}

function validateDate(element, DATE_REQUIRED) {
    const currentDate = new Date();
    const d = new Date(element.value);
    if (d instanceof Date && !isNaN(d) && d.getFullYear() > 1900 && d<currentDate) {
        return true;
    }
    return showError(element, DATE_REQUIRED);
}

function submitRegisterForm() {
    const form = document.getElementById("register");
    const EMAIL_INVALID = "Please enter a correct email address format";
    const EMAIL_REQUIRED = "Please enter your email";
    let emailValid = validateEmail(form.elements["email"], EMAIL_REQUIRED, EMAIL_INVALID);
    const PASS_REQUIRED = "Please enter your pass";
    let passValid = hasValue(form.elements["password"], PASS_REQUIRED);
    const SURNAME_REQUIRED = "Please enter your surname";
    let surnameValid = hasValue(form.elements["surname"], SURNAME_REQUIRED);
    const NAME_REQUIRED = "Please enter your name";
    let nameValid = hasValue(form.elements["name"], NAME_REQUIRED);
    const DATE_REQUIRED = "Please enter your Date";
    let dateValid = validateDate(form.elements["date"], DATE_REQUIRED);
    const NUMBER_REQUIRED = "Please enter your Number";
    let numberValid = validateNumber(form.elements["phoneNumber"], NUMBER_REQUIRED);
    if (emailValid && passValid && surnameValid && nameValid && dateValid && numberValid) {
        form.submit();
    }
}

function submitLoginForm() {
    const form = document.getElementById("signIn");
    const EMAIL_INVALID = "Please enter a correct email address format";
    const EMAIL_REQUIRED = "Please enter your email";
    let emailValid = validateEmail(form.elements["email"], EMAIL_REQUIRED, EMAIL_INVALID);
    const PASS_REQUIRED = "Please enter your pass";
    let passValid = hasValue(form.elements["password"], PASS_REQUIRED);
    if (emailValid && passValid) {
        form.submit();
    }
}

function showMessage(input, message, type) {
    const msg = input.parentNode.querySelector("small");
    msg.innerText = message;
    input.className = type ? "success" : "error";
    return type;
}

function showError(input, message) {
    return showMessage(input, message, false);
}

function showSuccess(input) {
    return showMessage(input, "", true);
}

function hasValue(input, message) {
    if (input.value.trim() === "") {
        return showError(input, message);
    }
    return showSuccess(input);
}

function validateEmail(input, requiredMsg, invalidMsg) {
    if (!hasValue(input, requiredMsg)) {
        return false;
    }
    const emailRegex =
        /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    const email = input.value.trim();
    if (!emailRegex.test(email)) {
        return showError(input, invalidMsg);
    }
    return true;
}

function validateNumber(input, requiredMsg) {
    if (!hasValue(input, requiredMsg)) {
        return false;
    }
    var phoneNumber = input.value;
    var numbersInPhoneNumber = phoneNumber.toString().replace(/\D+/g, "");
    if (numbersInPhoneNumber.length != 12) {
        return showError(input, requiredMsg);
    }
    return true;
}
