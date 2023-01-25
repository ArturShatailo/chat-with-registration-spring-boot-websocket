'use strict'

async function handleRegistrationFormSubmit(event) {

    event.preventDefault();

    let form = event.currentTarget;
    let url = form.action;

    await doRegistrationRequest(form, url);
}

async function doRegistrationRequest(form, url) {

    let notification;
    let formData = new FormData(form);
    let plainFormData = Object.fromEntries(formData.entries());
    let formDataJsonString = JSON.stringify(plainFormData);

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

    } else {
        let response = res.json()
        response.then((data) => {
            notification = createNotification(data.message, "fail-notification")
            addNotification(notification);
        })
    }
}

const connectForm = document.querySelector('#registration-form')
connectForm.addEventListener('submit', handleRegistrationFormSubmit, true)