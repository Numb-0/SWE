function pinco() {
    console.log('pinco');
}

// Jquery Script
// This script is used to handle the form submission of the add book form
// Without this script, the form submission would cause a page reload
$(document).ready(function() {
    $('#book-add').on('submit', function(event) {
        event.preventDefault();
        var formData = $(this).serialize();
        $.ajax({
            type: 'POST',
            url: '/dashboard-add',
            data: formData,
            success: function(response) {
                // Close the modal
                $('#book-add-close').click();
            },
            error: function(response) {
                // Handle error
                console.log(response);
            }
        });
    });
});