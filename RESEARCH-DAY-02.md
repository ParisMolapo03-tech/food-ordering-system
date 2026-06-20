## Q1. What is a Java generic type? Why is <T> useful?
    * A generic type uses <T> as a placeholder for any type. It lets you write one class that
      works with any data type instead of writing separate classes for each.

## Q2. What does Lombok @Builder generate behind the scenes?
    * @Builder generates a builder class behind the scenes that lets you set fields in a 
      chain instead of calling multiple setters.

## Q3. What is the Builder design pattern? When to use it?
    *  The Builder pattern lets you construct complex objects step by step. Use it when an 
       object has many fields and you want to set only the ones you need.

##   Q4. What is LocalDateTime? How is it different from Date?
    * LocalDateTime gives you: 2026-06-18T08:42:11. It is a modern Java class for storing 
      date and time without timezone confusion. Date is the old way and is harder
      to work with.

## Q5. Why does a consistent response format matter to frontend developers?
    * A consistent response format means frontend developers always know the shape of the 
      response. They can write one handler for all API calls instead of handling each differently.

##  Q6. What does @JsonInclude(JsonInclude.Include.NON_NULL) do?
    * @JsonInclude(NON_NULL) tells Jackson to exclude fields that are null from the JSON response. 
      This keeps the response clean.

## Q7. What is a static factory method? Why use Response.success(...) instead of new Response<>()?
    * A static factory method is a static method that creates and returns an object. Response.success() 
      is easier to read and use than calling new Response<>() with all parameters manually.

#9. SELF-QUIZ
-------------
## Q1. Why use generic <T> instead of Object for data field?
    *  <T> is safer than Object because with Object you lose type information and need to cast. With
       <T> the compiler knows exactly what type is inside.

## Q2. Difference between Response<T> and ResponseEntity<T>? Can you have both at once?
    * Response<T> is your custom wrapper class. ResponseEntity<T> is Spring's HTTP wrapper
      that adds status codes and headers. You can combine them:
      ResponseEntity<Response<CategoryDto>> — Spring handles the HTTP, your 
      Response handles the body shape.

## Q3. If a request fails, what statusCode does Response hold?
    *  If a request fails, Response holds the error code like 404 or 400 in statusCode.

## Q4. Why add a timestamp?
    *  Timestamp helps with debugging — you can see exactly when a request was made. It also 
       helps frontend developers display things like "last updated 2 minutes ago."
