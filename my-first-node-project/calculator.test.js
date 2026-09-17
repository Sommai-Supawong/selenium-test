const matOperations = require('./calculator');

describe('Calculator Operations', () => {
    test('Addition of two numbers', () => {
        expect(matOperations.sum(2, 3)).toBe(5);
    });

    test('Subtraction of two numbers', () => {
        expect(matOperations.diff(5, 3)).toBe(2);
    });

    test('Multiplication of two numbers', () => {
        expect(matOperations.product(4, 3)).toBe(12);
    });

    test('Division of two numbers', () => {
        expect(matOperations.divide(10, 2)).toBe(5);
    });

    test('Division by zero should throw an error', () => {
        expect(() => matOperations.divide(10, 0)).toThrow('Cannot divide by zero');
    })
});

describe("Calculator tests", () => {
    var input1 = 0
    var input2 = 0

    beforeAll(() => {
        console.log("beforeAll called");
    });

    afterAll(() => {
        console.log("afterAll called");
    });
    beforeEach(() => {
        console.log("beforeEach called");
        input1 = 1;
        input2 = 2;
    });
    afterEach(() => {
        console.log("afterEach called");
    });

    test('adding 1 + 2 should return 3', () => {
        // arrange and act
        var result = matOperations.sum(input1, input2)

        // assert
        expect(result).toBe(3);
    });
})