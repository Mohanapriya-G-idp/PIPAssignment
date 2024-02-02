function palindrome(str){

const inputString = str.replace(/[^a-zA-z0-9]/g,'').toLowercase();
return inputString === inputString.split('').reverse().join();


}
module.exports = palindrome;