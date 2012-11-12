function addBookToWantToReadList() {
    var requestData = {
        bookId:$('.bookId').text()
    };

    $('.add-btn').on('click', function () {
        $.post("add-book-to-want", requestData, function () {
        });
    });
}

$(function () {
    addBookToWantToReadList();
});

initialize = function () {
    addBookToWantToReadList();
}
