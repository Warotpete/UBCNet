/*
 * Function to toggle fields. Toggled by the post html dropdown. Will display each form
 * according to the value of the dropdown. Initially all fields are hidden
 */
function toggleFields() {
    const postType = document.getElementById("postType").value;

    // Hide all fields initially
    document.getElementById("announcement-fields").style.display = "none";
    document.getElementById("shop-fields").style.display = "none";
    document.getElementById("housing-fields").style.display = "none";

    // Show fields based on selected post type
    if (postType === "announcement") {
        document.getElementById("announcement-fields").style.display = "block";
    } else if (postType === "shop") {
        document.getElementById("shop-fields").style.display = "block";
    } else if (postType === "housing") {
        document.getElementById("housing-fields").style.display = "block";
    }
}

/*
 * Function to handle the submission of the post form from the announcement form
 * will retrieve the form data and send it to the controller to update the json file
 *
 * @param event The event object representing the form submission
 */
function handleSubmit(event) {
    console.log(document.getElementById("announcement-title").value);

    const object = {
        postType: document.getElementById("postType").value,
        title: document.getElementById("announcement-title").value,
        message: document.getElementById("announcement-message").value,
     };

    // Validate that all fields have values
    for (const key in object) {
        if (object.hasOwnProperty(key) && !object[key].trim()) {
            showPopup(`The field "${key}" is required.`);
            return; // Stop the function if a field is empty
        }
    }

    // Store the input fields in a FormData object
    const formData = new FormData();
    for (const key in object) {
        if (object.hasOwnProperty(key)) {
            formData.append(key, object[key]);
        }
    }

    // Send the form data to the with fetch method which will send it to the Post controller
    fetch('/create-post', {
            method: 'POST',
            body: formData
        }).then(response => {
            //Display message based on response
            if (response.ok) {
                showPopup("Post successful!", "/announcements");
            } else {
                showPopup("Failed to create post.");
            }
        }).catch(error => {
            console.error(error);
            showPopup("An error occurred while creating the post.");
        });
}

/*
 * Function to handle the submission of the post form from the shop form
 * will retrieve the form data and send it to the controller to update the json file
 *
 * @param event The event object representing the form submission
 */
function handleSubmit2(event) {
    console.log(document.getElementById("shop-title").value);


    const object = {
        postType: document.getElementById("postType").value,
        title: document.getElementById("shop-title").value,
        images: document.getElementById("shop-images").value,
        description: document.getElementById("shop-description").value,
        price: document.getElementById("shop-price").value,
        condition: document.getElementById("shop-condition").value,
        email: document.getElementById("shop-email").value
     };

// Validate that all fields (except files) have values
    for (const key in object) {
        if (object.hasOwnProperty(key)) {
            if (key === "images" && object[key].length === 0) {
                showPopup(`The field "images" is required.`);
                return; // Stop the function if a field is empty
            } else if (key !== "images" && !object[key].trim()) {
                showPopup(`The field "${key}" is required.`);
                return; // Stop the function if a field is empty
            }
        }
    }

    // Store the input fields in a FormData object
    const formData = new FormData();
    for (const key in object) {
        if (object.hasOwnProperty(key)) {
            formData.append(key, object[key]);
        }
    }

    // Send the form data to the with fetch method which will send it to the Post controller
    fetch('/create-post', {
        method: 'POST',
        body: formData
    }).then(response => {
        //Display message based on response
        if (response.ok) {
        showPopup("Post successful!", "/shop");
        } else {
            showPopup("Failed to create post. The Image URL is invalid");
            }
        }).catch(error => {
            console.error(error);
            showPopup("An error occurred while creating the post.");
        });
}

/*
 * Function to handle the submission of the post form from the housing form
 * will retrieve the form data and send it to the controller to update the json file
 *
 * @param event The event object representing the form submission
 */
function handleSubmit3(event) {
    console.log(document.getElementById("housing-title").value);

    const object = {
        postType: document.getElementById("postType").value,
        title: document.getElementById("housing-title").value,
        images: document.getElementById("housing-images").value,
        description: document.getElementById("housing-description").value,
        rentPerMonth: document.getElementById("housing-price").value,
        location: document.getElementById("housing-location").value,
        email: document.getElementById("housing-email").value
     };

// Validate that all fields (except files) have values
    for (const key in object) {
        if (object.hasOwnProperty(key)) {
            if (key === "images" && object[key].length === 0) {
                showPopup(`The field "images" is required.`);
                return; // Stop the function if a field is empty
            } else if (key !== "images" && !object[key].trim()) {
                showPopup(`The field "${key}" is required.`);
                return; // Stop the function if a field is empty
            }
        }
    }

    // Store the input fields in a FormData object
    const formData = new FormData();
    for (const key in object) {
        if (object.hasOwnProperty(key)) {
            formData.append(key, object[key]);
        }
    }

    // Send the form data to the with fetch method which will send it to the Post controller
    fetch('/create-post', {
            method: 'POST',
            body: formData
        }).then(response => {
            //Display message based on response
            if (response.ok) {
            showPopup("Post successful!", "/housing");
            } else {
                showPopup("Failed to create post. The Image URL is invalid");
                }
            }).catch(error => {
                console.error(error);
                showPopup("An error occurred while creating the post.");
            });
}

/*
 * Function to handle the pop up meaage display. Will display a message and redirect to a page
 * if a redirect url is provided the popup will redirect to that page after 2 seconds
 *
 * @param message The message to display
 * @param redirectUrl The html page to redirect to, defaults to null
 */
function showPopup(message, redirectUrl = null) {
    // Create or select the popup container
    let popup = document.getElementById("popup-message");
    if (!popup) {
        popup = document.createElement("div");
        popup.id = "popup-message";
        popup.style.position = "fixed";
        popup.style.top = "20px";
        popup.style.left = "50%";
        popup.style.transform = "translateX(-50%)";
        popup.style.color = "white";
        popup.style.padding = "10px 20px";
        popup.style.borderRadius = "5px";
        popup.style.fontSize = "16px";
        popup.style.boxShadow = "0px 4px 6px rgba(0, 0, 0, 0.2)";
        popup.style.zIndex = "1000";
        popup.style.display = "none";
        document.body.appendChild(popup);
    }

    // Set background color based on the message
    if (message === "Post successful!") {
        popup.style.backgroundColor = "#4CAF50"; // Green for success
    } else {
        popup.style.backgroundColor = "#FF4C4C"; // Default to red for errors
    }

    // Show the popup with the message
    popup.textContent = message;
    popup.style.display = "block";

    // Hide the popup after 2 seconds
    setTimeout(() => {
        popup.style.display = "none";
        if (redirectUrl) {
            window.location.href = redirectUrl;
        }
    }, 2000);
}