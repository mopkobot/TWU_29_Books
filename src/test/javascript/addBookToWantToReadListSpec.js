describe("should disable button", function () {
    it("should check if book exists in list while loading page", function () {
        spyOn($, 'post');

        window.onload();

        expect($.post).toHaveBeenCalled();
    });

});

