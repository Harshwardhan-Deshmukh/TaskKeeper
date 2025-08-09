const bcrypt = require("bcrypt");

const SALT_ROUNDS = 10;

async function generatePasswordHash(password) {
    const hash = await bcrypt.hash(password, SALT_ROUNDS);
    return hash;
}

async function compareHashedPasswords(hash, password) {
    const comparisionResult = await bcrypt.compare(password, hash);
    return comparisionResult;
}

module.exports = {
    generatePasswordHash,
    compareHashedPasswords
}