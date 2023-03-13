// Для валидации формы регистрации (через name можно получать объекты и тогда обобщить код и для авторизации, после 7 лабы не забыть
const form = document.getElementById("registrationForm");
const emailInput = document.getElementById("emailInput");
const passwordInput = document.getElementById("passwordInput");
const nameInput = document.getElementById("nameInput");
const surnameInput = document.getElementById("surnameInput");
const inputsNameAndSurname = [nameInput, surnameInput];

const regexEmail = new RegExp("@");
const regexPassword = new RegExp("\\w{8,25}");
const regexNameSurname = new RegExp("[A-ZА-Я][a-zа-я]{1,30}");

if (emailInput != null)
    emailInput.addEventListener("input", function () {
        if (regexEmail.test(emailInput.value)) {
            if (emailInput.classList.contains('is-invalid'))
                emailInput.classList.remove('is-invalid');
            emailInput.classList.add('is-valid');
        } else {
            if (emailInput.classList.contains('is-valid'))
                emailInput.classList.remove('is-valid');
            emailInput.classList.add('is-invalid');
        }
    });

if (passwordInput != null)
    passwordInput.addEventListener("input", function () {
        if (regexPassword.test(passwordInput.value)) {
            if (passwordInput.classList.contains('is-invalid'))
                passwordInput.classList.remove('is-invalid');
            passwordInput.classList.add('is-valid');
        } else {
            if (passwordInput.classList.contains('is-valid'))
                passwordInput.classList.remove('is-valid');
            passwordInput.classList.add('is-invalid');
        }
    });

inputsNameAndSurname.forEach(function (el) {
    if (el != null)
        el.addEventListener('input', function () {
            if (regexNameSurname.test(el.value)) {
                if (el.classList.contains('is-invalid'))
                    el.classList.remove('is-invalid');
                el.classList.add('is-valid');
            } else {
                if (el.classList.contains('is-valid'))
                    el.classList.remove('is-valid');
                el.classList.add('is-invalid');
            }
        });
});

if (form != null) {
    form.addEventListener('submit', function (event) {
        let formIsValid =
            nameInput.classList.contains('is-valid') &&
            surnameInput.classList.contains('is-valid') &&
            emailInput.classList.contains('is-valid') &&
            passwordInput.classList.contains('is-valid');

        console.log(formIsValid);
        if (formIsValid) {
            if (form.classList.contains('needs-validation'))
                form.classList.remove('needs-validation');
            form.classList.add('was-validation');
        } else {
            event.preventDefault();
            event.stopPropagation();
            if (form.classList.contains('was-validated'))
                form.classList.remove('was-validated');
            form.classList.add('needs-validation');
        }
    });
}