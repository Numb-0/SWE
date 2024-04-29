// Jquery Script
// This script is used to handle the form submission of the add book form
// Without this script, the form submission would cause a page reload

// Add book form submission
$(document).ready(function() {
    $('#book-add-form').on('submit', function(event) {
        event.preventDefault();
        var formData = $(this).serialize();
        $.ajax({
            type: 'POST',
            url: '/dashboard-book-add',
            data: formData,
            success: function(response) {
                // Handle success
                // Show toast
                const booktoast = document.getElementById('book-toast')
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(booktoast)
                toastBootstrap.show();
                // Close modal
                $('#book-add-close-btn').click();
                // Reset form on success submission
                $('#book-add-form').trigger('reset');
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

        var url = $(this).attr('action');
        var formData = $(this).serialize();

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
            }
        });
    });
});

// Javascript code to change the action attribute of the edit form 
// During runtime!!!
document.getElementById('book-update-button').addEventListener('click', function(event) {
    var id = document.getElementById('item-id').value;
    document.getElementById('book-edit-form').action = '/dashboard-book-edit/' + id;
});

