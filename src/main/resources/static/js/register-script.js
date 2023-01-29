'use strict'

const passwordMatch = (plainFormData) => {
    return plainFormData.password === plainFormData.confirm_password
}

function failedFieldStyleHandler(field) {
    $("#" + field).addClass("redBorderFieldError");
}

async function handleRegistrationFormSubmit(event) {

    event.preventDefault();

    let form = event.currentTarget;
    let url = form.action;
    let formData = new FormData(form);
    let plainFormData = Object.fromEntries(formData.entries());

    $(".form-field").removeClass("redBorderFieldError");

    if (passwordMatch(plainFormData)) {
        let formDataJsonString = JSON.stringify(plainFormData);
        await doRegistrationRequest(form, url, formDataJsonString);
    } else {
        let notification = createNotification("Password match is failed", "fail-notification")
        addNotification(notification);
        failedFieldStyleHandler("confirm_password");
    }
}

async function doRegistrationRequest(form, url, formDataJsonString) {

    let notification;

    let fetchOptions = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
        },
        body: formDataJsonString,
    };

    let res = await fetch(url, fetchOptions);

    if (res.ok) {
        notification = createNotification("Thank you. Your email is waiting for confirmation", "success-notification")
        addNotification(notification);
        $(form)[0].reset();
    } else {
        let response = res.json()
        response.then((data) => {
            notification = createNotification(data.message, "fail-notification")
            addNotification(notification);
            if (data.field) failedFieldStyleHandler(data.field)
        })
    }
}

const connectForm = document.querySelector('#registration-form')
connectForm.addEventListener('submit', handleRegistrationFormSubmit, true)