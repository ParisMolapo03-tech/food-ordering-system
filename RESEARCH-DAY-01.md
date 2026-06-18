# Research Day 01

## Q1. What does CRUD stand for?
    * CREATE, READ, UPDATE & DELETE

## Q2. Difference between POST, PUT, PATCH, DELETE?
    * POST sends a new data
    * PUT updates the data on an existing item
    * PATCH updates only one thing on an existing item
    * DELETE deletes the data

## Q3. HTTP Status Codes
    a. 201 
    b. 204
    c. 404
    d. 400
    e. 403

## Q4. @RequestBody, @RequestParam, @PathVariable
    4.1. @PathVariable - The ID in the url itself
        "Give me category number 3"
        // URL: /api/categories/3
            @GetMapping("/{id}")
            public CategoryDto getById(@PathVariable Long id) {...}
    
    4.2. @RequestParam — Extra filter added to the URL
        "Give me categories on page 2"
        // URL: /api/categories?page=2
            @GetMapping
            public List<CategoryDto> getAll(@RequestParam int page) {...}

    4.3. @RequestBody — Data sent inside the request, not in the URL
        "Here are the details of the new category I want to create"
        // Body: { "name": "Sushi" }
            @PostMapping
            public CategoryDto create(@RequestBody CategoryDto dto) {...}

## Q5. Jakarta Bean Validation lets you add rules to your fields
        * @NotBlank — Field cannot be empty
        * @Size — Controls minimum and maximum length
        * @Valid — Tells Spring to actually CHECK the validation

## Q6. Why return DTO not Entity?
        * For security - the entity might have sensitive fields we don't want to expose
        * For flexibility - if the database structure changes, the API response remains the same

## Q7. What is Optional<T>? Why does findById return Optional?
        * Optional is a wrapper that handles the case where a value might not exist. 
          findById returns Optional because the ID might not exist in the database. Instead 
          of crashing, Optional lets us handle the missing case cleanly by throwing a proper exception.


## Endpoints

| Method | URL                  | Body       |
|--------|----------------------|------------|
| POST   | /api/categories      | { "name" } |
| GET    | /api/categories      | -          |
| GET    | /api/categories/{id} | -          |
| PUT    | /api/categories/{id} | { "name" } |
| DELETE | /api/categories/{id} | -          |

# SELF-QUIZ
## Q1. Why ResponseEntity instead of returning the object?
        * ResponseEntity lets us control the HTTP status code ourselves instead of always returning 200. For 
          example when creating we return 201, when deleting we return 204.

## Q2. What status should a successful DELETE return? Why?
        * A successful DELETE should return 204 No Content because the resource has been removed and there is 
          nothing left to return to the client.

## Q3. Update only one field — PUT or PATCH?
        * PATCH is correct for updating one field because PUT replaces the entire resource. PATCH only updates
          what you send.

## Q4.  What happens if you forget @Valid on the controller?
        * If you forget @Valid, all the validation annotations on the DTO are ignored and invalid data gets
          saved to the database.

## Q5. Why must update/delete have {id} in the URL but create does not?
        * Update and delete need {id} because they target an existing record. Create does not need
          it because the database generates the id automatically when the new record is saved.



