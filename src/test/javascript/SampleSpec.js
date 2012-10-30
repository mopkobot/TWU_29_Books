// A typical jasmine spec; See jasmine docs
// Save in a new file and described your tests.
// See SampleSpecRunner.html for the next step.

// A JSLINT directive.
describe( "Sample test",
          function() {
              describe('JavaScript addition operator', function () {
                  it('adds two numbers together', function () {
                      expect(1 + 2).toEqual(3);
                  });
              });
          }
);
