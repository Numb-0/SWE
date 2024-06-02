// Jquery Script
// This script is used to handle the form submission of the add book form
// Without this script, the form submission would cause a page reload
// Using ajax we can capture the requests to the server and hadle using only js 

// Setting Ajax requests token
$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

// Add Reservation form submission
$(document).ready(function() {
    $('#reservation-add-form').on('submit', function(event) {
        event.preventDefault();

        // This gets the attribute of the form action
        var url = $(this).attr('action');
        var formData = $(this).serialize();
        console.log(formData);

        $.ajax({
            type: 'POST',
            url: url,
            data: formData,
            success: function(response) {
                // Close modal
                $('#reservation-add-close-btn').click();
                // Reset form on success submission
                $('#reservation-add-form').trigger('reset');
                // Refresh the page
                location.reload();
            },
            error: function(error) {
                // handle error
                console.log(error);
            }
        });
    });
});

/* // Filter books form submission
$(document).ready(function() {
    $('#reservation-filter-form').on('submit', function(event) {
        event.preventDefault();

        // This gets the attribute of the form action
        var url = $(this).attr('action');
        var formData = $(this).serialize();
        console.log(formData);

        $.ajax({
            type: 'POST',
            url: url,
            data: formData,
            success: function(response) {
                // Close modal
                $('#reservation-filter-close-btn').click();
                // Reset form on success submission
                //$('#reservation-filter-form').trigger('reset');
                // Refresh the page
                location.reload();
            },
            error: function(error) {
                // handle error
                console.log(error);
            }
        });
    });
}); */

// Add book form submission
$(document).ready(function() {
    $('#book-add-form').on('submit', function(event) {
        event.preventDefault();
        var formData = $(this).serialize();
        var url = $(this).attr('action');
        $.ajax({
            type: 'POST',
            url: url,
            data: formData,
            success: function(response) {
                // Handle success
                // Close modal
                $('#book-add-close-btn').click();
                // Reset form on success submission
                $('#book-add-form').trigger('reset');
                // Refresh the page on success
                location.reload();
            },
            error: function(response) {
                // Handle error
                console.log(response);
            }
        });
    });
});

// Edit book form submission
$(document).ready(function() {
    $('#book-edit-form').on('submit', function(e) {
        e.preventDefault();

        // This gets the attribute of the form action
        var url = $(this).attr('action');
        var formData = $(this).serialize();
        console.log(formData);

        $.ajax({
            type: 'POST',
            url: url,
            data: formData,
            success: function(response) {
                // Close modal
                $('#book-edit-close-btn').click();
                // Reset form on success submission
                $('#book-edit-form').trigger('reset');
                // Refresh the page
                location.reload();
            },
            error: function(error) {
                // handle error
                console.log(error);
                // This makes the input form become red
                Array.from(document.getElementsByClassName("book-edit-input")).forEach(input => {
                    input.classList.add("is-invalid");
                })
            }
        });
    });
});

Array.from(document.getElementsByClassName("delete-button")).forEach(element => {
    element.addEventListener('click', function(event) {
        event.preventDefault();
        var input = this.parentElement.querySelector('.item-input');
        var id = input.value;

        $.ajax({
            type: 'DELETE',
            url: '/dashboard-book-delete/' + id,
            success: function(response) {
                // Refresh the page
                location.reload();
            },
            error: function(response) {
                // handle error
                console.log(response);
            }
        });
    });
});


// Javascript code to change the action(link) attribute of the edit form 
// During runtime!!!
Array.from(document.getElementsByClassName("edit-button")).forEach(element => {
    element.addEventListener('click', function(event) {
        var input = this.parentElement.querySelector('.item-input');
        var id = input.value;
        document.getElementById('book-edit-form').action = '/dashboard-book-edit/' + id;
    });
});


document.getElementById('book-edit-modal').addEventListener('hidden.bs.modal', function (event) {
    // Remove the class from each input when the modal closes.
    Array.from(document.getElementsByClassName("book-edit-input")).forEach(input => {
        input.classList.remove("is-invalid");
    });
});


