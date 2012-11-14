function addBookToWantToReadList() {
    var requestData = {
        bookId:$('.bookId').text()
    };

    $('.add-btn').on('click', function () {
        $.post("add-book-to-want", requestData, function () {
        });
        window.location.reload();

    });

}

window.onload = function() {
    var requestData = {
        bookId:$('.bookId').text()
    };
    $.post("check-book", requestData, function (response) {
        if(response == "already saved") {
            $('.add-btn').attr('disabled', 'disabled');
        }
    });
};

$(function () {
    addBookToWantToReadList();
});

initialize = function () {
    addBookToWantToReadList();
}
