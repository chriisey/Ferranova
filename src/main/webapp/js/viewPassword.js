
function togglePassword() {
    var passwordField = document.getElementById("password");
    var viewPasswordButton = document.getElementById("mostraPassword").querySelector("i");
    if (passwordField.type === "password") {
    passwordField.type = "text";
    viewPasswordButton.className = "fa-regular fa-eye-slash";
    } else {
    passwordField.type = "password";
    viewPasswordButton.className = "fa-regular fa-eye";
    }
}
