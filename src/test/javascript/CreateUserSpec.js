describe("click save", function () {
    it("should redirect to welcome page", function () {
        document.getElementsByClassName("username").val("username");
        document.getElementsByClassName("save").click();
        expect(document.getTitle()).toBe("Welcome");
    });
});

