const mathOparetions = {
    sum: function (a, b) { 
        return a + b;
    },
    diff: function (a, b) {
        return a - b;
    },
    product: function(a, b) {
        return a * b;
    },
    divide: function(a, b) {
        if (b === 0) {
            throw new Error('Cannot divide by zero');
        }
        return a / b;
    }

};
module.exports = mathOparetions;