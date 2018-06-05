# Acceptance tests

* Evaluating template "" results in the string ""
* Evaluating template "Hello, ${name}" with the value "Reader" for variable "name" results in the string "Hello, Reader"
* Evaluating template "${greeting}, ${name}" with values "Hi" and "Reader", respectively, results in the string "Hi, Reader"
* Evaluating template "Hello, ${name}" with no value for variable "name" raises a `MissingValueError`
* Evaluating template "Hello, ${name}" with values "Hi" and "Reader" for variables "doesnotexist" and "name", respectively, results in the string "Hello, Reader"
* Evaluating template "${one}, ${two}, ${three}" with values "1", "${foo}", and "3", respectively, results in the string "1, ${foo}, 3"
* Verify that a template of 100 words and 20 variables with values of approximately 15 characters each is evaluated in 200 miliseconds or less
* And so forth...