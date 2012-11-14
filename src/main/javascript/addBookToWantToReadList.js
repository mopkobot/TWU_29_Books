window.onload= function() {
    var requestData = {
        bookId:$('.bookId').text()
    };
    $.post("check-book", requestData, function (response) {
        if(response == "already saved") {
            $('.add-btn').attr('disabled', 'disabled');
        }
    });

};

