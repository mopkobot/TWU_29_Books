describe("should add book to want to read list", function () {
    it("click add book to want to read list", function () {
        var fixtures = '<button class="add-btn">Want to read</button>' +
            '<h2 class="bookId" style="display: none;">4</h2>';

        setFixtures(fixtures);

        window = {
            location:{
                reload:function(){}
            }
        };

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

});

