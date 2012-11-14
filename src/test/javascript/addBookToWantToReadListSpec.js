describe("should add book to want to read list and display notification", function () {
    it("when you click add button to list", function () {
        var fixtures = '<button class="add-btn">Want to read</button>' +
            '<h2 class="bookId" style="display: none;">4</h2>';

        setFixtures(fixtures);

        spyOn(window.location, 'reload');

        var postUrl;

        var actual;
        initialize();

        spyOn($, 'post').andCallFake(function (resource, param, callback) {
            postUrl = resource;
            actual = param;
        });

        $('.add-btn').click();

        expect($.post).toHaveBeenCalled();
        expect(postUrl).toBe("add-book-to-want");
        expect(actual.bookId).toBe('4');
    });

    it("should check if book exists in list while loading page", function () {
        spyOn($, 'post');

        window.onload();

        expect($.post).toHaveBeenCalled();
    });

});

