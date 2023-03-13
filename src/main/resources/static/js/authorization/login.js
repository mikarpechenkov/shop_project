function mainFunction() {

    //Валидация пароля
    let inpPassword = document.getElementById("inputPassword");
    if (inpPassword != null) {
        inpPassword.addEventListener('blur', function () {
            let valueInp = inpPassword.value;
            let checkPassword = /\w{8,25}/.test(valueInp);
            if (checkPassword) {
                inpPassword.classList.remove('invalid');
                inpPassword.classList.add('valid');
            } else {
                inpPassword.classList.remove('valid');
                inpPassword.classList.add('invalid');
            }
        });
    }

    //Валдидация электронной почты
    let inpEmail = document.getElementById("inputEmail");
    if (inpEmail != null) {
        inpEmail.addEventListener('blur', function () {
            let valueInp = inpEmail.value;
            let checkEmail = /@/.test(valueInp);
            if (checkEmail) {
                inpEmail.classList.remove('invalid');
                inpEmail.classList.add('valid');
            } else {
                inpEmail.classList.remove('valid');
                inpEmail.classList.add('invalid');
            }
        });
    }


    //Валидация имени и фамилии
    let inpName = document.getElementById("inputName");
    let inpSurname = document.getElementById("inputSurname");
    if (inpSurname && inpName != null){
        let inpNameSur = [inpSurname, inpName];
        let regexNameSurname = new RegExp("[A-ZА-Я][a-zа-я]{1,30}");
        inpNameSur.forEach(function (el) {
            el.addEventListener('blur', function () {
                let valueEl = el.value;
                let checkNameSurname = regexNameSurname.test(valueEl);
                if (checkNameSurname) {
                    el.classList.remove('invalid');
                    el.classList.add('valid');
                } else {
                    el.classList.remove('valid');
                    el.classList.add('invalid');
                }
            });
        });
    }
}